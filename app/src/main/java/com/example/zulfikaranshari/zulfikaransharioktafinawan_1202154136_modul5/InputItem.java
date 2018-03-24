package com.example.zulfikaranshari.zulfikaransharioktafinawan_1202154136_modul5;

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
                dbHelper = new DbHelper(InputItem.this);
                String name = mTitle.getText().toString();
                String desc = mDesc.getText().toString();
                int priority = Integer.parseInt(mPriority.getText().toString());
//                dbHelper.insertData(name,desc,priority);

                boolean isInserted = dbHelper.insertData(name,desc,priority);
                if (isInserted){
                    Toast.makeText(InputItem.this, "Data inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InputItem.this, "Input failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
