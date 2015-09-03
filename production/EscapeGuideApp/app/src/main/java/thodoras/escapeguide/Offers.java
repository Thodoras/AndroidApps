package thodoras.escapeguide;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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

/**
 * Created by thodoras on 9/25/14.
 */
public class Offers extends Activity {

    public static final String LANGUAGE = "language5";

    private String mLanguage;

    ListView list;
    SightsAdapter adapter;
    ArrayList<HelperOffers> offersList;
    ProgressDialog pd;
    TextView textTitle;

    ImageView buyButton;
    ImageView home;
    ImageView credits;
    ImageView contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_2);

        mLanguage = getIntent().getStringExtra(LANGUAGE);

        textTitle = (TextView)findViewById(R.id.title_text);
        textTitle.setText("O F F E R S");

        list = (ListView) findViewById(R.id.list);
        offersList = new ArrayList<HelperOffers>();

        if (mLanguage.equals("En")) {
            new SightsAsyncTask().execute("http://www.escapeguide.gr/en/rest/views/mobile_listings.json?display_id=offers_service");
        }else{
            new SightsAsyncTask().execute("http://www.escapeguide.gr/el/rest/views/mobile_listings.json?display_id=offers_service");
        }

        buyButton = (ImageView)findViewById(R.id.button_routes_book_now);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Offers.this, WebNet.class);
                String url = "http://www.escapeguide.gr/en/offers";
                i.putExtra(WebNet.URL, url);
                startActivity(i);
            }
        });

        credits = (ImageView) findViewById(R.id.credits_button);
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Offers.this, Credits.class);
                startActivity(i);
            }
        });

        contact = (ImageView) findViewById(R.id.contact_button);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Offers.this, Contact.class);
                startActivity(i);
            }
        });

        home = (ImageView) findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Offers.this, EscpeGuideLanguage.class);
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

                        HelperOffers sights = new HelperOffers();

                        JSONObject obj = array.getJSONObject(i);


                        sights.setImage(obj.getString("thumbnail"));
                        sights.setTitle(obj.getString("title"));
                        sights.setLocation(obj.getString("location"));
                        sights.setDescription(obj.getString("description"));

                        offersList.add(sights);
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

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(Offers.this, "Please wait", "Downloading content", false, true);
        }


        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (result == false) {
                pd.dismiss();
                Toast.makeText(Offers.this, "Network Error", Toast.LENGTH_SHORT).show();
            } else {
                pd.dismiss();
                OffersAdapter adapter = new OffersAdapter(getApplicationContext(), R.layout.row3, offersList);
                list.setAdapter(adapter);
            }
        }

    }
}
