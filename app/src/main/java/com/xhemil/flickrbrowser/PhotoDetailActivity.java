package com.xhemil.flickrbrowser;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoDetailActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        activateToolbar(true);
        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra(PHOTO_TRANSFER);
        if (photo != null){

            TextView photoTitle = findViewById(R.id.photo_title);
//            photoTitle.setText("Title: " + photo.getTitle());
            Resources resources = getResources();
            String text = resources.getString(R.string.photo_title_text, photo.getTitle());
            photoTitle.setText(text);

            TextView photoTags = findViewById(R.id.photo_tags);
            String tags = resources.getString(R.string.photo_title_text, photo.getTags());
            photoTags.setText(tags);
//            photoTags.setText(getString(R.string.tags) + photo.getTags());


            TextView photoAuthor = findViewById(R.id.photo_author);
            photoAuthor.setText(getString(R.string.author) + photo.getAuthor());

            ImageView photoImage = findViewById(R.id.photo_image);

            Picasso.get().load(photo.getLink())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(photoImage);
        }

    }

}
