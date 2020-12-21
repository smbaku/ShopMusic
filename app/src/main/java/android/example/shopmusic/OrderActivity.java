package android.example.shopmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"salehmacbook@gmail.com"};
    String subject = "Order from Music shop";
    String emailText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your Order");

        Intent recivideOrderIntent = getIntent();
        //  извлекаем name
        String userName = recivideOrderIntent.getStringExtra("userName");
        String goodsName = recivideOrderIntent.getStringExtra("goodsName");
        int quantity = recivideOrderIntent.getIntExtra("quantity", 0);
        double orderPrice = recivideOrderIntent.getDoubleExtra("price", 0);
        double price = recivideOrderIntent.getDoubleExtra("onePrice", 0);

        emailText = "Client name: " + userName + "\n" + "Selected Instrument: " + goodsName +
                "\n" + "Quantity :" + quantity + "\n" +
                "Total price : " + orderPrice + "  $" + "\n" +
                "Price per one " + goodsName + " cost : " + price + " $";


        TextView orderTextView = findViewById(R.id.orderTextView);

        orderTextView.setText(emailText);


    }

    public void sentBtn(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}




