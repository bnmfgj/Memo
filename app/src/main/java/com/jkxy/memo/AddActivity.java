package com.jkxy.memo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/11/22.
 */
public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Db db;
    private EditText etTime,etDes;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_add);
        initView();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                SQLiteDatabase dbWrite =db.getWritableDatabase();
                ContentValues cv=new ContentValues();
                cv.put("time",etTime.getText().toString());
                cv.put("description",etDes.getText().toString());
                dbWrite.insert("note",null,cv);
                break;
        }
    }
    public void initView(){
        db=new Db(this);
        findViewById(R.id.save).setOnClickListener(this);
        etDes= (EditText) findViewById(R.id.etDes);
        etTime= (EditText) findViewById(R.id.etTime);
    }
}
