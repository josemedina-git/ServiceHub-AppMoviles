package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends AppCompatActivity {

    ImageButton back;
    Button pay;
    TextView addCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        back = findViewById(R.id.ibtn_back_pay);
        pay = findViewById(R.id.btn_pay_now);
        addCard = findViewById(R.id.tv_add_card);

        RadioButton rbVisa = findViewById(R.id.rb_visa);
        RadioButton rbMastercard = findViewById(R.id.rb_mastercard);
        RadioButton rbPaypal = findViewById(R.id.rb_paypal);

        RadioButton[] radioButtons = {rbVisa, rbMastercard, rbPaypal};

        for (RadioButton radioButton : radioButtons) {
            radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    for (RadioButton other : radioButtons) {
                        if (other != radioButton) {
                            other.setChecked(false);
                        }
                    }
                }
            });
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment.this, Suscription.class);
                startActivity(intent);
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stripePaymentLink = "https://buy.stripe.com/test_dR63cJ6zD8Ss7a8cMM";

                Toast.makeText(Payment.this, "You will be redirected to the payment page...", Toast.LENGTH_SHORT).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(stripePaymentLink));
                startActivity(browserIntent);

            }

        });
        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stripePaymentLink = "https://buy.stripe.com/test_dR63cJ6zD8Ss7a8cMM";

                Toast.makeText(Payment.this, "You will be redirected to the payment page...", Toast.LENGTH_SHORT).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(stripePaymentLink));
                startActivity(browserIntent);
            }
        });
      /*  pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment.this, MainActivity.class);
                Toast.makeText(Payment.this, "Thanks for you pay. You are now part of the family", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });*/
    }
}