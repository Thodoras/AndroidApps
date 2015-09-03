package thodoras.escapeguide;

import android.app.Activity;
import android.os.Bundle;

import com.example.thodoras.escapeguide.R;

/**
 * Created by thodoras on 9/22/14.
 */
public class OvWineRoads extends Activity {

    public static final String LANGUAGE = "languagezcxnbvm";

    private String mLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLanguage = getIntent().getStringExtra(LANGUAGE);

        if (mLanguage.equals("En")) {
            setContentView(R.layout.ov_wine_roads);
        } else {
            setContentView(R.layout.ov_wine_roads_el);
        }
    }
}
