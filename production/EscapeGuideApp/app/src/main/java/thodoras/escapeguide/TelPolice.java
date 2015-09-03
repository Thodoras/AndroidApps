package thodoras.escapeguide;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by thodoras on 9/23/14.
 */
public class TelPolice extends Activity {

    public static final String LANGUAGE = "languageqwerq";

    private String mLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLanguage = getIntent().getStringExtra(LANGUAGE);

        if (mLanguage.equals("En")) {
            setContentView(R.layout.telephones_police);
        } else {
            setContentView(R.layout.telephones_police_el);
        }
    }
}
