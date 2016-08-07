package pl.krystiankaniowski.futuremind;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.ButterKnife;

public class DetailsFragment extends Fragment {

    // =============================================================================================
    //      FINALS
    // =============================================================================================

    private static final String TAG = DetailsFragment.class.getSimpleName();

    private static final String ARGUMNET_URL = "url";

    // =============================================================================================
    //      VARIABLES
    // =============================================================================================

    private String url;

    private WebView webView;

    // =============================================================================================
    //      CONSTRUCTOR
    // =============================================================================================

    public static DetailsFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(ARGUMNET_URL, url);

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;

    }

    // =============================================================================================
    //      LIFE CYCLE
    // =============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url = getArguments().getString(ARGUMNET_URL);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        webView = ButterKnife.findById(rootView, R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore the fragment's state here
            webView.restoreState(savedInstanceState);
        } else {
            Log.d(TAG, "URL :" + url);
            webView.loadUrl(url);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

}
