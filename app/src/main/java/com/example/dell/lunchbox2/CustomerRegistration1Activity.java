package com.example.dell.lunchbox2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class CustomerRegistration1Activity extends AppCompatActivity implements View.OnClickListener{
    Button cust_reg1button;
    EditText customerfnm,customerlnm,customermnm,customeraddr,customermob1,customermob2,customeremail;
    String cust_fnm,cust_mnm,cust_lnm,cust_addr,cust_mob1,cust_mob2,cust_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration1);
        cust_reg1button=findViewById(R.id.cust_reg1_button);
        customerfnm=findViewById(R.id.customerfnm);
        customermnm=findViewById(R.id.customermnm);
        customerlnm=findViewById(R.id.customerlnm);
        customeraddr=findViewById(R.id.customeraddr);
        customermob1=findViewById(R.id.customermob1);
        customermob2=findViewById(R.id.customermob2);
        customeremail=findViewById(R.id.customeremail);

        cust_reg1button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==cust_reg1button.getId())
        {
            cust_fnm=customerfnm.getText().toString();
            cust_mnm=customermnm.getText().toString();
            cust_lnm=customerlnm.getText().toString();
            cust_addr=customeraddr.getText().toString();
            cust_mob1=customermob1.getText().toString();
            cust_mob2=customermob2.getText().toString();
            cust_email=customeremail.getText().toString();

            if((customerfnm.getText().toString().matches(""))||(customermnm.getText().toString().matches(""))||(customerlnm.getText().toString().matches(""))||
                    (customeraddr.getText().toString().matches(""))||(customermob1.getText().toString().matches(""))||(customeremail.getText().toString().matches(""))){
                Toast.makeText(this,"Fields with * are compulsary.",Toast.LENGTH_SHORT).show();
            }
            else if(!checkValidEmail(customeremail.getText().toString())){
                Toast.makeText(this,"Invalid email address.",Toast.LENGTH_SHORT).show();
            }
            else  {
                Bundle b= new Bundle();
                b.putString("cust_fnm",cust_fnm);
                b.putString("cust_mnm",cust_mnm);
                b.putString("cust_lnm",cust_lnm);
                b.putString("cust_addr",cust_addr);
                b.putString("cust_mob1",cust_mob1);
                b.putString("cust_mob2",cust_mob2);
                b.putString("cust_email",cust_email);
                Intent i = new Intent(getApplicationContext(), CustomerRegistration2Activity.class);
                i.putExtras(b);
                startActivity(i);
            }
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
