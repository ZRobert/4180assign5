//Robert Payne
//in class 5a
package com.example.inclass5a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Button b;
	TextView t1;
	TextView t2; 
	EditText et;
	Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(this);
		et = (EditText) findViewById(R.id.editText1);
		handler = new Handler();
		t1 = (TextView) findViewById(R.id.textView1);
		t2 = (TextView) findViewById(R.id.textView2);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	
	public void handleResponse(String result){
		Log.d("demo", "handleResponse: " + result);
		String[] str = result.split(";");
		
		t1.setText(str[0]);
		t2.setText(str[1]);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		new AsyncGetPopulation(handler, this).execute("http://cci-webdev.uncc.edu/~mshehab/api-rest/states/index.php?method=getStatePopulation&state=" + et.getText().toString());

	}
	

}
