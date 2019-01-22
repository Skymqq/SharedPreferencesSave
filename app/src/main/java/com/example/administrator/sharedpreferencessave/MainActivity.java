package com.example.administrator.sharedpreferencessave;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn, btn_read;
    private TextView tv_name, tv_age, tv_married;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        btn_read = (Button) findViewById(R.id.btn_read);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_married = (TextView) findViewById(R.id.tv_married);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();//将数据保存入本地sp
                Toast.makeText(MainActivity.this, "data already saved in sp", Toast.LENGTH_SHORT).show();
            }
        });

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                read();//将数据从本地sp中读取出来并显示
            }
        });
    }

    private void save() {
        SharedPreferences.Editor editor = this.getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("name", "Tom");
        editor.putInt("age", 28);
        editor.putBoolean("married", false);
        editor.apply();
    }

    private void read() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                String name = sp.getString("name", "");
                int age = sp.getInt("age", 0);
                boolean married = sp.getBoolean("married", false);
                tv_name.setText("" + name);
                tv_age.setText("" + age);
                tv_married.setText("" + married);
                Toast.makeText(MainActivity.this, "data already read in sp", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
