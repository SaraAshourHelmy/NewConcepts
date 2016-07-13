package com.sara.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.sara.newconcepts.R;

import java.util.ArrayList;

/**
 * Created by Bassem on 7/12/2016.
 */
public class CategoryAdapter extends ArrayAdapter<Category> {

    Context context;
    ArrayList<Category> lst_category;
    ImageLoader imageLoader;

    public CategoryAdapter(Context context, int layout,
                           ArrayList<Category> lst_category, ImageLoader imageLoader) {
        super(context, layout, lst_category);
        this.context = context;
        this.lst_category = lst_category;
        this.imageLoader = imageLoader;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_category, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_category_name = (TextView) convertView.findViewById(
                    R.id.tv_category_name);
            viewHolder.img_category = (NetworkImageView) convertView.findViewById(
                    R.id.img_category);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_category_name.setText(lst_category.get(position).getName());
        //imageLoader.get(lst_category.get(position).getImg_url(), ImageLoader.getImageListener(
          //      viewHolder.img_category, R.drawable.img, R.drawable.img));

        viewHolder.img_category.setImageUrl(lst_category.get(position).getImg_url()
                , imageLoader);

        return convertView;
    }

    static class ViewHolder {

        TextView tv_category_name;
        NetworkImageView img_category;

    }
}
