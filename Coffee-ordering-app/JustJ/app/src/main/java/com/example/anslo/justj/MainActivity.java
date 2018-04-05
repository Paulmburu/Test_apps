package com.example.anslo.justj;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if(quantity==100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than a 100 coffees", Toast.LENGTH_SHORT).show();
            //Exit this method early because there is nothing left to do
            return;
        }
        quantity=quantity+1;
        displayQuantity(quantity);

    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity==1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            //Exit this method early because there is nothing left to do
            return;
        }
        quantity=quantity-1;
        displayQuantity(quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox=(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream=whippedCreamCheckBox.isChecked();
        Log.v("MainActivity","hasWhippedCream "+hasWhippedCream);

        CheckBox chocolate=(CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate=chocolate.isChecked();

        EditText name=(EditText)findViewById(R.id.name_edittext);
        String userName=name.getText().toString();
        int price=calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage=createOrderSummary(price,hasWhippedCream,hasChocolate,userName);



        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just java order for "+userName);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        //displayMessage(priceMessage);

        //        Intent intent = new Intent(Intent.ACTION_VIEW);
        //        intent.setData(Uri.parse("geo:47.6,-122.3"));
        //        if (intent.resolveActivity(getPackageManager()) != null) {
        //            startActivity(intent);
        //        }

    }

    /*
     *create summary of the order
     *
     * @param price of the order
     * @param addWhippedCream is whether or not the user wants the whipped topping
     * @param addChocolate is whether user wants chocolate topping
     * @return text summary
     */
    private String createOrderSummary(int price,boolean addWhippedCream,boolean addChocolate,String name){
        String priceMessage="Name :"+name;
        priceMessage+="\nadd whipped cream ? "+addWhippedCream;
        priceMessage+="\nadd chocolate ? "+addChocolate;
        priceMessage+="\nQuantity "+quantity;
        priceMessage+="\nTotal :$ "+price;
        priceMessage+="\nThank you!";
        return priceMessage;
    }

    /**
     * Calculates the price of the order.
     *@param addChocolate is whether or not user wants chocolate topping
     * @param addWhippedCream is whether or not user wants whipped cream topping
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice=5;  //price of one cup of coffee
        if(addWhippedCream)  //add $1 if user wants whipped cream
            basePrice+=1;
        if(addChocolate)    //add $2 if user wants whipped cream
            basePrice+=2;

        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numOfCoffeee) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numOfCoffeee);
    }


    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}

