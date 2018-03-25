package com.example.zulfikaranshari.zulfikaransharioktafinawan_1202154136_modul5;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public DbHelper dbHelper;
    private RecyclerView mRecyclerView;
    private ArrayList<ListModel> mModel;
    private Adapter mAdapter;
    String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //membuat object dbHelper
        dbHelper = new DbHelper(this);
        //membuat objeck ArrayList
        mModel = new ArrayList<>();
        //menginisialisasi rec view
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //set LayoutManger untuk rec view
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //set Adapter baru
        mAdapter = new Adapter(this, mModel);
        //memasukkan adapter kedalam rec view
        mRecyclerView.setAdapter(mAdapter);

        //membuat FAB dan action jika di click
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //memulai InputItem activity jika FAB di clik
                startActivity(new Intent(MainActivity.this, InputItem.class));
            }
        });

        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);


        color = pref.getString("chosenColor", "-1");

        //menginisialisasi data yang sudah ada
        initialiseData();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
           ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                Collections.swap(mModel, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                ListModel listModel = mModel.get(viewHolder.getAdapterPosition());
                String id = String.valueOf(listModel.getID());

                mModel.remove(viewHolder.getAdapterPosition());
                //menghapus data jika card di swipe
                dbHelper.deleteData(listModel.getID());

                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
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
//            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initialiseData(){
//        dbHelper = new DbHelper(MainActivity.this);
        //membuat cursor untuk mengmbil semua data
        Cursor c = dbHelper.getAllData();
        mModel.clear();
        //mengambil data dari setiap row pada DB dan dimasukkan ke dalam ArrayList
        while(c.moveToNext()){
            mModel.add(new ListModel(c.getInt(0), c.getString(1), c.getString(2),c.getString(3)));
        }

        mAdapter.notifyDataSetChanged();

    }



}
