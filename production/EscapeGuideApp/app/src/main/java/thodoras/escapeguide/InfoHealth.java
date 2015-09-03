package thodoras.escapeguide;

import android.app.Activity;
import android.os.Bundle;

import com.example.thodoras.escapeguide.R;

/**
 * Created by thodoras on 9/23/14.
 */
public class InfoHealth extends Activity {

    public static final String LANGUAGE = "languagejklasd";

    private String mLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLanguage = getIntent().getStringExtra(LANGUAGE);

        if (mLanguage.equals("En")) {
            setContentView(R.layout.info_health);
        } else {
            setContentView(R.layout.info_health_el);
        }
    }
}
