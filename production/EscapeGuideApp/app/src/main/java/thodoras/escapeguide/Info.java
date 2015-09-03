package thodoras.escapeguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thodoras.escapeguide.R;

/**
 * Created by thodoras on 9/22/14.
 */
public class Info extends Activity {

    public static final String LANGUAGE = "language6";

    private String mLanguage;

    private Button mTransportation;
    private Button mCivilization;
    private Button mHealth;
    private Button mTelephones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLanguage = getIntent().getStringExtra(LANGUAGE);

        if(mLanguage.equals("En")) {
            setContentView(R.layout.info_menu);
        } else {
            setContentView(R.layout.info_menu_2);
        }
        mTransportation = (Button) findViewById(R.id.info_transportation);
        mTransportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Info.this, InfoTransportation.class);
                i.putExtra(InfoTransportation.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

        mCivilization = (Button) findViewById(R.id.info_civilization);
        mCivilization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Info.this, InfoCivilization.class);
                i.putExtra(InfoCivilization.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

        mHealth = (Button) findViewById(R.id.info_health);
        mHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Info.this, InfoHealth.class);
                i.putExtra(InfoHealth.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

        mTelephones = (Button) findViewById(R.id.info_telephones);
        mTelephones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Info.this, InfoTelephones.class);
                i.putExtra(InfoTelephones.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });
    }
}
