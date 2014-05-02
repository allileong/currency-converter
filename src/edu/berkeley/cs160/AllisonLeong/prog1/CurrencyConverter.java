package edu.berkeley.cs160.AllisonLeong.prog1;

//import com.example.myfirstapp.DisplayMessageActivity;
//import com.example.myfirstapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;



public class CurrencyConverter extends Activity implements OnItemSelectedListener {
	
	public final static String EXTRA_MESSAGE = "com.example.currencyconverter.MESSAGE";
	private String select1 = "$";
	private String select2 = "$";
	private static float DC = (float) 638.17;
	private static float BC = (float) 820.6;
	private static float euro = (float) 1.35;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);
        
        EditText msg = (EditText) findViewById(R.id.edit_message); 
        msg.setText("1.0");
        
        Spinner spinner1 = (Spinner) findViewById(R.id.choice1);
        spinner1.setOnItemSelectedListener(this);
        
        
        Spinner spinner2 = (Spinner) findViewById(R.id.choice2);
        spinner2.setOnItemSelectedListener(this);
        
	     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
	             R.array.choices, android.R.layout.simple_spinner_item);
	     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    
	     spinner1.setAdapter(adapter);
	     spinner2.setAdapter(adapter);   
	     
	     TextView  textView = (TextView) findViewById(R.id.result_text); 
	     textView.setText("Total");
	     
	     EditText text = (EditText) findViewById(R.id.edit_message);

	        text.addTextChangedListener(new TextWatcher() {

	          public void afterTextChanged(Editable s) {
	        	  try {
	        		EditText text = (EditText) findViewById(R.id.edit_message);
	          		String amt = text.getText().toString();
	              	System.out.println("EditText has: " + amt);
	              	float amount = Float.parseFloat(amt);
	              	amount = convertToUSD(amount, select1);
	              	amount = convertToWhatever(amount, select2);
	              	displayResult(Float.toString(amount));
	          	} catch (Exception e) {
	          		displayResult("Please enter a number value");
	          		System.out.println("Dude...no");
	          	}
	          }

	          public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

	          public void onTextChanged(CharSequence s, int start, int before, int count) {}
	       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_currency_converter, menu);
        return true;
    }
    
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
    	Spinner spinner = (Spinner) parent;
    	if (spinner.getId() == R.id.choice1) {
    		select1 = parent.getItemAtPosition(position).toString();
    		System.out.println("from: " + select1);
    	} else {
    		select2 = parent.getItemAtPosition(position).toString();
    		System.out.println("to: " + select2);
    	}
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	try {
    		String amt = editText.getText().toString();
        	System.out.println("EditText has: " + amt);
        	float amount = Float.parseFloat(amt);
        	amount = convertToUSD(amount, select1);
        	amount = convertToWhatever(amount, select2);
        	displayResult(Float.toString(amount));
    	} catch (Exception e) {
    		displayResult("Please enter a number value");
    		System.out.println("Dude...no");
    	}
    }
    
    public void onNothingSelected(AdapterView<?> arg0) {
    	select1 = "USD";
    	select2 = "USD";
    }
    
  
    /* On click of the Send button */
    /*
    public void convertAmount(View view) {
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	try {
    		String amt = editText.getText().toString();
        	System.out.println("EditText has: " + amt);
        	float amount = Float.parseFloat(amt);
        	amount = convertToUSD(amount, select1);
        	amount = convertToWhatever(amount, select2);
        	displayResult(Float.toString(amount));
    	} catch (Exception e) {
    		displayResult("Please enter a number value");
    		System.out.println("Dude...no");
    	}
    	
    }
    */
    
    public static float convertToUSD(float amt, String from) {
    	System.out.println("from is this: " + from);
    	if (from.equals("USD")) {
    		System.out.println("if case: USD"); 
    		System.out.println("Converting to USD yo: " + amt); 
    		return amt;
    	} else if (from.equals("BTC")) {
    		System.out.println("if case: BitCoin");
    		System.out.println("incoming!! " + amt); 
    		amt = (float) (amt*BC); 
    		System.out.println("Converting to USD yo: " + amt); 
    		return amt;
    	} else if (from.equals("Doge")) {
    		System.out.println("if case: DogeCoin");
    		amt = (float) (amt/DC); 
    		System.out.println("Converting to USD yo: " + amt); 
    		return amt;
    	} else if (from.equals("Euro")) {
    		System.out.println("if case: Euro");
    		amt = (float) (amt*euro);
    		System.out.println("Converting to USD yo: " + amt);
    		return amt;
    	} else {
    		System.out.println("Invalid conversion picked");
    		return (float) 0.0;
    	}
    	
    }
    public static float convertToWhatever(float usd, String to) {
    	System.out.println("Whatever man.");
    	if (to.equals("USD")) {
    		return usd;
    	} else if (to.equals("BTC")) {
    		return usd/BC;
    	} else if (to.equals("Doge")){
    		return usd*DC;
    	} else if (to.equals("Euro")) {
    		return usd/euro;
    	} else {
    		System.out.println("Invalid conversion chosen");
    		return (float) 0.0;
    	}
    }
    
    public void displayResult(String message) {
    	TextView  textView = (TextView) findViewById(R.id.result_text); 
    	textView.setText(message);
    }
    
    
    	
    
}
