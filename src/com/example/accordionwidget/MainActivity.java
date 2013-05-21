package com.example.accordionwidget;
 
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
 
public class MainActivity extends TabActivity {
 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
 
		// Scan tab
		Intent intentScan = new Intent().setClass(this, ScanActivity.class);
		TabSpec tabSpecScan = tabHost
		  .newTabSpec("Scan")
		  .setIndicator("", ressources.getDrawable(R.drawable.ic_launcher))
		  .setContent(intentScan);
 
		// History tab
		Intent intentHistory = new Intent().setClass(this, HistActivity.class);
		TabSpec tabSpecHistory = tabHost
		  .newTabSpec("History")
		  .setIndicator("", ressources.getDrawable(R.drawable.ic_launcher))
		  .setContent(intentHistory);
 
		// About tab
		Intent intentAbout = new Intent().setClass(this, AboutActivity.class);
		TabSpec tabSpecAbout = tabHost
		  .newTabSpec("About")
		  .setIndicator("", ressources.getDrawable(R.drawable.ic_launcher))
		  .setContent(intentAbout);
 
		// add all tabs 
		tabHost.addTab(tabSpecScan);
		tabHost.addTab(tabSpecHistory);
		tabHost.addTab(tabSpecAbout);
 
		//set Scan tab as default (zero based)
		tabHost.setCurrentTab(0);
	}
 
}