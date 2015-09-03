package thodoras.escapeguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thodoras.escapeguide.R;

/**
 * Created by thodoras on 9/23/14.
 */
public class InfoTelephones extends Activity {

    public static final String LANGUAGE = "Language10";

    private String mLanguage;

    private Button mEmergency;
    private Button mHealth;
    private Button mPolice;
    private Button mOther;
    private Button mCulture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLanguage = getIntent().getStringExtra(LANGUAGE);

        if (mLanguage.equals("En")) {
            setContentView(R.layout.info_telephones);
        } else {
            setContentView(R.layout.info_telephones_el);
        }

        mEmergency = (Button) findViewById(R.id.button_tel_emergency);
        mEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InfoTelephones.this, TelEmergency.class);
                i.putExtra(TelEmergency.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

        mHealth = (Button) findViewById(R.id.button_tel_healthcare);
        mHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InfoTelephones.this, TelHealth.class);
                i.putExtra(TelHealth.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

        mPolice = (Button) findViewById(R.id.button_tel_police);
        mPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InfoTelephones.this, TelPolice.class);
                i.putExtra(TelPolice.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

        mOther = (Button) findViewById(R.id.button_tel_other);
        mOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InfoTelephones.this, TelOther.class);
                i.putExtra(TelOther.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

        mCulture = (Button) findViewById(R.id.button_tel_cultural);
        mCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InfoTelephones.this, TelCulture.class);
                i.putExtra(TelCulture.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });
    }
}
