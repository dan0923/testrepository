package com.fypic.imageclassification;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {

    DatabaseHelper db;
    private ListView lv;
    int fl;
    int sl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lv = (ListView) findViewById(R.id.listView);
        db = new DatabaseHelper(this);

        Cursor data = db.getData();
        ArrayList<String> listData = new ArrayList<>();
        ArrayList<Object> listObj = new ArrayList<>();
        while(data.moveToNext()){
            fl = data.getInt(1);
            sl = data.getInt(0);

            Object obj = new Object(sl,fl);
            listObj.add(obj);
        }

        ObjListAdapter adapter = new ObjListAdapter(this, R.layout.adap_layout, listObj);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent editScreenIntent = new Intent(ListActivity.this, EditActivity.class);
                editScreenIntent.putExtra("id",listObj.get(position).getId());
                editScreenIntent.putExtra("name",listObj.get(position).getMat());
                startActivity(editScreenIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}