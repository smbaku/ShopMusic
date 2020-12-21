package android.example.shopmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;

    EditText userNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.nameEditText);
        createSpinner();
        createMap();


    }

    public void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("Guitar", 100.0);
        goodsMap.put("Piano", 200.0);
        goodsMap.put("Clarinet", 1000.0);
        goodsMap.put("Drums", 500.0);
    }


    public void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList<>();
        spinnerArrayList.add("Guitar");
        spinnerArrayList.add("Piano");
        spinnerArrayList.add("Clarinet");
        spinnerArrayList.add("Drums");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void increaseBtn(View view) {
        TextView quantityText = findViewById(R.id.quantityTextView);
        quantityText.setText(String.valueOf(++quantity));
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);

    }

    public void decreaseBtn(View view) {

        TextView quantityText = findViewById(R.id.quantityTextView);
        if (quantity == 0) {
            quantity = 0;
        } else {
            quantityText.setText(String.valueOf(--quantity));
        }
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageViev);

        switch (goodsName) {
            case "Guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "Piano":
                goodsImageView.setImageResource(R.drawable.piano);
                break;
            case "Clarinet":
                goodsImageView.setImageResource(R.drawable.clarnet);
                break;
            case "Drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addToCart(View view) {

        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.price = price;
        order.orderPrice = price * quantity;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.orderPrice);
        orderIntent.putExtra("onePrice", order.price);


        startActivity(orderIntent);


    }
}

/*
 orderIntent.putExtra("goodsName",order.goodsName);
         orderIntent.putExtra("quantity",order.quantity);
         orderIntent.putExtra("orderPrice",order.orderPrice);*/
