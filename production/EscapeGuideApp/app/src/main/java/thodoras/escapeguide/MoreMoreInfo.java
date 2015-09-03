package thodoras.escapeguide;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thodoras.escapeguide.R;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by thodoras on 9/26/14.
 */
public class MoreMoreInfo extends Activity {

    public static final String TITLE = "title2";
    public static final String BIG_IMAGE = "big_image_2";
    public static final String ADDRESS = "address2";
    public static final String TELEPHONE = "telephone2";
    public static final String WEB = "web2";
    public static final String EMAIL = "email2";
    public static final String OPEN_HOURS = "open hours2";
    public static final String LATITUDE = "latitude2";
    public static final String LONGITUDE = "longitude2";
    public static final String IMAGES = "images2";

    private String mTitle;
    private String mBigImage;
    private String mAddress;
    private String mTelephone;
    private String mWeb;
    private String mEmail;
    private String mOpenHours;
    private String mLatitude;
    private String mLongitude;
    private String mImages;

    private ImageView mapButton;
    private ImageView gridButton;

    private TextView textTitle;
    private TextView textAddress;
    private TextView textTelephone;
    private TextView textWeb;
    private TextView textEmail;
    private TextView textOpenHours;
    private ImageView img;
    Bitmap bitmap;
    ProgressDialog pDialog;

    ImageView home;
    ImageView credits;
    ImageView contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_more_info);

        mTitle = getIntent().getStringExtra(TITLE);
        mBigImage = getIntent().getStringExtra(BIG_IMAGE);
        mAddress = getIntent().getStringExtra(ADDRESS);
        mTelephone = getIntent().getStringExtra(TELEPHONE);
        mWeb = getIntent().getStringExtra(WEB);
        mEmail = getIntent().getStringExtra(EMAIL);
        mOpenHours = getIntent().getStringExtra(OPEN_HOURS);
        mLatitude = getIntent().getStringExtra(LATITUDE);
        mLongitude = getIntent().getStringExtra(LONGITUDE);
        mBigImage = getIntent().getStringExtra(BIG_IMAGE);
        mImages = getIntent().getStringExtra(IMAGES);

        textTitle = (TextView) findViewById(R.id.title_text);
        textTitle.setText(mTitle);
        textAddress = (TextView) findViewById(R.id.adress_text);
        if ((mAddress == null) || (mAddress.equals("null"))){textAddress.setText("No info ");}else{textAddress.setText(mAddress);}
        textTelephone = (TextView) findViewById(R.id.telephone_text);
        if ((mTelephone == null) || (mTelephone.equals("null"))){textTelephone.setText("No info ");}else{textTelephone.setText(mTelephone);}
        textEmail = (TextView) findViewById(R.id.email_text);
        if ((mEmail == null) || (mEmail.equals("null"))){textEmail.setText("No info ");}else{textEmail.setText(mEmail);}
        textWeb = (TextView) findViewById(R.id.web_text);
        if ((mWeb == null) || (mWeb.equals("null"))){textWeb.setText("No info ");}else{textWeb.setText(mWeb);}
        textOpenHours = (TextView) findViewById(R.id.open_hous_text);
        if ((mOpenHours == null) || (mOpenHours.equals("null"))){textOpenHours.setText("No info ");}else{textOpenHours.setText(mOpenHours);}

        mapButton = (ImageView)findViewById(R.id.map_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreMoreInfo.this, Maps.class);
                i.putExtra(Maps.LATITUDE, mLatitude);
                i.putExtra(Maps.LONGITUDE, mLongitude);
                startActivity(i);
            }
        });

        gridButton = (ImageView) findViewById(R.id.grid_button);
        gridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreMoreInfo.this, Grid.class);
                i.putExtra(Grid.IMAGES, mImages);
                i.putExtra(Grid.TITLE, mTitle);
                startActivity(i);
            }
        });

        credits = (ImageView) findViewById(R.id.credits_button);
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreMoreInfo.this, Credits.class);
                startActivity(i);
            }
        });

        contact = (ImageView) findViewById(R.id.contact_button);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreMoreInfo.this, Contact.class);
                startActivity(i);
            }
        });

        home = (ImageView) findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreMoreInfo.this, EscpeGuideLanguage.class);
                startActivity(i);
            }
        });

        img = (ImageView)findViewById(R.id.title_image);
        new LoadImage().execute(mBigImage);
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = ProgressDialog.show(MoreMoreInfo.this, "Please wait", "Downloading content", false, true);
        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                img.setImageBitmap(image);
                pDialog.dismiss();
            }else{
                pDialog.dismiss();
                Toast.makeText(MoreMoreInfo.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
