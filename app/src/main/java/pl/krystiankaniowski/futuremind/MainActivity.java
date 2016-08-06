package pl.krystiankaniowski.futuremind;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pl.krystiankaniowski.futuremind.adapter.DataAdapter;
import pl.krystiankaniowski.futuremind.adapter.ListManager;
import pl.krystiankaniowski.futuremind.adapter.view.ViewType;
import pl.krystiankaniowski.futuremind.model.SingleData;
import pl.krystiankaniowski.futuremind.rest.DataManager;

public class MainActivity extends AppCompatActivity implements ListManager {

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    private boolean twoPaneLayout;

    private DataManager dataManager = new DataManager();
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

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPaneLayout = true;
        }

        // dataManager.requestData();

        List<ViewType> data = new ArrayList<>();
        SingleData testObject = new SingleData();
        testObject.setTitle("title");
        testObject.setDescription("http://www.google.pl");
        testObject.setImageUrl("http://www.google.pl");
        data.add(testObject);

        adapter.swap(data);

    }

    // =============================================================================================
    //      LOGIC
    // =============================================================================================

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        adapter = new DataAdapter(this, null);
        recyclerView.setAdapter(adapter);
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
