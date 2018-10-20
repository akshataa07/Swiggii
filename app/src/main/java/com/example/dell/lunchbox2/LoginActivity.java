package com.example.dell.lunchbox2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button login_button;
    TextView createAccount;
    EditText username,password;
    Spinner user_types_spinner;
    String usernm;

    SharedPreferences sharedPreferences;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createAccount=findViewById(R.id.createAccount);
        login_button=findViewById(R.id.loginButton);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        user_types_spinner=findViewById(R.id.spinner);

        sharedPreferences=getSharedPreferences("LunchBoxPrefs", Context.MODE_PRIVATE);

        createAccount.setOnClickListener(this);
        login_button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==createAccount.getId())
        {
            Intent i=new Intent(getApplicationContext(),UserActivity.class);
            startActivity(i);
        }
        else if(view.getId()==login_button.getId())
        {

            if (username.getText().toString().matches(""))
            {
                Toast.makeText(getApplicationContext(),"Please Enter Username.",Toast.LENGTH_SHORT).show();
            }
            else if (password.getText().toString().matches(""))
            {
                Toast.makeText(getApplicationContext(),"Please Enter Password.",Toast.LENGTH_SHORT).show();
            }
            else {

                usernm=username.getText().toString();

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("usernm",usernm);
                editor.apply();

                //final Bundle b = new Bundle();
                //b.putString("usernm",usernm);

                if (user_types_spinner.getSelectedItem().toString().equals("Login as")) {
                    Toast.makeText(getApplicationContext(), "Select Customer or Tiffin Provider as a Login.", Toast.LENGTH_SHORT).show();
                }
                else if (user_types_spinner.getSelectedItem().toString().equals("Customer")) {
                    //add code to handle Customer database and fetch username and password

                    databaseReference= FirebaseDatabase.getInstance().getReference("customers");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot custSnapshot : dataSnapshot.getChildren())
                            {
                                Customer customer=custSnapshot.getValue(Customer.class);
                                if((customer.getEmail().matches(username.getText().toString())) && (customer.getPasswd().matches(password.getText().toString())))
                                {
                                    Intent i=new Intent(getApplicationContext(),CustomerHomeActivity.class);
                                    //i.putExtras(b);
                                    startActivity(i);
                                }
                                else {
                                    if(!(customer.getEmail().matches(username.getText().toString())))
                                        Toast.makeText(getApplicationContext(), "Username Password doesnot match try again!", Toast.LENGTH_SHORT).show();
                                    else if(!(customer.getEmail().matches(username.getText().toString())))
                                        Toast.makeText(getApplicationContext(), "Password doesnot match try again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

//                  Intent i=new Intent(getApplicationContext(),CustomerHomeActivity.class);
//                  startActivity(i);
                } else if (user_types_spinner.getSelectedItem().toString().equals("Tiffin Provider")) {
//add code to handle Provider's database and fetch username and password

                databaseReference=FirebaseDatabase.getInstance().getReference("providers");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot providerSnapshot : dataSnapshot.getChildren())
                        {
                            Provider provider=providerSnapshot.getValue(Provider.class);
                            if((provider.getEmail().matches(username.getText().toString()))&&(provider.getPasswd().matches(password.getText().toString())) )
                            {
                                Toast.makeText(getApplicationContext(),"starting activity!",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),ProviderHomeActivity.class);
                                //i.putExtras(b);
                                startActivity(i);

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Username or password does not match..please try again!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
//                    Intent i=new Intent(getApplicationContext(),ProviderHomeActivity.class);
//                    startActivity(i);
                }

            }
            //Intent i=new Intent(getApplicationContext(),CustomerHomeActivity.class);
            //startActivity(i);
        }

    }
}
