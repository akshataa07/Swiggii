package com.example.dell.lunchbox2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
RadioButton rdbtn1,rdbtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        rdbtn1=findViewById(R.id.customerrdbtn);
        rdbtn2=findViewById(R.id.providerrdbtn);

        rdbtn1.setOnClickListener(this);
        rdbtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==rdbtn1.getId())
        {
            Intent i=new Intent(getApplicationContext(),CustomerRegistration1Activity.class);
            startActivity(i);
        }
        else
        {
            Intent i=new Intent(getApplicationContext(),TiffinProviderRegistration1Activity.class);
            startActivity(i);
        }
    }
}
