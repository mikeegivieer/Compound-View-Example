package com.migueelaangeel.compoundviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SideSpinner fruitsSpinner;
        fruitsSpinner = (SideSpinner)this
                .findViewById(R.id.sidespinner_fruits);

        CharSequence fruitList[] = { "Apple",
                "Orange",
                "Pear",
                "Grapes" };
        fruitsSpinner.setValues(fruitList);
        fruitsSpinner.setSelectedIndex(1);
    }
}
