package pl.krystiankaniowski.futuremind;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;
import pl.krystiankaniowski.futuremind.adapter.DataAdapter;
import pl.krystiankaniowski.futuremind.adapter.ListManager;
import pl.krystiankaniowski.futuremind.managers.DatabaseManager;
import pl.krystiankaniowski.futuremind.model.database.Row;
import pl.krystiankaniowski.futuremind.rest.RestManager;
import pl.krystiankaniowski.futuremind.rest.RestObserver;

public class MainActivity extends AppCompatActivity implements ListManager, RestObserver {

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    private boolean twoPaneLayout;

    private DataAdapter adapter;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        RecyclerView recyclerView = ButterKnife.findById(this, R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPaneLayout = true;
        }

        DatabaseManager.getInstance(getApplicationContext()).clearData(Row.class);
        RestManager.getInstance(getApplicationContext()).requestData(this);

    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new DataAdapter(this, null);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onSuccess(List<Row> data) {
        adapter.swap(data);
        DatabaseManager.getInstance(getApplicationContext()).clearData(Row.class);
    }

    @Override
    public void showContent(String title, String url) {

        if (twoPaneLayout) {
            getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, DetailsFragment.newInstance(url)).commit();
        } else {
            startActivity(DetailsActivity.newIntent(this, url));
        }

    }

}
