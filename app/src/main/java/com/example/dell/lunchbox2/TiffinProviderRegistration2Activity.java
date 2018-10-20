package com.example.dell.lunchbox2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TiffinProviderRegistration2Activity extends AppCompatActivity implements View.OnClickListener{
    Button tiffp_reg2button;
    RadioGroup tiffpcategorygrp;
    RadioButton veg,nonveg,both,category;
    TextView tiffinProvusernm;
    EditText tiffinProvpassword,tiffinProvconfirm_password;
    String tiffp_id,tiffpcategory,tiffpveg,tiffpnonveg,tiffpboth,tiffp_ownernm,tiffp_fnm,tiffp_mnm,tiffp_lnm,tiffp_addr,tiffp_mob1,tiffp_mob2,tiffp_email,tiffp_usernm,tiffp_psswd,tiffp_con_psswd;

    DatabaseReference databaseProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiffin_provider_registration2);
        tiffp_reg2button = findViewById(R.id.tiffp_reg2_button);
        tiffinProvusernm=findViewById(R.id.tiffpusernm);
        tiffinProvpassword=findViewById(R.id.tiffppassword);
        tiffinProvconfirm_password=findViewById(R.id.tiffpconfirm_password);
        veg=findViewById(R.id.tiffpveg);
        nonveg=findViewById(R.id.tiffpnonveg);
        both=findViewById(R.id.tiffpboth);
        tiffpcategorygrp=findViewById(R.id.tiffpcategorygrp);

        Bundle b=getIntent().getExtras();

        databaseProvider= FirebaseDatabase.getInstance().getReference("providers");

        tiffp_ownernm=b.getString("tiffp_ownernm");
        tiffp_fnm=b.getString("tiffp_fnm");
        tiffp_mnm=b.getString("tiffp_mnm");
        tiffp_lnm=b.getString("tiffp_lnm");
        tiffp_addr=b.getString("tiffp_addr");
        tiffp_mob1=b.getString("tiffp_mob1");
        tiffp_mob2=b.getString("tiffp_mob2");
        tiffp_email=b.getString("tiffp_email");

        tiffinProvusernm.setText(tiffp_email);

        veg.setOnClickListener(this);
        nonveg.setOnClickListener(this);
        both.setOnClickListener(this);
        tiffp_reg2button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (tiffp_reg2button.getId() == view.getId()) {

            int selectedId=tiffpcategorygrp.getCheckedRadioButtonId();
            category=findViewById(selectedId);
            tiffpcategory=category.getText().toString();

            tiffp_usernm = tiffinProvusernm.getText().toString();
            tiffp_psswd = tiffinProvpassword.getText().toString();
            tiffp_con_psswd = tiffinProvconfirm_password.getText().toString();


            if (tiffp_reg2button.getId() == view.getId()) {
                if ((tiffinProvusernm.getText().toString().matches("")) || (tiffinProvpassword.getText().toString().matches("")) || (tiffinProvconfirm_password.getText().toString().matches(""))) {
                    Toast.makeText(this, "Fields with * are compulsary.", Toast.LENGTH_SHORT).show();
                } else if (!checkStrongNess(tiffinProvpassword.getText().toString())) {
                    Toast.makeText(this, "Password is not Strong , Password should contain atleast one special character and digit.", Toast.LENGTH_SHORT).show();
                } else if (tiffinProvpassword.getText().toString().equals(tiffinProvconfirm_password.getText().toString())) {

                    ////code to add contents to tiffinProv database
                    addProvider(tiffp_ownernm,tiffp_fnm,tiffp_mnm,tiffp_lnm,tiffp_addr,tiffp_mob1,tiffp_mob2,tiffp_email,tiffp_psswd,tiffpcategory);

                    Toast.makeText(this, "Account Created Successfully....Speciality : "+category.getText(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(this, "password does not match.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



    boolean checkStrongNess(String input)
    {
        int n = input.length();

        // Checking lower alphabet in string
        boolean hasLower = false, hasUpper = false;
        boolean hasDigit = false, specialChar = false;
        String normalChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ";

        for (int i = 0; i < n; i++) {
            if (Character.isLowerCase(input.charAt(i)))
                hasLower = true;
            if (Character.isUpperCase(input.charAt(i)))
                hasUpper = true;
            if (Character.isDigit(input.charAt(i)))
                hasDigit = true;
            Pattern p=Pattern.compile("[^A-Za-z0-9]");
            Matcher m=p.matcher(input);
            boolean b=m.find();
            if (b==true)
                specialChar = true;
            //     size_t special = input.find_first_not_of(normalChars);
            //     if (special != String::npos)
            //         specialChar = true;
        }
        // Strength of password
        //  cout << "Strength of password:-";
        if (hasLower && hasUpper && hasDigit && specialChar && (n >= 8))
            return true;
        else if ((hasLower || hasUpper) && specialChar && (n >= 6))
            return true;
        else
            return false;
    }

    private void addProvider(String tiffp_ownernm, String tiffp_fnm, String tiffp_mnm, String tiffp_lnm, String tiffp_addr, String tiffp_mob1, String tiffp_mob2, String tiffp_email, String tiffp_psswd, String tiffpcategory) {
        tiffp_id=databaseProvider.push().getKey();
        Provider provider=new Provider(tiffp_id,tiffp_ownernm,tiffp_fnm,tiffp_mnm,tiffp_lnm,tiffp_addr,tiffp_mob1,tiffp_mob2,tiffp_email,tiffp_psswd,tiffpcategory);
        databaseProvider.child(tiffp_id).setValue(provider);
        Toast.makeText(getApplicationContext(),"Provider added",Toast.LENGTH_SHORT).show();
    }

}
