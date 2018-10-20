package com.example.dell.lunchbox2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.zip.Inflater;

public class ProfileFragment1Activity extends Fragment {

    String usernm,fnm,mnm,lnm,addr,mob1,mob2,email;
    TextView custfnm,custmnm,custlnm,custaddr,custmob1,custmob2,custemail;

    SharedPreferences sharedPreferences;

    DatabaseReference databaseCust;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view=inflater.inflate(R.layout.activity_profile_fragment1, container, false);
        //return inflater.inflate(R.layout.activity_profile_fragment1, container, false);

        custfnm=view.findViewById(R.id.view_cust_first);
        custmnm=view.findViewById(R.id.view_cust_middle);
        custlnm=view.findViewById(R.id.view_cust_last);
        custaddr=view.findViewById(R.id.view_cust_addr);
        custmob1=view.findViewById(R.id.view_cust_mob1);
        custmob2=view.findViewById(R.id.view_cust_mob2);
        custemail=view.findViewById(R.id.view_cust_email);

        databaseCust= FirebaseDatabase.getInstance().getReference("customers");

        sharedPreferences=this.getActivity().getSharedPreferences("LunchBoxPrefs", Context.MODE_PRIVATE);
        usernm=sharedPreferences.getString("usernm","@@@");

        showCustomerProfile();

        Toast.makeText(getContext(),"User  name : "+usernm,Toast.LENGTH_SHORT).show();
        return view;
    }

    private void showCustomerProfile() {

        databaseCust.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot custSnapShot : dataSnapshot.getChildren())
                {
                    Customer customer = custSnapShot.getValue(Customer.class);
                    if(customer.getEmail().matches(usernm))
                    {
                        custfnm.setText(customer.getFnm());
                        custmnm.setText(customer.getMnm());
                        custlnm.setText(customer.getLnm());
                        custaddr.setText(customer.getAddr());
                        custmob1.append(customer.getMob1());
                        custmob2.append(customer.getMob2());
                        custemail.setText(usernm);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 2");
    }

}
