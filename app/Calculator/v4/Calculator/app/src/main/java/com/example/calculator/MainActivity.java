package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //the EditText is getting set
    public EditText EditText;
    //these floats are for the first and second value entered
    public float mValueOne, mValueTwo, Result;
    //booleans for checking which one is active
    public boolean Addition, mSubtract, Multiplication, Division, Values, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Retrieves GlobalVars
        this.setGlobalVars();
    }

    private void setGlobalVars()
    {
        EditText = findViewById(R.id.edt1);
    }

    public void setNumbers(View target)
    {
        Button but   = (Button)target;
        String value = but.getText().toString();
        if(!value.equals("C")) {
            EditText.setText(EditText.getText() + value);
        }else{
            EditText.setText(null);
            mValueOne = 0.0f;
            mValueTwo = 0.0f;
            Result    = 0.0f;
        }
    }

    public void calc(View target)
    {
        Button but = (Button)target;
        String value = but.getText().toString();
        if(EditText.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Please select a number", Toast.LENGTH_SHORT).show();
        } else {
            if (!value.equals("=")) {
                Values = mValueOne != 0.0f && !result;
                switch (value) {
                    case "+":
                        Addition = true;
                        if (Values) {
                            mValueOne = mValueOne + Float.parseFloat(EditText.getText() + "");
                        }
                        break;
                    case "-":
                        mSubtract = true;
                        if (Values) {
                            mValueOne = mValueOne - Float.parseFloat(EditText.getText() + "");
                        }
                        break;
                    case "*":
                        Multiplication = true;
                        if (Values) {
                            mValueOne = mValueOne * Float.parseFloat(EditText.getText() + "");
                        }
                        break;
                    case "/":
                        Division = true;
                        if (Values) {
                            mValueOne = mValueOne / Float.parseFloat(EditText.getText() + "");
                        }
                        break;
                }
                if (!Values) {
                    mValueOne = Float.parseFloat(EditText.getText() + "");
                }
                EditText.setText(null);
                result = false;
            } else {
                mValueTwo = Float.parseFloat(EditText.getText() + "");

                if (Addition) {
                    Result = mValueOne + mValueTwo;
                    Addition = false;
                } else if (mSubtract) {
                    Result = mValueOne - mValueTwo;
                    mSubtract = false;
                } else if (Multiplication) {
                    Result = mValueOne * mValueTwo;
                    Multiplication = false;
                } else if (Division) {
                    Result = mValueOne / mValueTwo;
                    Division = false;
                }
                EditText.setText(Result + "");
                mValueOne = Result;
                result = true;
            }
        }
    }
}

