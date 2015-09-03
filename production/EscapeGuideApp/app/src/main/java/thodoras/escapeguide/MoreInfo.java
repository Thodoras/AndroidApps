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

import java.io.InputStream;
import java.net.URL;

/**
 * Created by thodoras on 9/25/14.
 */
public class MoreInfo extends Activity {

    public static final String IMAGE = "image1";
    public static final String TITLE = "title1";
    public static final String LOCATION = "location1";
    public static final String DESCRIPTION = "description";
    public static final String LATITUDE = "123";
    public static final String LONGITUDE ="456";
    public static final String BIG_PICTURE = "bigPicture1";
    public static final String ADDRESS = "address1";
    public static final String TELEPHONE = "telephone1";
    public static final String EMAIL = "email1";
    public static final String WEB = "web1";
    public static final String IMAGES = "images1";


    private String mLatitude;
    private String mLongitude;
    private String mTitle;
    private String mDescription;
    private String mBigPicture;
    private String mAdress;
    private String mTelephone;
    private String mEmail;
    private String mWeb;
    private String mImages;

    private ImageView mapButton;
    private ImageView InfoButton;
    private ImageView gridButton;

    ImageView home;
    ImageView credits;
    ImageView contact;

    private TextView textTitle;
    private TextView textDescription;
    private ImageView img;
    Bitmap bitmap;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_info);
        mLatitude = getIntent().getStringExtra(LATITUDE);
        mLongitude = getIntent().getStringExtra(LONGITUDE);
        mTitle = getIntent().getStringExtra(TITLE);
        mDescription = getIntent().getStringExtra(DESCRIPTION);
        mBigPicture = getIntent().getStringExtra(BIG_PICTURE);
        mAdress = getIntent().getStringExtra(ADDRESS);
        mTelephone = getIntent().getStringExtra(TELEPHONE);
        mEmail = getIntent().getStringExtra(EMAIL);
        mWeb = getIntent().getStringExtra(WEB);
        mImages = getIntent().getStringExtra(IMAGES);
        textTitle = (TextView) findViewById(R.id.title_text);
        textTitle.setText(mTitle);
        textDescription = (TextView) findViewById(R.id.description_text);
        textDescription.setText(mDescription);


        mapButton = (ImageView) findViewById(R.id.map_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreInfo.this, Maps.class);
                i.putExtra(Maps.LATITUDE, mLatitude);
                i.putExtra(Maps.LONGITUDE, mLongitude);
                startActivity(i);
            }
        });

        InfoButton = (ImageView)findViewById(R.id.more_more_info_button);
        InfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreInfo.this, MoreMoreInfo.class);
                i.putExtra(MoreMoreInfo.TITLE, mTitle);
                i.putExtra(MoreMoreInfo.BIG_IMAGE, mBigPicture);
                i.putExtra(MoreMoreInfo.ADDRESS, mAdress);
                i.putExtra(MoreMoreInfo.TELEPHONE, mTelephone);
                i.putExtra(MoreMoreInfo.EMAIL, mEmail);
                i.putExtra(MoreMoreInfo.WEB,mWeb);
                i.putExtra(MoreMoreInfo.LATITUDE, mLatitude);
                i.putExtra(MoreMoreInfo.LONGITUDE, mLongitude);
                i.putExtra(MoreMoreInfo.IMAGES, mImages);
                startActivity(i);
            }
        });

        gridButton = (ImageView) findViewById(R.id.grid_button);
        gridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreInfo.this, Grid.class);
                i.putExtra(Grid.IMAGES, mImages);
                i.putExtra(Grid.TITLE, mTitle);
                startActivity(i);
            }
        });

        credits = (ImageView) findViewById(R.id.credits_button);
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreInfo.this, Credits.class);
                startActivity(i);
            }
        });

        contact = (ImageView) findViewById(R.id.contact_button);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreInfo.this, Contact.class);
                startActivity(i);
            }
        });

        home = (ImageView) findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreInfo.this, EscpeGuideLanguage.class);
                startActivity(i);
            }
        });

        img = (ImageView)findViewById(R.id.title_image);

        new LoadImage().execute(mBigPicture);

    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = ProgressDialog.show(MoreInfo.this, "Please wait", "Downloading content", false, true);
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
                Toast.makeText(MoreInfo.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
