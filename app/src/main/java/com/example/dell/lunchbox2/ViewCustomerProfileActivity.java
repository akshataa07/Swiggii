package com.example.dell.lunchbox2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewCustomerProfileActivity extends AppCompatActivity {

    String fnm,mnm,lnm,addr,mob1,mob2,email;
    TextView custfnm,custmnm,custlnm,custaddr,custmob1,custmob2,custemail;

    DatabaseReference databaseCust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_profile);

        custfnm=findViewById(R.id.view_cust_first);
        custmnm=findViewById(R.id.view_cust_middle);
        custlnm=findViewById(R.id.view_cust_last);
        custaddr=findViewById(R.id.view_cust_addr);
        custmob1=findViewById(R.id.view_cust_mob1);
        custmob2=findViewById(R.id.view_cust_mob2);
        custemail=findViewById(R.id.view_cust_email);

        databaseCust= FirebaseDatabase.getInstance().getReference("customers");

        showProfile();

    }

    private void showProfile() {

        databaseCust.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
