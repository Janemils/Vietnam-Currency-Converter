package com.example.vietnamcurrencyconverter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity2 extends AppCompatActivity {
    EditText INR_To_VND, USD_To_INR,USD_To_VND;
    Button btnback;
    String INR_To_VND_Global,USD_To_INR_Global,USD_To_VND_Global;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        init();

        // To display the previously saved/entered value on navigating to this page.
        SharedPreferences settings = getSharedPreferences("ttt", 0);
        INR_To_VND_Global=settings.getString("Global Amount1","");
        INR_To_VND.setText(INR_To_VND_Global);
        USD_To_INR_Global=settings.getString("Global Amount2","");
        USD_To_INR.setText(USD_To_INR_Global);
        USD_To_VND_Global=settings.getString("Global Amount3","");
        USD_To_VND.setText(USD_To_VND_Global);

        // Method for clicking on back button.
        ClicklistenersforBack(this);
    }

    //Initialize all the widgets and do view binding
    public void init(){

        INR_To_VND=findViewById(R.id.Exchange_INR_To_VND);
        USD_To_INR=findViewById(R.id.Exchange_USD_To_INR);
        USD_To_VND=findViewById(R.id.Exchange_USD_To_VND);
        btnback=findViewById(R.id.btnback);

    }


    //Method to perform on clicking the back button - to go back to activity_main.xml page.
    public void ClicklistenersforBack(MainActivity2 mainActivity2){

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }

            public void openActivity1() {
                Intent intent1 = new Intent(MainActivity2.this, MainActivity.class);

                // To globalize all the values entered by user, so that they can be used for conversion in the MainActivity.java page.
                SharedPreferences sp= getSharedPreferences("ttt", 0);
                SharedPreferences.Editor editor = sp.edit();
                String Global_Amount1=INR_To_VND.getText().toString();
                String Global_Amount2=USD_To_INR.getText().toString();
                String Global_Amount3=USD_To_VND.getText().toString();
                editor.putString("Global Amount1", Global_Amount1);
                editor.putString("Global Amount2", Global_Amount2);
                editor.putString("Global Amount3", Global_Amount3);
                editor.commit();
                startActivity(intent1);
                finish();

            }
        });
    }

}
