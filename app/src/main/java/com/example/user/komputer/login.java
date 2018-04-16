package com.example.user.komputer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button mainActivity = (Button) findViewById(R.id.btn_login);

        mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent utamaIntent = new Intent(login.this, MainActivity.class);

                // Start the new activity
                startActivity(utamaIntent);
            }
        });
//s
    }
}
