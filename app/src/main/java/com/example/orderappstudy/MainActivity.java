package com.example.orderappstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView quantityTextView;
    EditText price;
    String addTopping;
    String chocolateTopping;
    EditText name;
    int total;

    //List toppings = new ArrayList();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantityTextView = (TextView)findViewById(R.id.quantity_text_view);
        price = (EditText)findViewById(R.id.price_edit_view);
        name = (EditText)findViewById(R.id.name_edit_view);



    }


    public void increseQuantity(View v) {
        String quantity = String.valueOf(quantityTextView.getText());
        int newQuantity = Integer.parseInt((quantity));
        newQuantity ++;
        quantityTextView.setText(Integer.toString(newQuantity));
    }

    public void decreaseQuantity(View v) {
        String quantity = String.valueOf(quantityTextView.getText());
        int newQuantity = Integer.parseInt((quantity));
        if (newQuantity>0)
        {
            newQuantity--;
        } else{
            newQuantity=0;
        }
        quantityTextView.setText(Integer.toString(newQuantity));
    }

//if may checkboxes
//    public void onCheckboxClicked(View view) {
//        boolean checked = ((CheckBox) view).isChecked();
//        switch(view.getId()) {
//            case R.id.checkbox_whipped_cream:
//                if (checked) {
//                    toppings.add("Whipped Cream");
//                } else {
//                    toppings.remove("Whipped Cream");
//                }break;
//            case R.id.checkbox_chocolate:
//                if (checked) {
//                    toppings.add("Chocolate");
//                } else {
//                    toppings.remove("Chocolate");
//                }break;
//
//        }
//    }

    public void submitOrder (View v) {
        TextView totalOrder= (TextView)findViewById(R.id.totalOrder);
        String quantity = String.valueOf(quantityTextView.getText());
        int newQuantity = Integer.parseInt((quantity));
        EditText price = (EditText) findViewById(R.id.price_edit_view);
        String unitPrice = String.valueOf((price.getText()));
        int newPrice=Integer.parseInt(unitPrice);

//Set card view to visible
        CardView card= (CardView)findViewById(R.id.card_view);
        card.setVisibility(View.VISIBLE);



//Check if checkbox is checked
        CheckBox toppings=(CheckBox)findViewById(R.id.checkbox_whipped_cream);
        if(toppings.isChecked())
        {
            addTopping = "Whipped cream";
        } else
        {
            addTopping="";
        }
        CheckBox chocolate=(CheckBox)findViewById(R.id.checkbox_chocolate);
        if(chocolate.isChecked())
        {
            chocolateTopping = "Chocolate";
        } else
        {
            chocolateTopping="";
        }

//check if price or quantity entered
        if (newPrice>0 && newQuantity>0) {
            total = newPrice * newQuantity;
//compose the text
            totalOrder.setText("Hello "+name.getText()+"\n the total price is " + Integer.toString(total) + "\nTopping \n"+ addTopping +"\n"+chocolateTopping);
        }
         else
        {
            totalOrder.setText("Please enter correct data");
        }


    }


    //send email

    public void sendEmail(View v)
    {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_TEXT,"Hello " + name.getText() + "\n the total price is " + Integer.toString(total) + "\nTopping \n" + addTopping + "\n" + chocolateTopping);
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

}