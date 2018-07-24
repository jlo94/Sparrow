package com.banregio.sparrow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private TextView MensajeTextView;
    private EditText MensajeEditText;
    private Button  BtnAceptar;


    DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
    DatabaseReference mensajeRef=ref.child("Mensaje");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MensajeTextView=(TextView)findViewById(R.id.mensajeTextView);
        MensajeEditText=(EditText)findViewById(R.id.mensajeEditText);
        BtnAceptar=(Button)findViewById(R.id.btnAceptar);



    }

    @Override

    public void onStart(){

        super.onStart();
        //método oyente

        mensajeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue(String.class);
                MensajeTextView.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError){



            }


        });



    }




    public void Modificar(View view){


        String mensaje=MensajeEditText.getText().toString();

        mensajeRef.setValue(mensaje);


        MensajeEditText.setText("");

        //  MensajeEditText.setText(ref.getDatabase().toString());


    }

}
