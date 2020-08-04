package com.fypic.imageclassification;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ListActivity extends AppCompatActivity {

    DatabaseHelper db;
    private ListView lv;
    int material_id;
    int object_id;
    String dateStr;
    Button allBtn,paperBtn, metalBtn, plasticBtn, wasteBtn, summaryBtn, dateBtn;
    ArrayList<Object> dateObj = new ArrayList<>();
    TextView tv10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lv = (ListView) findViewById(R.id.listView);
        db = new DatabaseHelper(this);
        allBtn = findViewById(R.id.allBtn);
        paperBtn = findViewById(R.id.paperBtn);
        metalBtn = findViewById(R.id.metalBtn);
        plasticBtn = findViewById(R.id.plasticBtn);
        wasteBtn = findViewById(R.id.wasteBtn);
        summaryBtn = findViewById(R.id.summarybutton);
        dateBtn = findViewById(R.id.dateBtn);
        tv10 = findViewById(R.id.textView10);

        TextView emptyText = (TextView)findViewById(android.R.id.empty);
        lv.setEmptyView(emptyText);

        Cursor data = db.getData();
        Cursor alldata = db.getAllData();
        Cursor paperdata = db.getPaperData();
        Cursor metaldata = db.getMetalData();
        Cursor plasticdata = db.getPlasticData();
        Cursor wastedata = db.getWasteData();

        ArrayList<Object> listObj = new ArrayList<>();
        ArrayList<Object> allObj = new ArrayList<>();
        ArrayList<Object> paperObj = new ArrayList<>();
        ArrayList<Object> metalObj = new ArrayList<>();
        ArrayList<Object> plasticObj = new ArrayList<>();
        ArrayList<Object> wasteObj = new ArrayList<>();

        while (data.moveToNext()) {
            material_id = data.getInt(1);
            object_id = data.getInt(0);

            Object obj = new Object(object_id, material_id);
            listObj.add(obj);
        }

        while (alldata.moveToNext()) {
            material_id = alldata.getInt(1);
            object_id = alldata.getInt(0);

            Object obj = new Object(object_id, material_id);
            allObj.add(obj);
        }

        while (paperdata.moveToNext()) {
            material_id = paperdata.getInt(1);
            object_id = paperdata.getInt(0);

            Object obj = new Object(object_id, material_id);
            paperObj.add(obj);
        }

        while (metaldata.moveToNext()) {
            material_id = metaldata.getInt(1);
            object_id = metaldata.getInt(0);

            Object obj = new Object(object_id, material_id);
            metalObj.add(obj);
        }

        while (plasticdata.moveToNext()) {
            material_id = plasticdata.getInt(1);
            object_id = plasticdata.getInt(0);

            Object obj = new Object(object_id, material_id);
            plasticObj.add(obj);
        }

        while (wastedata.moveToNext()) {
            material_id = wastedata.getInt(1);
            object_id = wastedata.getInt(0);

            Object obj = new Object(object_id, material_id);
            wasteObj.add(obj);
        }

        Intent intent = getIntent();
        dateStr = intent.getStringExtra("datestr");

        ObjListAdapter adapter;

        if (dateStr == null) {
            adapter = new ObjListAdapter(this, R.layout.adap_layout, listObj);
        }

        else {
            Cursor datedata = db.getDataByDate(dateStr);
            if (datedata == null) {
                return;
            }

            else {
                while (datedata.moveToNext()) {
                    material_id = datedata.getInt(1);
                    object_id = datedata.getInt(0);

                    Object obj = new Object(object_id, material_id);
                    dateObj.add(obj);
                }
            }

            adapter = new ObjListAdapter(this, R.layout.adap_layout, dateObj);
        }

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent editScreenIntent = new Intent(ListActivity.this, EditActivity.class);
                editScreenIntent.putExtra("id", listObj.get(position).getId());
                editScreenIntent.putExtra("name", listObj.get(position).getMat());
                startActivity(editScreenIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


        allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                adapter.addAll(allObj);
                adapter.notifyDataSetChanged();
            }
        });

        paperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                adapter.addAll(paperObj);
                adapter.notifyDataSetChanged();
            }
        });

        metalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                adapter.addAll(metalObj);
                adapter.notifyDataSetChanged();
            }
        });

        plasticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                adapter.addAll(plasticObj);
                adapter.notifyDataSetChanged();
            }
        });

        wasteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                adapter.addAll(wasteObj);
                adapter.notifyDataSetChanged();
            }
        });

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cl = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        cl.set(Calendar.YEAR, year);
                        cl.set(Calendar.MONTH, month);
                        cl.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN).format(cl.getTime());
                        String dateStr = currentDateString.replace("/","-");
                        Intent resultIntent = new Intent(ListActivity.this, ListActivity.class);
                        resultIntent.putExtra("datestr", dateStr);
                        startActivity(resultIntent);
                        overridePendingTransition(0,0);
                    }
                };

                //Create the DatePicker dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(ListActivity.this,myDateListener,cl.get(Calendar.YEAR),cl.get(Calendar.MONTH),cl.get(Calendar.DAY_OF_MONTH));
                myDateDialog.show();
            }
        });

        summaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, SummaryActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Intent intent=new Intent(ListActivity.this, MainActivityLogged.class);
        startActivity(intent);
    }
}