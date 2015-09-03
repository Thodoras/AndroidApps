package thodoras.escapeguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.thodoras.escapeguide.R;

/**
 * Created by thodoras on 9/16/14.
 */
public class EscapeGuideMenu extends Activity {

    public static final String LANGUAGE = "languagr1";

    private ImageView mOverview;
    private ImageView mSights;
    private ImageView mHotSpots;
    private ImageView mGastronomy;
    private ImageView mInfo;
    private ImageView mRoutes;
    private ImageView mOffers;

    private String mLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escape_guide_menu);

        mLanguage = getIntent().getStringExtra(LANGUAGE);

        mOverview = (ImageView) findViewById(R.id.overview_button);
        mOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EscapeGuideMenu.this, OverviewMenu.class);
                i.putExtra(OverviewMenu.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

        mSights = (ImageView) findViewById(R.id.sights_button);
        mSights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_2 = new Intent(EscapeGuideMenu.this, Sights.class);
                i_2.putExtra(Sights.LANGUAGE, mLanguage);
                startActivity(i_2);
            }
        });

        mGastronomy = (ImageView) findViewById(R.id.gastronomy_button);
        mGastronomy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_3 = new Intent(EscapeGuideMenu.this, Gastronomy.class);
                i_3.putExtra(Gastronomy.LANGUAGE, mLanguage);
                startActivity(i_3);
            }
        });

        mHotSpots = (ImageView) findViewById(R.id.hotspots_button);
        mHotSpots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EscapeGuideMenu.this, HotSpots.class);
                i.putExtra(HotSpots.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

        mInfo = (ImageView) findViewById(R.id.info_button);
        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EscapeGuideMenu.this, Info.class);
                i.putExtra(Info.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

        mRoutes = (ImageView) findViewById(R.id.routes_button);
        mRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EscapeGuideMenu.this, Routes.class);
                i.putExtra(Routes.LANGUAGE, mLanguage);
                startActivity(i);
            }
        });

        mOffers = (ImageView) findViewById(R.id.offers_button);
        mOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EscapeGuideMenu.this, Offers.class);
                i.putExtra(Offers.LANGUAGE,mLanguage);
                startActivity(i);
            }
        });

    }
}