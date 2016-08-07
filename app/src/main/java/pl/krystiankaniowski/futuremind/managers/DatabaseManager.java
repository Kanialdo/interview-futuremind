package pl.krystiankaniowski.futuremind.managers;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

public class DatabaseManager {

    // =============================================================================================
    //      FINALS
    // =============================================================================================

    private static final String TAG = DatabaseManager.class.getSimpleName();

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    private static DatabaseManager instance;

    private Realm realm;

    // =============================================================================================
    //      CONSTRUCTOR
    // =============================================================================================

    private DatabaseManager(Context context) {

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfig);

        realm = Realm.getDefaultInstance();

    }

    public static DatabaseManager getInstance(Context context) {

        if (instance == null) {
            instance = new DatabaseManager(context.getApplicationContext());
        }

        return instance;

    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    public <Type extends RealmObject> RealmResults<Type> getData(Class<Type> typeClass) {

        final RealmResults<Type> items = realm.where(typeClass).findAllSortedAsync("orderId", Sort.ASCENDING);

        Log.i(TAG, "Database content");

        for (Type row : items) {
            Log.d(TAG, String.valueOf(row));
        }

        return items;

    }

    public <Type extends RealmObject> void saveData(final List<Type> data) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(data);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.v(TAG, "Data seved");
            }
        });

    }

    public <Type extends RealmObject> void clearData(final Class<Type> typeClass) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(typeClass);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.v(TAG, "Data seved");
            }
        });

    }

    public void clearAllData() {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.v(TAG, "Data seved");
            }
        });

    }

}
