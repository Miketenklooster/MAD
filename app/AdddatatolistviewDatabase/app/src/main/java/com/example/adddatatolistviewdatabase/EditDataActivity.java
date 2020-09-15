package com.example.adddatatolistviewdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave,btnDelete;
    private EditText editable_item,editable_item2;
    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private String selectedPrice;
    private int selectedID;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        editable_item2 = (EditText) findViewById(R.id.editable_item2);
        mDatabaseHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id",-1);
        selectedName = receivedIntent.getStringExtra("name");
        selectedPrice = receivedIntent.getStringExtra("price");
        editable_item.setText(selectedName);
        editable_item2.setText(selectedPrice);

        //String.format("%.2f", selectedName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item,selectedID,selectedName);
                }else{
                    toastMessage("You must enter a name");
                }

                String item2 = editable_item2.getText().toString();
                if(!item2.equals("")){
                    mDatabaseHelper.updatePrice(item2,selectedID,selectedPrice);
                }else{
                    toastMessage("You must enter a price");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                mDatabaseHelper.deletePrice(selectedID,selectedPrice);
                editable_item.setText("");
                editable_item2.setText("");
                toastMessage("removed from database");
            }
        });
    }

    /**
     *
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
