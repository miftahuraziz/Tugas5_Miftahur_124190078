package com.miftahuraziz.simpankontak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.miftahuraziz.simpankontak.entity.AppDatabase;
import com.miftahuraziz.simpankontak.entity.DataContact;
import com.miftahuraziz.simpankontak.adapter.MainAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<DataContact> list = new ArrayList<>();
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        MainAdapter mainAdapter = new MainAdapter(getApplicationContext());

        if (appDatabase == null) {
            appDatabase = AppDatabase.db(getApplicationContext());
        }
        list.addAll(appDatabase.dao().getData());
        mainAdapter.setData(list);
        mainAdapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToAdd = new Intent(MainActivity.this, NewItemActivity.class);
                startActivity(moveToAdd);
                new MainActivity();
            }
        });
    }
}