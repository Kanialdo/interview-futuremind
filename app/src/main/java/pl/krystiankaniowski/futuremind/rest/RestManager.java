package pl.krystiankaniowski.futuremind.rest;

import android.content.Context;
import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.krystiankaniowski.futuremind.managers.DatabaseManager;
import pl.krystiankaniowski.futuremind.model.ModelConverter;
import pl.krystiankaniowski.futuremind.model.database.Row;
import pl.krystiankaniowski.futuremind.model.rest.DataContainer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestManager {

    // =============================================================================================
    //      INTERFACE
    // =============================================================================================

    public interface RestObserver {

        void onRequestSuccess(List<Row> data);

        void onRequestNoDataError();

        void onRequestError(Throwable t);
    }

    // =============================================================================================
    //      FINALS
    // =============================================================================================

    private static final String TAG = RestManager.class.getSimpleName();

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    private static RestManager instance;

    private FutureMindWebServices service;

    private Context applicationContext;

    private final String TASK = "test35";

    private boolean pendingRequest = false;

    private Set<RestObserver> observers = new HashSet<>();

    // =============================================================================================
    //      CONSTRUCTOR
    // =============================================================================================

    private RestManager(Context applicationContext) {

        this.applicationContext = applicationContext;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(FutureMindWebServices.URL).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(FutureMindWebServices.class);

    }

    public static RestManager getInstance(Context context) {

        if (instance == null) {
            instance = new RestManager(context.getApplicationContext());
        }

        return instance;

    }

    public void register(RestObserver observer) {
        observers.add(observer);
    }

    public void unregister(RestObserver observer) {
        observers.remove(observer);
    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    /**
     * return boolean if request has started
     */
    public boolean requestData() {

        if (pendingRequest) {
            Log.w(TAG, "Last request is still live");
            return false;
        }

        pendingRequest = true;

        Call<DataContainer> call = service.getData(TASK);
        call.enqueue(new Callback<DataContainer>() {

            @Override
            public void onResponse(Call<DataContainer> call, Response<DataContainer> response) {
                pendingRequest = false;

                if (response.isSuccessful()) {
                    Log.i(TAG, "request success");
                    List<Row> data = ModelConverter.Companion.toDatabaseModel(response.body().getData());
                    DatabaseManager.getInstance(applicationContext).saveData(data);
                    for (RestObserver observer : observers) {
                        observer.onRequestSuccess(data);
                    }
                } else {
                    Log.w(TAG, "request error, no data");
                    for (RestObserver observer : observers) {
                        observer.onRequestNoDataError();
                    }
                }

            }

            @Override
            public void onFailure(Call<DataContainer> call, Throwable t) {
                pendingRequest = false;
                Log.d("Error", t.getMessage());
                for (RestObserver observer : observers) {
                    observer.onRequestError(t);
                }
            }

        });

        return true;

    }

}
