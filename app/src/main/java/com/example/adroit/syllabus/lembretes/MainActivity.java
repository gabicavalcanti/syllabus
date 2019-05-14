package com.example.adroit.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irParaResumo (View view) {

        Intent intentr = new Intent(this, com.example.adroit.myapplication.resumo.MainActivity.class);
        startActivity(intentr);
    }

    public void irParaLembretes (View view) {

        Intent intentr = new Intent(this, com.example.adroit.myapplication.lembretes.MainActivity.class);
        startActivity(intentr);
    }
}
