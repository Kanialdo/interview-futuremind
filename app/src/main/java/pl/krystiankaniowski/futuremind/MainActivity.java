package pl.krystiankaniowski.futuremind;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import pl.krystiankaniowski.futuremind.adapter.DataAdapter;
import pl.krystiankaniowski.futuremind.adapter.ListManager;
import pl.krystiankaniowski.futuremind.managers.DatabaseManager;
import pl.krystiankaniowski.futuremind.model.database.Row;
import pl.krystiankaniowski.futuremind.rest.RestManager;

public class MainActivity extends AppCompatActivity implements ListManager, RestManager.RestObserver {

    // =============================================================================================
    //      FINALS
    // =============================================================================================

    private static final String TAG = MainActivity.class.getSimpleName();

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    private boolean twoPaneLayout;

    private DataAdapter adapter;

    private RealmResults<Row> result;

    // =============================================================================================
    //      LIFE CYCLE
    // =============================================================================================

    @Override
    public Context createDeviceProtectedStorageContext() {
        return super.createDeviceProtectedStorageContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (RestManager.getInstance(MainActivity.this).requestData()) {
                    adapter.setLoadingState();
                    DatabaseManager.getInstance(MainActivity.this).clearAllData();
                } else {
                    Snackbar.make(view, "Poczekaj na zakonczenie poprzedniego zapytania", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        RecyclerView recyclerView = ButterKnife.findById(this, R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);

        if (findViewById(R.id.item_detail_container) != null) {
            twoPaneLayout = true;
        }

        RestManager.getInstance(this).register(this);

        result = DatabaseManager.getInstance(this).getData(Row.class);
        result.addChangeListener(new RealmChangeListener<RealmResults<Row>>() {
            @Override
            public void onChange(RealmResults<Row> data) {
                result.removeChangeListener(this);
                if (data.size() > 0) {
                    Log.i(TAG, "Cached data received");
                    result = null;
                    adapter.setData(data);
                } else {
                    Log.i(TAG, "Make date request");
                    RestManager.getInstance(MainActivity.this).requestData();
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("f_webpage");
        if (fragment != null) {
            Log.v(TAG, "save fragment");
            getSupportFragmentManager().putFragment(outState, "frag_1", fragment);
        }

    }

    @Override
    protected void onDestroy() {

        RestManager.getInstance(this).unregister(this);

        if (result != null) {
            result.removeChangeListeners();
        }
        super.onDestroy();
    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new DataAdapter(this, this, null);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onRequestSuccess(List<Row> data) {
        adapter.setData(data);
    }

    @Override
    public void onRequestNoDataError() {
        adapter.setError("No data to display : (");
    }

    @Override
    public void onRequestError(Throwable t) {
        adapter.setError("Error, no data to display :(\n\nInfo for geeks: " + t.getMessage());
    }

    @Override
    public void showContent(String title, String url) {

        if (twoPaneLayout) {
            getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, DetailsFragment.newInstance(url), "f_webpage").commit();
        } else {
            startActivity(DetailsActivity.newIntent(this, title, url));
        }

    }

}
