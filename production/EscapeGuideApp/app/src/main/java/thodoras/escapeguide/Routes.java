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
public class Routes extends Activity {

    public static final String LANGUAGE = "lamguage4";

    private String mLanguage;

    ListView list;
    SightsAdapter adapter;
    ArrayList<HelperRoutes> routesList;
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

        textTitle = (TextView) findViewById(R.id.title_text);
        textTitle.setText("R O U T E S");



        list = (ListView) findViewById(R.id.list);
        routesList = new ArrayList<HelperRoutes>();

        if (mLanguage.equals("En")) {
            new SightsAsyncTask().execute("http://www.escapeguide.gr/en/rest/views/mobile_listings.json?display_id=routes_service");
        } else {
            new SightsAsyncTask().execute("http://www.escapeguide.gr/el/rest/views/mobile_listings.json?display_id=routes_service");
        }

        buyButton = (ImageView)findViewById(R.id.button_routes_book_now);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Routes.this, WebNet.class);
                String url = "http://www.escapeguide.gr/en/routes";
                i.putExtra(WebNet.URL, url);
                startActivity(i);
            }
        });

        credits = (ImageView) findViewById(R.id.credits_button);
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Routes.this, Credits.class);
                startActivity(i);
            }
        });

        contact = (ImageView) findViewById(R.id.contact_button);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Routes.this, Contact.class);
                startActivity(i);
            }
        });

        home = (ImageView) findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Routes.this, EscpeGuideLanguage.class);
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

                        HelperRoutes sights = new HelperRoutes();

                        JSONObject obj = array.getJSONObject(i);


                        sights.setImage(obj.getString("thumbnail"));
                        sights.setTitle(obj.getString("title"));
                        sights.setTopPlaces(obj.getString("top places"));
                        sights.setDays(obj.getString("days"));
                        sights.setDistance(obj.getString("distance"));
                        sights.setDestination(obj.getString("destination "));
                        sights.setCharge(obj.getString("minimum charge"));
                        sights.setDescription(obj.getString("description"));



                        routesList.add(sights);
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
            pd = ProgressDialog.show(Routes.this, "Please wait", "Downloading content", false, true);
        }


        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (result == false) {
                pd.dismiss();
                Toast.makeText(Routes.this, "Network Error", Toast.LENGTH_SHORT).show();
            } else {
                pd.dismiss();
                RoutesAdapter adapter = new RoutesAdapter(getApplicationContext(), R.layout.row2, routesList);
                list.setAdapter(adapter);
            }
        }

    }
}
