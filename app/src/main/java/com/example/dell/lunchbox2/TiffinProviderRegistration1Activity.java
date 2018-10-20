package com.example.dell.lunchbox2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class TiffinProviderRegistration1Activity extends AppCompatActivity implements View.OnClickListener{
    Button tiffinProv_reg1button;
    EditText tiffinServicenm,tiffinProvfnm,tiffinProvlnm,tiffinProvmnm,tiffinProvaddr,tiffinProvmob1,tiffinProvmob2,tiffinProvemail;
    String tiffp_ownernm,tiffp_fnm,tiffp_mnm,tiffp_lnm,tiffp_addr,tiffp_mob1,tiffp_mob2,tiffp_email;

    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiffin_provider_registration1);
        
        tiffinServicenm=findViewById(R.id.tiffinservicename);
        tiffinProvfnm=findViewById(R.id.ownerfnm);
        tiffinProvmnm=findViewById(R.id.ownermnm);
        tiffinProvlnm=findViewById(R.id.ownerlnm);
        tiffinProvaddr=findViewById(R.id.tiffpaddr);
        tiffinProvmob1=findViewById(R.id.tiffpmob1);
        tiffinProvmob2=findViewById(R.id.tiffpmob2);
        tiffinProvemail=findViewById(R.id.tiffpemail);
        tiffinProv_reg1button=findViewById(R.id.tiffp_reg1_button);
        
        tiffinProv_reg1button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==tiffinProv_reg1button.getId())
        {
            tiffp_ownernm=tiffinServicenm.getText().toString();
            tiffp_fnm=tiffinProvfnm.getText().toString();
            tiffp_mnm=tiffinProvmnm.getText().toString();
            tiffp_lnm=tiffinProvlnm.getText().toString();
            tiffp_addr=tiffinProvaddr.getText().toString();
            tiffp_mob1=tiffinProvmob1.getText().toString();
            tiffp_mob2=tiffinProvmob2.getText().toString();
            tiffp_email=tiffinProvemail.getText().toString();


            if((tiffinServicenm.getText().toString().matches(""))||(tiffinProvfnm.getText().toString().matches(""))||(tiffinProvmnm.getText().toString().matches(""))||(tiffinProvlnm.getText().toString().matches(""))||
                    (tiffinProvaddr.getText().toString().matches(""))||(tiffinProvmob1.getText().toString().matches(""))||(tiffinProvemail.getText().toString().matches(""))){
                Toast.makeText(this,"Fields with * are compulsary.",Toast.LENGTH_SHORT).show();
            }

            else if(!checkValidEmail(tiffinProvemail.getText().toString())){
                Toast.makeText(this,"Invalid email address.",Toast.LENGTH_SHORT).show();
            }
            else  {
                Bundle b= new Bundle();
                b.putString("tiffp_ownernm",tiffp_ownernm);
                b.putString("tiffp_fnm",tiffp_fnm);
                b.putString("tiffp_mnm",tiffp_mnm);
                b.putString("tiffp_lnm",tiffp_lnm);
                b.putString("tiffp_addr",tiffp_addr);
                b.putString("tiffp_mob1",tiffp_mob1);
                b.putString("tiffp_mob2",tiffp_mob2);
                b.putString("tiffp_email",tiffp_email);

                Intent i = new Intent(getApplicationContext(), TiffinProviderRegistration2Activity.class);
                i.putExtras(b);
                startActivity(i);
            }
            //Intent i= new Intent(getApplicationContext(),TiffinProviderRegistration2Activity.class);
            //startActivity(i);
        }
    }

    public static boolean checkValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
