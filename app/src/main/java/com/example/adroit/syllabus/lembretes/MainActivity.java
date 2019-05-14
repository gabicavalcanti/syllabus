package com.example.adroit.myapplication.lembretes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.example.adroit.myapplication.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    private Button btndata;
    private Button btnhora;

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText txtimput;
    private EditText txtimput2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lembrete);
        ListView listView= (ListView) findViewById(R.id.listv);
        String[] eventos= {
                "Estudar Java" +
                        "\n13 de jun de 2018 5:00:00 PM",
                "Matéria BD " +
                        "\n6 de jun de 2018 7:30:00 PM"};
        arrayList= new ArrayList<>(Arrays.asList(eventos));
        adapter= new ArrayAdapter<String>(this,R.layout.lista_eventos,R.id.txteventos,arrayList);
        listView.setAdapter(adapter);

        txtimput=(EditText) findViewById(R.id.txtimput);
        txtimput2=(EditText) findViewById(R.id.txtimput2);

        Button btadd =(Button)findViewById(R.id.btadd);
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEvento=txtimput.getText().toString();
                String newDH=txtimput2.getText().toString();
                arrayList.add(newEvento);
                arrayList.add(newDH);
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        btndata = (Button) findViewById(R.id.btndata);
        btnhora = (Button) findViewById(R.id.btnhora);

        btndata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });

        btnhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });

        updateTextLabel();
    }

    private void updateDate(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTime(){
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            updateTextLabel();
        }
    };

    private void updateTextLabel(){
        txtimput2.setText(formatDateTime.format(dateTime.getTime()));
    }
}
