package com.example.zulfikaranshari.zulfikaransharioktafinawan_1202154136_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputItem extends AppCompatActivity {
    public DbHelper dbHelper;
    private EditText mTitle;
    private EditText mDesc;
    private EditText mPriority;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_item);
        dbHelper = new DbHelper(InputItem.this);

        //menginisialisasi setiap View
        mTitle = (EditText) findViewById(R.id.inputTitle);
        mDesc = (EditText) findViewById(R.id.inputDsc);
        mPriority = (EditText) findViewById(R.id.inputPriority);
        mButton = (Button) findViewById(R.id.btnInput);

        addData();
    }

    public void addData(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mendapatkan data dari EditText lalu mengubahnya menjadi String
                String name = mTitle.getText().toString();
                String desc = mDesc.getText().toString();
                String priority = mPriority.getText().toString();
//                dbHelper.insertData(name,desc,priority);

                //mengecek apakah data berhasil di input atau tidak
                boolean isInserted = dbHelper.insertData(name,desc,priority);
                //jika berhasil maka akan menampilkan toast dan kembali ke MainActivity
                if (isInserted){
                    Toast.makeText(InputItem.this, "Data inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InputItem.this, MainActivity.class));
                //jika gagal maka akan menampilkan toast
                }else{
                    Toast.makeText(InputItem.this, "Input failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
