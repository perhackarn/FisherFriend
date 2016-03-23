package com.example.christoffer.fisherfriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Spara extends AppCompatActivity {
    EditText art, langd, vikt, plats;
    DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spara);

        handler = new DatabaseHandler(this);
        art = (EditText) findViewById(R.id.editTextArt);
        langd = (EditText) findViewById(R.id.editTextLängd);
        vikt = (EditText) findViewById(R.id.editTextVikt);
        plats = (EditText) findViewById(R.id.editTextPlats);
    }

    public void spara(View view) {
        DatabaseHandler db = new DatabaseHandler(this);


        String ar = art.getText().toString();
        if(ar.isEmpty()){
            ar = "Fiskart Okänd";
        }
        String la = langd.getText().toString();
        if(la.isEmpty()){
            la = "0";
        }
        String vi = vikt.getText().toString();
        if(vi.isEmpty()){
            vi = "0";
        }
        String pl = plats.getText().toString();
        if(pl.isEmpty()){
            pl = "Plats Okänd";
        }
        String da = new SimpleDateFormat("yyyy-MM-dd.   HH:mm:ss", Locale.getDefault()).format(new Date());

        db.addFisk(ar, la, vi, pl, da);

        art.setText("");
        langd.setText("");
        vikt.setText("");
        plats.setText("");

        toast("Fisk sparad");
        openMain();


    }

    public void openTop5(View view){
        Intent start = new Intent(this, Top5.class);
        startActivity(start);
    }

    public void openMain(){
        Intent start = new Intent(this, MainActivity.class);
        startActivity(start);
    }

    public void delete(View view){
        handler.deletall();
        openMain();
    }

    private void toast( String text )
    {
        Toast.makeText( Spara.this,
                String.format(text), Toast.LENGTH_SHORT )
                .show();
    }
}
