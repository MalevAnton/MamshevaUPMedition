package com.example.malevup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    ImageView image;

    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tvNameProfile);

        tvName.setText(LoginActivity.User.getNickName());

        image = findViewById(R.id.avatar);

        new AdapterQuote.DownloadImageTask((ImageView) image).execute(LoginActivity.User.getAvatar());
    }

    public  void nextMenu(View view)
    {
        startActivity(new Intent(this, MenuActivity.class));
    }

    public  void nextHome(View view)
    {
        startActivity(new Intent(this, MainActivity2.class));
    }

    public void nextListen(View view)
    {
        startActivity(new Intent(this, MusicActivity.class));
    }

    public void nextLogin(View view)
    {
        startActivity(new Intent(this, LoginActivity.class));
    }
}