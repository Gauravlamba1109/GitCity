package com.example.githouse.controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import com.bumptech.glide.Glide;
import com.example.githouse.R;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity implements onCreateOptionsMenu {
    TextView Link , UserName;
    Toolbar mActionBarToolbar;
    ImageView imageView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.user_image_header);
        UserName = (TextView) findViewById(R.id.username);

        Link = (TextView) findViewById(R.id.Link);

        String username = getIntent().getExtras().getString("login");
        String avatarUrl = getIntent().getExtras().getString("avatar_url");
        String link = getIntent().getExtras().getString("html_url");

        Link.setText(link);
        Linkify.addLinks(Link,Linkify.WEB_URLS);

        UserName.setText(username);
        Glide.with(this)
                .load(avatarUrl)
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(imageView);

        getSupportActionBar().setTitle("Details Activity");
    }
    private Intent createShareForecastIntent(){
        String username = getIntent().getExtras().getString("login");
        String link = getIntent().getExtras().getString("html_url");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("say hello to my lil friend @"+ username+" , "+link)
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail , menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForecastIntent());
        return true;
    }
}
