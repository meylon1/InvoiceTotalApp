package com.example.melat.invoicetotalapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends Activity implements TextView.OnEditorActionListener{
//declare instance variable for the widgets


     private EditText  inputeditText;
     private TextView percentTextView;
    private TextView discounttextView;
    private TextView totalTextView;

   private  String subtotalString;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to the widgets from the R class
        inputeditText = (EditText) findViewById(R.id.inputeditText);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        discounttextView = (TextView) findViewById(R.id.discountTextview);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        inputeditText.setOnEditorActionListener(this);
    }

        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {

        }

        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
           calculateAndDisplay();
           return false;
        }

    private void calculateAndDisplay() {

        //get subtotal

        subtotalString = inputeditText.getText().toString();
        float subtotal;
        if (subtotalString.equals("")) {
            subtotal = 0;
        } else {
            subtotal = Float.parseFloat(subtotalString);
        }

        //get the discount percent
        float discountPercent = 0;

        if (subtotal >= 200) {
            discountPercent = .2f;
        } else if (subtotal >= 100) {
            discountPercent = .1f;
        } else {
            discountPercent = 0;
        }

        //calculate the discount
        float discountAmount = subtotal * discountPercent;
        float total = subtotal - discountAmount;

        //display the data on the layout
        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(discountPercent));

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        discounttextView.setText(currency.format(discountAmount));
        totalTextView.setText(currency.format(total));

    }
}
