package com.example.vietnamcurrencyconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//Create objects for all the widgets
    String[] currency = { "INR", "VND", "USD" };
    TextView tv_from,tv_result,btnsetting;
    EditText edt_amount, curr_amt;
    Button btnusd,btninr,btnvnd;
    String amount,INR_To_VND_Global,USD_To_INR_Global,USD_To_VND_Global;
    double INR,USD, VND,result;
    DecimalFormat df = new DecimalFormat("#,##,###.####");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        // To work selected option from  the dropdown options.
        Spinner spinnerCurrency=findViewById(R.id.currency_drpdwn);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.currency, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCurrency.setAdapter(adapter);
        spinnerCurrency.setOnItemSelectedListener(this);

        // Method to perform on clicking settings.
        ClicklistenersforSettings(this);

        // To get the global conversions from the settings in the dialog_layout.xml
        SharedPreferences settings = getSharedPreferences("ttt", 0);
        INR_To_VND_Global=settings.getString("Global Amount1","");
        USD_To_INR_Global = settings.getString("Global Amount2","");
        USD_To_VND_Global = settings.getString("Global Amount3","");

    }
    //Initialize all the widgets and do view binding
    public void init(){

        tv_from=findViewById(R.id.tv_from);
        tv_result=findViewById(R.id.tvresult);
        edt_amount=findViewById(R.id.edt_amount);
        btnusd=findViewById(R.id.btnusd);
        btninr=findViewById(R.id.btninr);
        btnvnd=findViewById(R.id.btnvnd);
        btnsetting=findViewById(R.id.settings_button);

  }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {

        // To hide/show the buttons, depending on the currency selected.
        Toast.makeText(getApplicationContext(), "Selected User: "+currency[position] ,Toast.LENGTH_SHORT).show();
        if(currency[position].equals("INR")){
            btninr.setVisibility(View.GONE);
            btnusd.setVisibility(View.VISIBLE);
            btnvnd.setVisibility(View.VISIBLE);
            System.out.println(currency[position]);
            ClicklistenersforINR();
        } else if (currency[position].equals("VND")) {
            btnvnd.setVisibility(View.GONE);
            btninr.setVisibility(View.VISIBLE);
            btnusd.setVisibility(View.VISIBLE);
            ClicklistenersforVND();
        }
        else
        {
            btnusd.setVisibility(View.GONE);
            btninr.setVisibility(View.VISIBLE);
            btnvnd.setVisibility(View.VISIBLE);
            ClicklistenersforUSD();
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }

    //Now We will create click listeners for each button

    // To convert INR to either VND or USD.
    public void ClicklistenersforINR(){
        btnusd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here we get user input into this variable
                amount=edt_amount.getText().toString();
                INR= Double.parseDouble(USD_To_INR_Global);

                //Double.parse used to convert string value into double for calculating purpose.
                result=Double.parseDouble(amount)/(INR);
                //Setting the result and the basic/standard conversion rate on the screen.
                tv_result.setText("$ "+ df.format(result));
                tv_from.setText("1 INR= "+df.format(1/INR)+" USD");
            }
        });

        btnvnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount=edt_amount.getText().toString();
                INR= Double.parseDouble(INR_To_VND_Global);
                result=Integer.parseInt(amount)*(INR);
                tv_result.setText("VND "+ df.format(result));
                tv_from.setText("1 INR= "+INR_To_VND_Global+" VND");
            }
        });
    }

    // To convert USD to either VND or INR.
    public void ClicklistenersforUSD(){

        btninr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount=edt_amount.getText().toString();
                USD= Double.parseDouble(USD_To_INR_Global);
                result=Integer.parseInt(amount)*(USD);
                tv_result.setText("₹ "+ df.format(result));
                tv_from.setText("1 USD= "+df.format(1/USD)+" INR");
            }
        });
        btnvnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount=edt_amount.getText().toString();
                USD= Double.parseDouble(USD_To_VND_Global);
                result=Integer.parseInt(amount)*(USD);
                tv_result.setText("VND "+ df.format(result));
                tv_from.setText("1 USD = "+df.format(USD)+" VND");
            }
        });
    }

    // To convert VND to either INR or USD.
    public void ClicklistenersforVND(){

        btnusd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                amount=edt_amount.getText().toString();
                VND= Double.parseDouble(USD_To_VND_Global);
                result=Integer.parseInt(amount)/(VND);
                tv_result.setText("$ "+ df.format(result));
                tv_from.setText("1 VND = "+df.format(1/VND)+" USD");
            }
        });
        btninr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount=edt_amount.getText().toString();

                VND= Double.parseDouble(INR_To_VND_Global);
                result=Double.parseDouble(amount)/(VND);
                tv_result.setText("₹ "+ df.format(result));

                tv_from.setText("1 VND = "+df.format(1/VND)+" INR");
            }
        });
    }

    //Method to perform on clicking the settings button.
    public void ClicklistenersforSettings(MainActivity mainActivity){

        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }

            public void openActivity2() {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
    });
    }

}