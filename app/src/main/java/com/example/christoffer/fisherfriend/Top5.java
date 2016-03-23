package com.example.christoffer.fisherfriend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Top5 extends AppCompatActivity {

    TextView text;
    TextView result;
    DatabaseHandler handler;
    String sorted;
    String summ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top5);

        handler = new DatabaseHandler(this);
        text = (TextView) findViewById(R.id.textViewTop5);
        result =(TextView) findViewById(R.id.textViewResult);

        sorted = handler.getSort();
        summ = handler.getSum();
        text.setText(sorted);
        result.setText(summ);
    }




}
