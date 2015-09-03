package thodoras.escapeguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by thodoras on 9/16/14.
 */
public class OverviewMenu extends Activity {

    public static final String LANGUAGE = "languagexziaosdncv";

    private String mLanguage;

    private Button mFastFacts;
    private Button mGastronomy;
    private Button mWineRoads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLanguage = getIntent().getStringExtra(LANGUAGE);

        if (mLanguage.equals("En")) {
            setContentView(R.layout.overview_menu);
        } else {
            setContentView(R.layout.overview_menu_el);
        }

        mFastFacts = (Button) findViewById(R.id.ov_fast_facts);
        mFastFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OverviewMenu.this, FastFacts.class);
                startActivity(i);
            }
        });

        mGastronomy = (Button)findViewById(R.id.ov_gastronomy);
        mGastronomy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OverviewMenu.this, OvGastronomy.class);
                i.putExtra(OvGastronomy.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

        mWineRoads = (Button) findViewById(R.id.wine_roads);
        mWineRoads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OverviewMenu.this, OvWineRoads.class);
                i.putExtra(OvWineRoads.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

    }
}
