package com.example.jsonandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getIncomingIntent();

    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("username")){
            String username = getIntent().getStringExtra("username");
            TextView text_username = findViewById(R.id.text_username);
            text_username.setText(username);
        }
        if(getIntent().hasExtra("name")){
            String name = getIntent().getStringExtra("name");
            TextView text_name = findViewById(R.id.text_name);
            text_name.setText(name);
        }
        if(getIntent().hasExtra("phone")){
            String phone = getIntent().getStringExtra("phone");
            TextView text_phone = findViewById(R.id.text_phone);
            text_phone.setText(phone);
        }
        if(getIntent().hasExtra("address")){
            String address = getIntent().getStringExtra("address");
            TextView text_address = findViewById(R.id.text_address);
            text_address.setText(address);
        }
        if(getIntent().hasExtra("email")){
            String email = getIntent().getStringExtra("email");
            TextView text_email = findViewById(R.id.text_email);
            text_email.setText(email);
        }
        if(getIntent().hasExtra("company")){
            String company = getIntent().getStringExtra("company");
            TextView text_company = findViewById(R.id.text_company);
            text_company.setText(company);
        }
        if(getIntent().hasExtra("photo")){
            ImageView image_photo = findViewById(R.id.photo);
            Picasso.get().load("https://lebavui.github.io/" + getIntent().getStringExtra("photo")).placeholder(R.drawable.ic_baseline_account_circle_24).transform(new CropCircleTransformation()).into(image_photo);
        }
    }

}