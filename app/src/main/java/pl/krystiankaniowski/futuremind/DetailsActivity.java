package pl.krystiankaniowski.futuremind;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class DetailsActivity extends AppCompatActivity {

    // =============================================================================================
    //      FINALS
    // =============================================================================================

    private static final String TAG = DetailsActivity.class.getSimpleName();

    private static final String ARGUMNET_TILTE = "title";
    private static final String ARGUMNET_URL = "url";

    private Fragment innerFragment;

    // =============================================================================================
    //      CONSTRUCTOR
    // =============================================================================================

    public static Intent newIntent(Context context, String title, String url) {

        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(ARGUMNET_TILTE, title);
        intent.putExtra(ARGUMNET_URL, url);

        return intent;

    }

    // =============================================================================================
    //      LIFE CYCLE
    // =============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        actionBar.setTitle(getIntent().getStringExtra(ARGUMNET_TILTE));

        if (savedInstanceState == null) {
            innerFragment = DetailsFragment.newInstance(getIntent().getStringExtra(ARGUMNET_URL));
            getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, innerFragment).commit();
        } else {
            innerFragment = getSupportFragmentManager().getFragment(savedInstanceState, "frag_1");
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "frag_1", innerFragment);
    }

    // =============================================================================================
    //      MENU
    // =============================================================================================

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
