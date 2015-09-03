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
 * Created by thodoras on 9/25/14.
 */
public class RoutesAdapter extends ArrayAdapter<HelperRoutes> {

    ArrayList<HelperRoutes> ArrayListRoutes;
    int Resource;
    Context context;
    LayoutInflater vi;

    public RoutesAdapter(Context context, int resource, ArrayList<HelperRoutes> objects) {
        super(context, resource, objects);

        ArrayListRoutes = objects;
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
            holder.sDays = (TextView)convertView.findViewById(R.id.rDays);
            holder.sDistance = (TextView)convertView.findViewById(R.id.rDistance);
            holder.sDestination = (TextView)convertView.findViewById(R.id.rDestination);
            holder.sPointsOfInterest = (TextView)convertView.findViewById(R.id.rPointsOfInterest);
            holder.sCost = (TextView)convertView.findViewById(R.id.rCost);
            holder.sTopPlaces = (TextView)convertView.findViewById(R.id.rTopPlaces);
            holder.sDescription = (TextView)convertView.findViewById(R.id.rDescription);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        new DownloadImageTask(holder.sImage).execute(ArrayListRoutes.get(position).getImage());
        holder.sTitle.setText(ArrayListRoutes.get(position).getTitle());
        holder.sDays.setText(ArrayListRoutes.get(position).getDays());
        holder.sDistance.setText(ArrayListRoutes.get(position).getDistance());
        holder.sDestination.setText(ArrayListRoutes.get(position).getDestination());
        holder.sPointsOfInterest.setText(ArrayListRoutes.get(position).getPointsOfInterest());
        holder.sCost.setText(ArrayListRoutes.get(position).getCharge());
        holder.sTopPlaces.setText(ArrayListRoutes.get(position).getTopPlaces());
        holder.sDescription.setText(ArrayListRoutes.get(position).getDescription());



        return convertView;

    }

    static class ViewHolder{

        public ImageView sImage;
        public TextView sTitle;
        public TextView sDays;
        public TextView sDistance;
        public TextView sDestination;
        public TextView sPointsOfInterest;
        public TextView sCost;
        public TextView sTopPlaces;
        public TextView sDescription;

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
