package pl.krystiankaniowski.futuremind.rest;

import android.util.Log;

import pl.krystiankaniowski.futuremind.model.DataContainer;
import pl.krystiankaniowski.futuremind.model.SingleData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {

    // =============================================================================================
    //      FINALS
    // =============================================================================================

    private static final String TAG = DataManager.class.getSimpleName();

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    private FutureMindWebServices service;

    private final String TASK = "test35";

    // =============================================================================================
    //      CONSTRUCTOR
    // =============================================================================================

    public DataManager() {

        // create webservice impl

        Retrofit retrofit = new Retrofit.Builder().baseUrl(FutureMindWebServices.URL).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(FutureMindWebServices.class);

    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    public void requestData() {

        Call<DataContainer> call = service.getData(TASK);

        call.enqueue(new Callback<DataContainer>() {

            @Override
            public void onResponse(Call<DataContainer> call, Response<DataContainer> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "request success");
                    response.body().getData(); // data
                    for (SingleData data : response.body().getData()) {
                        Log.v(TAG, "Object: " + data.toString());
                    }
                } else {
                    Log.w(TAG, "request error, no data");
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<DataContainer> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }

        });

    }

}
