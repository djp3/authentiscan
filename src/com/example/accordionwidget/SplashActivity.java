package com.example.accordionwidget;

import com.example.accordionwidget.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.content.Context;
import android.content.Intent;
import android.view.Window;

public class SplashActivity extends Activity {
	private long ms = 0;
	private long delay = 2000;
	private boolean splashOn = true;
	private boolean isPaused = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		Thread mythread = new Thread() {
			public void run() {
				try {
					while (splashOn && ms < delay) {
						if(!isPaused)
							ms=ms+100;
						sleep(100);
					}
				} catch(Exception e) {}
				finally {
					Intent intent = new Intent(SplashActivity.this, MainActivity.class);
					startActivity(intent);
				}
			}
		};
		mythread.start();
	}
}