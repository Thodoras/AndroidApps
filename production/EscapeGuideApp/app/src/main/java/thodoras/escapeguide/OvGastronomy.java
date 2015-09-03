package thodoras.escapeguide;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by thodoras on 9/22/14.
 */
public class OvGastronomy extends Activity{

    public static final String LANGUAGE = "languagecnidojs";

    private String mLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLanguage = getIntent().getStringExtra(LANGUAGE);

        if (mLanguage.equals("En")) {
            setContentView(R.layout.ov_gastronomy);
        } else {
            setContentView(R.layout.ov_gastronomy_el);
        }
    }
}
