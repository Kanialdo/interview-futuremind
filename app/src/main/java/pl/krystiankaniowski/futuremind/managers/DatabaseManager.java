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
            instance = new DatabaseManager(context);
        }

        return instance;

    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    public <Type extends RealmObject> void clearData(Class<Type> typeClass) {

        realm.beginTransaction();
        realm.delete(typeClass);
        realm.commitTransaction();

    }

    public <Type extends RealmObject> void saveData(List<Type> data) {

        realm.beginTransaction();
        realm.copyToRealm(data);
        realm.commitTransaction();

    }

    public <Type extends RealmObject> List<Type> getData(Class<Type> typeClass) {

        final RealmResults<Type> items = realm.where(typeClass).findAllSorted("orderId", Sort.ASCENDING);

        Log.i(TAG, "Database content");

        for (Type row : items) {
            Log.d(TAG, String.valueOf(row));
        }

        return items;

    }

}
