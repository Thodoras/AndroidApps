package thodoras.escapeguide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thodoras.escapeguide.R;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by thodoras on 9/19/14.
 */
public class SightsAdapter extends ArrayAdapter<HelperSights> {

    ArrayList<HelperSights> ArrayListSights;
    int Resource;
    Context context;
    LayoutInflater vi;

    public SightsAdapter(Context context, int resource, ArrayList<HelperSights> objects) {
        super(context, resource, objects);

        ArrayListSights = objects;
        Resource = resource;
        this.context = context;
        vi = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView = vi.inflate(Resource, null);
            holder = new ViewHolder();
            holder.sImage = (ImageView)convertView.findViewById(R.id.sImage);
            holder.sTitle = (TextView)convertView.findViewById(R.id.sTitle);
            holder.sLocation = (TextView)convertView.findViewById(R.id.sLocation);
            holder.sImageCount = (TextView)convertView.findViewById(R.id.image_count);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        new DownloadImageTask(holder.sImage).execute(ArrayListSights.get(position).getImage());
        holder.sTitle.setText(ArrayListSights.get(position).getTitle());
        holder.sLocation.setText(ArrayListSights.get(position).getLocation());
        holder.sImageCount.setText(ArrayListSights.get(position).getCountImages());

        return convertView;

    }

    static class ViewHolder{

        public ImageView sImage;
        public TextView sTitle;
        public TextView sLocation;
        public TextView sImageCount;

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }
}
