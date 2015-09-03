package thodoras.escapeguide;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.thodoras.escapeguide.R;

/**
 * Created by thodoras on 9/25/14.
 */
public class Maps extends Activity {

    public static final String LATITUDE = "LAT1";
    public static final String LONGITUDE = "LON";

    private String mLatitude;
    private String mLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        mLatitude = getIntent().getStringExtra(LATITUDE);
        mLongitude = getIntent().getStringExtra(LONGITUDE);
        String url = "http://www.openstreetmap.org/?mlat="+mLatitude+"&mlon="+mLongitude+"&zoom=12#map=5/40.613/22.188";
        WebView view = (WebView) this.findViewById(R.id.webview1);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(url);
    }
}
