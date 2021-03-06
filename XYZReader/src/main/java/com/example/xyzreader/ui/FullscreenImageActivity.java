package com.example.xyzreader.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.xyzreader.R;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

/**
 * Fullscreen actibity to show full sized image
 */
public class FullscreenImageActivity extends AppCompatActivity {
    private ImageViewTouch mContentView;

    public static final String PHOTO_URL = "photoUrl";
    private String photoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen_image);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mContentView = (it.sephiroth.android.library.imagezoom.ImageViewTouch) findViewById(R.id.image_full);

        Intent intent = getIntent();
        photoURL = intent.getStringExtra(PHOTO_URL);

        ImageLoaderHelper.getInstance(this).getImageLoader()
                .get(photoURL, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                        Bitmap bitmap = imageContainer.getBitmap();
                        if (bitmap != null) {
                            mContentView.setImageBitmap(imageContainer.getBitmap());
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
