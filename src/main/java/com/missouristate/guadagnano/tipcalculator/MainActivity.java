package com.missouristate.guadagnano.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TipCalculator tipCalc;
    private NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator( .17f, 100.0f);
        setContentView(R.layout.activity_main);

        billEditText = findViewById(R.id.et_Bill_Amount);
        tipEditText = findViewById(R.id.et_Enter_Tip);

        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);
    }

    public void calculate ()
    {

        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();

        TextView tipTextView = findViewById(R.id.tv_Tip_Total);
        TextView totalTextView = findViewById(R.id.tv_Bill_Total);

        try
        {
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);

            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);

            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();

            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
        }
        catch(NumberFormatException nfe)
        {

        }

    }

    private class TextChangeHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            calculate();
        }
    }
}
