package com.jkxy.memo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Db db;
    private EditText etTime, etDes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                String str = etDes.getText().toString();
                int i = Integer.parseInt(etTime.getText().toString());
                if (i >= 0 && i < 24 && !str.equals("") && !etTime.getText().toString().equals("")) {
                    SQLiteDatabase dbWrite = db.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put("time", etTime.getText().toString());
                    cv.put("description", etDes.getText().toString());
                    dbWrite.insert("note", null, cv);
                    dbWrite.close();
                    Toast.makeText(AddActivity.this, "已存储", Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    Toast.makeText(AddActivity.this, "请输入正确的时间和内容", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public void initView() {
        db = new Db(this);
        findViewById(R.id.save).setOnClickListener(this);
        etDes = (EditText) findViewById(R.id.etDes);
        etTime = (EditText) findViewById(R.id.etTime);
    }
}
