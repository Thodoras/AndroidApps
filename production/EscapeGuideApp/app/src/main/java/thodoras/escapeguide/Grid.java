package thodoras.escapeguide;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thodoras.escapeguide.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by thodoras on 9/27/14.
 */
public class Grid extends Activity {

    public static final String IMAGES = "images3";
    public static final String TITLE = "title3";

    private String mImages;
    private String mTitle;

    private ImageView[] img = new ImageView[21];
    private TextView textTitle;
    ProgressDialog pd;

    private int mlength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);

        mTitle = getIntent().getStringExtra(TITLE);
        textTitle = (TextView) findViewById(R.id.title_text_grid);
        textTitle.setText(mTitle);

        img[0] = (ImageView) findViewById(R.id.img0);
        img[1] = (ImageView) findViewById(R.id.img1);
        img[2] = (ImageView) findViewById(R.id.img2);
        img[3] = (ImageView) findViewById(R.id.img3);
        img[4] = (ImageView) findViewById(R.id.img4);
        img[5] = (ImageView) findViewById(R.id.img5);
        img[6] = (ImageView) findViewById(R.id.img6);
        img[7] = (ImageView) findViewById(R.id.img7);
        img[8] = (ImageView) findViewById(R.id.img8);
        img[9] = (ImageView) findViewById(R.id.img9);
        img[10] = (ImageView) findViewById(R.id.img10);
        img[11] = (ImageView) findViewById(R.id.img11);
        img[12] = (ImageView) findViewById(R.id.img12);
        img[13] = (ImageView) findViewById(R.id.img13);
        img[14] = (ImageView) findViewById(R.id.img14);
        img[15] = (ImageView) findViewById(R.id.img15);
        img[16] = (ImageView) findViewById(R.id.img16);
        img[17] = (ImageView) findViewById(R.id.img17);
        img[18] = (ImageView) findViewById(R.id.img18);
        img[19] = (ImageView) findViewById(R.id.img19);
        img[20] = (ImageView) findViewById(R.id.img20);


        mImages = getIntent().getStringExtra(IMAGES);
        String delims = "[, ]+";
        String[] tokens = mImages.split(delims);
        mlength = tokens.length;
        new GetXMLTask().execute(tokens);

    }

    private class GetXMLTask extends AsyncTask<String, Void, ArrayList<Bitmap>> {
        @Override
        protected ArrayList<Bitmap> doInBackground(String... urls) {
            ArrayList<Bitmap> map = new ArrayList<Bitmap>();
            for(int i=0;i<mlength;i++){
                map.add(downloadImage(urls[i]));
            }
            return map;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pd = ProgressDialog.show(Grid.this, "Please wait", "Downloading content", false, true);
        }

        @Override
        protected void onPostExecute(ArrayList<Bitmap> result) {
            for (int i=0; i<mlength; i++) {
                img[i].setImageBitmap(result.get(i));
            }
            pd.dismiss();
        }

        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }
}
