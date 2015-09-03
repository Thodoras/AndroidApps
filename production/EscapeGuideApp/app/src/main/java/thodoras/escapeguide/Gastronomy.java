package thodoras.escapeguide;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thodoras.escapeguide.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class Gastronomy extends Activity {

    public static final String LANGUAGE = "language3";

    private String mLanguage;

    ListView list;
    GastronomyAdapter adapter;
    ArrayList<HelperGastronomy> gastronomyList;
    ProgressDialog pd;
    TextView textTitle;

    ImageView home;
    ImageView credits;
    ImageView contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        mLanguage = getIntent().getStringExtra(LANGUAGE);


        textTitle = (TextView)findViewById(R.id.title_text);
        textTitle.setText("G A S T R O N O M Y");

        list = (ListView) findViewById(R.id.list);
        gastronomyList = new ArrayList<HelperGastronomy>();

        if (mLanguage.equals("En")) {
            new SightsAsyncTask().execute("http://www.escapeguide.gr/en/rest/views/mobile_listings.json?display_id=gastronomy_service");
        } else {
            new SightsAsyncTask().execute("http://www.escapeguide.gr/el/rest/views/mobile_listings.json?display_id=gastronomy_service");
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent j = new Intent(Gastronomy.this, MoreInfo.class);
                String latitude = gastronomyList.get(i).getLatitude();
                String longitude = gastronomyList.get(i).getLongitude();
                String title = gastronomyList.get(i).getTitle();
                String location = gastronomyList.get(i).getLocation();
                String coverImage = gastronomyList.get(i).getImage();
                String description = gastronomyList.get(i).getDescription();
                String images = gastronomyList.get(i).getImages();
                String address = gastronomyList.get(i).getAdress();
                String email = gastronomyList.get(i).getEmail();
                String url = gastronomyList.get(i).getUrl();
                String phone = gastronomyList.get(i).getPhone();
                j.putExtra(MoreInfo.LATITUDE, latitude);
                j.putExtra(MoreInfo.LONGITUDE,longitude);
                j.putExtra(MoreInfo.TITLE,title);
                j.putExtra(MoreInfo.LOCATION, location);
                j.putExtra(MoreInfo.BIG_PICTURE, coverImage);
                j.putExtra(MoreInfo.DESCRIPTION,description);
                j.putExtra(MoreInfo.IMAGES,images);
                j.putExtra(MoreInfo.ADDRESS,address);
                j.putExtra(MoreInfo.EMAIL, email);
                j.putExtra(MoreInfo.WEB, url);
                j.putExtra(MoreInfo.TELEPHONE, phone);
                startActivity(j);
            }
        });

        credits = (ImageView) findViewById(R.id.credits_button);
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Gastronomy.this, Credits.class);
                startActivity(i);
            }
        });

        contact = (ImageView) findViewById(R.id.contact_button);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Gastronomy.this, Contact.class);
                startActivity(i);
            }
        });

        home = (ImageView) findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Gastronomy.this, EscpeGuideLanguage.class);
                startActivity(i);
            }
        });
    }

    public class SightsAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {


            try {

                HttpClient client = new DefaultHttpClient();
                HttpGet post = new HttpGet(params[0]);
                HttpResponse response = client.execute(post);
                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {

                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    JSONArray array = new JSONArray(data);

                    for (int i = 0; i < array.length(); i++) {

                        HelperGastronomy sights = new HelperGastronomy();

                        JSONObject obj = array.getJSONObject(i);


                        sights.setImage(obj.getString("thumbnail"));
                        sights.setTitle(obj.getString("title"));
                        sights.setLocation(obj.getString("location"));
                        sights.setLatitude(obj.getString("lat"));
                        sights.setLongitude(obj.getString("lon"));
                        sights.setCountImages(obj.getString("count_images"));
                        sights.setDescription(obj.getString("description"));
                        sights.setImages(obj.getString("images"));
                        sights.setAdress(obj.getString("address"));
                        sights.setPhone(obj.getString("phone"));
                        sights.setEmail(obj.getString("email"));
                        sights.setUrl(obj.getString("url"));

                        gastronomyList.add(sights);
                    }

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(Gastronomy.this, "Please wait", "Downloading content", false, true);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (result == false) {
                pd.dismiss();
                Toast.makeText(Gastronomy.this, "Network Error", Toast.LENGTH_SHORT).show();
            } else {
                pd.dismiss();
                GastronomyAdapter adapter = new GastronomyAdapter(getApplicationContext(), R.layout.row, gastronomyList);
                list.setAdapter(adapter);
            }
        }

    }

}
