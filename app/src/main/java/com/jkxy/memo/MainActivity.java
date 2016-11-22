package com.jkxy.memo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Db db;
    private SimpleCursorAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnAdd).setOnClickListener(this);
        listView= (ListView) findViewById(R.id.list);
        db = new Db(this);
        dataRefresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                Intent i =new Intent(this,AddActivity.class);
                startActivity(i);
                break;
        }
    }

    public void dataRefresh() {
        SQLiteDatabase dbRead = db.getReadableDatabase();
        Cursor c = dbRead.query("note", null, null, null, null, null, null);
        adapter=new SimpleCursorAdapter(this,R.layout.layout_list,c,new String[]{"time","description"},new int[]{R.id.tvTime,R.id.tvDes});
        listView.setAdapter(adapter);

    }
}
