package com.sara.Glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.sara.newconcepts.R;

public class GlideActivity extends AppCompatActivity {

    String img_url = "http://shaklakaklak.com/upload/images/icon01@2X.png";
    private ImageView img_glide;
    private String gif_url =
            "http://www.thisiscolossal.com/wp-content/uploads/2014/03/120430.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        //SetupTools();
        // SetupGif();
        // SetupGif();
        LoadGif();
    }

    private void LoadGif() {

        // asGif is very very slow but this class
        //  Glide.with(this).load(gif_url).asGif.into(img_view)  very very slow
        // GlideDrawableImageViewTarget good in time performance

        img_glide = (ImageView) findViewById(R.id.img_glide);
        GlideDrawableImageViewTarget imageViewTarget = new
                GlideDrawableImageViewTarget(img_glide);

        Glide.with(this).load(gif_url).placeholder(R.drawable.img)
                .into(imageViewTarget);
    }

    private void SetupGif() {

        img_glide = (ImageView) findViewById(R.id.img_glide);
        GlideDrawableImageViewTarget imageViewTarget = new
                GlideDrawableImageViewTarget(img_glide);
        Glide.with(this).load(R.drawable.test_gif).into(imageViewTarget);
    }

    private void SetupTools() {
        img_glide = (ImageView) findViewById(R.id.img_glide);
        img_glide.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        Glide.with(this).load(img_url).crossFade()
                .placeholder(R.drawable.img).error(R.drawable.img).into(img_glide);
    }
}
