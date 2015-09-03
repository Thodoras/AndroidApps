package thodoras.escapeguide;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.thodoras.escapeguide.R;

/**
 * Created by thodoras on 9/27/14.
 */
public class WebNet extends Activity {

    public static final String URL = "url1";

    private String mURL;

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_net);

        mURL = getIntent().getStringExtra(URL);

        webView = (WebView)findViewById(R.id.webview2);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(mURL);

    }
}
