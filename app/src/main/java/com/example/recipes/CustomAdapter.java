package com.example.recipes;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<HashMap<String,String>> {

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();

            Drawable d = Drawable.createFromStream(is, "any_image_name");
            return d;
        } catch (Exception e) {
            Log.i("TAG", ""+e);
            return null;
        }
    }


    public CustomAdapter(Context context, List<HashMap<String,String>> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.food_custom, parent, false);
        }

        TextView title = row.findViewById(R.id.foodTitle);
        TextView description = row.findViewById(R.id.foodDescription);
        ImageView photo = (ImageView)row.findViewById(R.id.foodImage);
        final HashMap<String,String> hm = getItem(position);

        String url = hm.get("ImagePath");
        Picasso.get().load(url).into(photo);
        title.setText(hm.get("Title"));
        description.setText(hm.get("Description"));

        return row;
    }

}