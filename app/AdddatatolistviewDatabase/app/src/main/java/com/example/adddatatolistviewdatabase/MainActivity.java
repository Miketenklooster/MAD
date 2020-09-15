package com.example.adddatatolistviewdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnView;
    private EditText editText, editText2;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                String newEntry2 = editText2.getText().toString();



                if (editText.length() == 0 || editText2.length() == 0) {
                    toastMessage("You must put something in the field");
                    return;
                }


                AddData(newEntry);
                AddData2(newEntry2);
                editText.setText("");
                editText2.setText("");
                return;
            }
        });

        btnView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * @param newEntry
     */
    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);

        String toastMsg = (insertData) ? "Data SuccessFully Inserted" : "Something went wrong";
        toastMessage(toastMsg);
    }
    public void AddData2(String newEntry2){
        boolean insertData2 = mDatabaseHelper.addData2(newEntry2);

        String toastMsg = (insertData2) ? "Data SuccessFully Inserted" : "Something went wrong";
        toastMessage(toastMsg);
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
