package com.example.iteration1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button joinNowButton,loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        joinNowButton=findViewById(R.id.main_join_now_btn);
        loginButton=findViewById(R.id.main_login_btn);

        joinNowButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_join_now_btn:
                RegistrationPage();
                break;
            case R.id.main_login_btn:
                LoginPage();
                break;
        }
    }

    private void LoginPage() {
        Intent i=new Intent(MainActivity.this,loginActivity.class);
        startActivity(i);

    }

    private void RegistrationPage() {
        Intent i=new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(i);
    }
}

