// HistActivity.java
//
// History
package com.example.accordionwidget;

import com.example.accordionwidget.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

public class HistActivity extends Activity { 
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_history);
			gobackhome();
		}
		public void gobackhome() {
			 
			final Context context = this;

			Button btnHome = (Button) findViewById(R.id.homeButton);

			btnHome.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

				    Intent intent = new Intent(context, ScanActivity.class);
		                        startActivity(intent);   
				}
			});
		}
		
}