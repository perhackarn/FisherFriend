package com.example.christoffer.fisherfriend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler handler = new DatabaseHandler(this);

    ListView list;



    class SingelRow{
        String id;
        String art;
        String langd;
        String vikt;
        String plats;
        String datum;


        public SingelRow(String s, String s1, String s2, String s3, String s4, String s5) {
            this.id=s;
            this.art=s1;
            this.langd=s2;
            this.vikt=s3;
            this.plats=s4;
            this.datum=s5;
        }
    }

    class MinAdapter extends BaseAdapter {

        ArrayList<SingelRow> list;
        Context context;
        MinAdapter(Context c){
            context = c;
            list = new ArrayList<SingelRow>();
            ArrayList<String> ID = new ArrayList<>(handler.getid());
            ArrayList<String> ART = new ArrayList<>(handler.getArt());
            ArrayList<String> LANGD = new ArrayList<>(handler.getLangd());
            ArrayList<String> VIKT = new ArrayList<>(handler.getVikt());
            ArrayList<String> PLATS = new ArrayList<>(handler.getPlats());
            ArrayList<String> DATUM = new ArrayList<>(handler.getDatum());
            for(int i=0;i<ID.size();i++){
                list.add(new SingelRow(ID.get(i), ART.get(i), LANGD.get(i)+"cm", VIKT.get(i)+"g", PLATS.get(i), DATUM.get(i)));
            }
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.singelrow,parent,false);

            TextView id = (TextView) row.findViewById(R.id.textViewId1);
            TextView art = (TextView) row.findViewById(R.id.textViewArt1);
            TextView langd = (TextView) row.findViewById(R.id.textViewLangd1);
            TextView vikt = (TextView) row.findViewById(R.id.textViewVikt1);
            TextView plats = (TextView) row.findViewById(R.id.textViewPlats1);
            TextView datum = (TextView) row.findViewById(R.id.textViewDatum1);

            SingelRow temp=list.get(position);

            id.setText(temp.id);
            art.setText(temp.art);
            langd.setText(temp.langd);
            vikt.setText(temp.vikt);
            plats.setText(temp.plats);
            datum.setText(temp.datum);

            return row;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        populate();

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view.findViewById(R.id.textViewId1 );
                String s = textView.getText().toString();
                long i = Long.valueOf(s);
                deleteRow(i);
                toast("Fish Nr " + i + " Deleted!" );


                populate();
                return false;
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSpara();
            }
        });
    }

    private void populate(){
        list=(ListView)findViewById(R.id.listView);
        list.setAdapter(new MinAdapter(this));
    }


    private void openSpara() {
        Intent start = new Intent(this, Spara.class);
        startActivity(start);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toast( String text )
    {
        Toast.makeText( MainActivity.this,
                String.format( "Item clicked: %s", text ), Toast.LENGTH_SHORT )
                .show();
    }

    public void deleteRow(long id){
        handler.deleteRow(id);
    }




}
