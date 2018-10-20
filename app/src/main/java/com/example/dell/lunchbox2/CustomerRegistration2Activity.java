package com.example.dell.lunchbox2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegistration2Activity extends AppCompatActivity implements View.OnClickListener {
    Button cust_reg2button;
    EditText customerusernm,customerpassword,customerconfirm_password;
    String cust_id,cust_fnm,cust_mnm,cust_lnm,cust_addr,cust_mob1,cust_mob2,cust_email,cust_usernm,cust_psswd,cust_con_psswd;

    DatabaseReference databaseCust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration2);
        cust_reg2button = findViewById(R.id.cust_reg2_button);
        customerusernm=findViewById(R.id.customerusernm);
        customerpassword=findViewById(R.id.customerpassword);
        customerconfirm_password=findViewById(R.id.customerconfirm_password);

        databaseCust= FirebaseDatabase.getInstance().getReference("customers");

        Bundle b=getIntent().getExtras();

        cust_fnm=b.getString("cust_fnm");
        cust_mnm=b.getString("cust_mnm");
        cust_lnm=b.getString("cust_lnm");
        cust_addr=b.getString("cust_addr");
        cust_mob1=b.getString("cust_mob1");
        cust_mob2=b.getString("cust_mob2");
        cust_email=b.getString("cust_email");

        cust_reg2button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        cust_usernm=customerusernm.getText().toString();
        cust_psswd=customerpassword.getText().toString();
        cust_con_psswd=customerconfirm_password.getText().toString();

        if (cust_reg2button.getId() == view.getId()) {
            if ((customerusernm.getText().toString().matches("")) || (customerpassword.getText().toString().matches("")) || (customerconfirm_password.getText().toString().matches(""))) {
                Toast.makeText(this, "Fields with * are compulsary.", Toast.LENGTH_SHORT).show();
            } else if (!checkStrongNess(customerpassword.getText().toString())) {
                Toast.makeText(this, "Password is not Strong , Password should contain atleast one special character and digit.", Toast.LENGTH_SHORT).show();
            } else if (customerpassword.getText().toString().equals(customerconfirm_password.getText().toString())) {

                ////code to add contents to customer database
                addCustomer(cust_fnm,cust_mnm,cust_lnm,cust_addr,cust_mob1,cust_mob2,cust_email,cust_psswd);
                Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(this, "password does not match.", Toast.LENGTH_SHORT).show();
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

    private void addCustomer(String fnm,String mnm,String lnm,String addr,String mob1,String mob2,String email,String passwd)
    {
        cust_id=databaseCust.push().getKey();
        Customer customer=new Customer(cust_id,fnm,mnm,lnm,addr,mob1,mob2,email,passwd);
        databaseCust.child(cust_id).setValue(customer);
        Toast.makeText(getApplicationContext(),"Customer added",Toast.LENGTH_SHORT).show();
    }
}
