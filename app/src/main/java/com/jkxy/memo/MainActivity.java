package com.jkxy.memo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Db db;
    private SimpleCursorAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        dataRefresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                Intent i = new Intent(this, AddActivity.class);
                startActivityForResult(i, 1);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        dataRefresh();
    }

    public void dataRefresh() {
        SQLiteDatabase dbRead = db.getReadableDatabase();
        Cursor c = dbRead.query("note", null, null, null, null, null, null);
        adapter = new SimpleCursorAdapter(this, R.layout.layout_list, c, new String[]{"time", "description"}, new int[]{R.id.tvTime, R.id.tvDes});
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void initView() {
        findViewById(R.id.btnAdd).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        db = new Db(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择操作");
        final String[] operation = {"删除", "取消"};
        builder.setItems(operation, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Cursor c = adapter.getCursor();
                        c.moveToPosition(i);

                        int itemId = c.getInt(c.getColumnIndex("_id"));
                        SQLiteDatabase dbWrite = db.getWritableDatabase();
                        dbWrite.delete("note", "_id=?", new String[]{itemId + ""});
                        dataRefresh();
                }
            }
        });
        builder.show();
    }
}
