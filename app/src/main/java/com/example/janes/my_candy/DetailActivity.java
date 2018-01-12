package com.example.janes.my_candy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = DetailActivity.this.getIntent();
        String candy_name="";
        String candy_image="";
        String candy_price="";
        String candy_desc="";

        if(intent !=null && intent.hasExtra("candy_name")) {
            candy_name=intent.getStringExtra("candy_name");
        }

        if(intent !=null && intent.hasExtra("candy_image")) {
            candy_image = intent.getStringExtra("candy_image");
        }
        if(intent !=null && intent.hasExtra("candy_price")) {
            candy_price = intent.getStringExtra("candy_price");
        }
        if(intent !=null && intent.hasExtra("candy_description")) {
            candy_desc=intent.getStringExtra("candy_description");
        }

        TextView textView = (TextView)this.findViewById(R.id.text_view_name);
        textView.setText(candy_name);

        Log.d("DetailActivity","Intent data: "+candy_image+", "+candy_price+", "+candy_desc);

    }
}
