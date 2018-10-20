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

public class ProviderProfileFragment1 extends Fragment {

    String usernm,fnm,mnm,lnm,addr,mob1,mob2,email;
    TextView tiffpservicenm,tiffpfnm,tiffpmnm,tiffplnm,tiffpaddr,tiffpmob1,tiffpmob2,tiffpemail;

    SharedPreferences sharedPreferences;

    DatabaseReference databaseProvider;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view=inflater.inflate(R.layout.activity_provider_profile_fragment1, container, false);

        tiffpservicenm=view.findViewById(R.id.view_tiffp_servicename);
        tiffpfnm=view.findViewById(R.id.view_tiffp_first);
        tiffpmnm=view.findViewById(R.id.view_tiffp_middle);
        tiffplnm=view.findViewById(R.id.view_tiffp_last);
        tiffpaddr=view.findViewById(R.id.view_tiffp_addr);
        tiffpmob1=view.findViewById(R.id.view_tiffp_mob1);
        tiffpmob2=view.findViewById(R.id.view_tiffp_mob2);
        tiffpemail=view.findViewById(R.id.view_tiffp_email);

        databaseProvider= FirebaseDatabase.getInstance().getReference("providers");

        sharedPreferences=this.getActivity().getSharedPreferences("LunchBoxPrefs", Context.MODE_PRIVATE);
        usernm=sharedPreferences.getString("usernm","@@@");

        showProviderProfile();

        Toast.makeText(getContext(),"User  name : "+usernm,Toast.LENGTH_SHORT).show();

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Provider Profile");
    }

    private void showProviderProfile() {

        databaseProvider.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot tiffpSnapShot : dataSnapshot.getChildren())
                {
                    Provider provider = tiffpSnapShot.getValue(Provider.class);
                    if(provider.getEmail().matches(usernm))
                    {
                        Toast.makeText(getContext(),"@@@@@!!!!!@@@@",Toast.LENGTH_SHORT).show();

                        tiffpfnm.setText(provider.getFnm());
                        tiffpmnm.setText(provider.getMnm());
                        tiffplnm.setText(provider.getLnm());
                        tiffpservicenm.setText(provider.getServicenm());
                        tiffpaddr.setText(provider.getAddr());
                        tiffpmob1.append(provider.getMob1());
                        tiffpmob2.append(provider.getMob2());
                        tiffpemail.setText(usernm);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
