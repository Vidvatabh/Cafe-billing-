
package com.cafebilling;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private TextView totalText;
    private double total = 0;
    private final Map<String, Integer> cart = new LinkedHashMap<>();

    private final String[] items = {
            "Omelette + Bread",
            "Bread Pakoda",
            "Kanda Pakoda",
            "Upma",
            "Poha",
            "Thali",
            "Extra Rice",
            "Extra Dal",
            "Extra Sabji",
            "Extra Roti"
    };

    private final double[] prices = {
            50, 35, 35, 35, 35, 80, 25, 35, 35, 8
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(20, 20, 20, 20);

        TextView title = new TextView(this);
        title.setText("CAFE BILLING");
        title.setTextSize(26);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 10, 0, 20);
        main.addView(title);

        totalText = new TextView(this);
        totalText.setText("TOTAL: ₹0");
        totalText.setTextSize(24);
        totalText.setGravity(Gravity.CENTER);
        totalText.setPadding(0, 10, 0, 20);
        main.addView(totalText);

        for (int i = 0; i < items.length; i++) {
            final int index = i;

            Button button = new Button(this);
            button.setText(items[i] + "  ₹" + (int) prices[i]);
            button.setTextSize(18);

            button.setOnClickListener(v -> {
                total += prices[index];

                int quantity = cart.containsKey(items[index])
                        ? cart.get(items[index]) + 1
                        : 1;

                cart.put(items[index], quantity);
                totalText.setText("TOTAL: ₹" + (int) total);
            });

            main.addView(button);
        }

        Button clear = new Button(this);
        clear.setText("CLEAR BILL");
        clear.setTextSize(18);
        clear.setTextColor(Color.RED);

        clear.setOnClickListener(v -> {
            total = 0;
            cart.clear();
            totalText.setText("TOTAL: ₹0");
        });

        main.addView(clear);

        setContentView(main);
    }
}
