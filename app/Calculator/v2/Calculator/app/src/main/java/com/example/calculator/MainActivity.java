package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //the EditText is getting set
    public EditText EditText;
    //these floats are for the first and second value entered
    public float mValueOne, mValueTwo;
    //booleans for checking which one is active
    public boolean Addition, mSubtract, Multiplication, Division;

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
        }
    }

    public void calc(View target)
    {

        Button but = (Button)target;
        String value = but.getText().toString();
        if(!value.equals("=")){
            mValueOne = Float.parseFloat(EditText.getText() + "");
            switch (value)
            {
                case "+":
                    Addition = true;
                    break;
                case "-":
                    mSubtract = true;
                    break;
                case "*":
                    Multiplication = true;
                    break;
                case "/":
                    Division = true;
                    break;
            }
            EditText.setText(null);
        } else {
            mValueTwo = Float.parseFloat(EditText.getText() + "");

            if (Addition) {
                EditText.setText(mValueOne + mValueTwo + "");
                Addition = false;
            }else if (mSubtract) {
                EditText.setText(mValueOne - mValueTwo + "");
                mSubtract = false;
            }else if (Multiplication) {
                EditText.setText(mValueOne * mValueTwo + "");
                Multiplication = false;
            }else if (Division) {
                EditText.setText(mValueOne / mValueTwo + "");
                Division = false;
            }
        }

    }
}

