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

public class ScanActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
	//	addListenerOnButton();

		Button btnNFC = (Button) findViewById(R.id.btnNFC);
		btnNFC.setEnabled(false);
		Button btnVISUAL = (Button) findViewById(R.id.btnVISUAL);
		btnVISUAL.setEnabled(false);
		Button btnNETWORK = (Button) findViewById(R.id.btnNETWORK);
		btnNETWORK.setEnabled(false);
		Button btnRESULT = (Button) findViewById(R.id.btnRESULT);
		btnRESULT.setEnabled(false);

		final View panelNFC = findViewById(R.id.panelNfc);
		panelNFC.setVisibility(View.VISIBLE);

		View panelVisual = findViewById(R.id.panelVisual);
		panelVisual.setVisibility(View.GONE);

		View panelNetwork = findViewById(R.id.panelNetwork);
		panelNetwork.setVisibility(View.GONE);
		
		View panelResult = findViewById(R.id.panelResult);
		panelResult.setVisibility(View.GONE);
		
		
		// since buttons are no longer clickable, listeners are no longer needed
		
		/* STEP 1*/
/*		btnNFC.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// DO STUFF
				View panelProfile = findViewById(R.id.panelNfc);
				panelProfile.setVisibility(View.VISIBLE);

				View panelSettings = findViewById(R.id.panelVisual);
				panelSettings.setVisibility(View.GONE);

				View panelPrivacy = findViewById(R.id.panelNetwork);
				panelPrivacy.setVisibility(View.GONE);
				
				View panelResult = findViewById(R.id.panelResult);
				panelResult.setVisibility(View.GONE);

			}
		});
*/		
		final Button btnOpenPopup = (Button) findViewById(R.id.scan);
		btnOpenPopup.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
				View popupView = layoutInflater.inflate(R.layout.popup, null);
				final PopupWindow popupWindow = new PopupWindow(popupView,
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

				Button btnDismiss = (Button) popupView
						.findViewById(R.id.dismiss);
				btnDismiss.setOnClickListener(new Button.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						popupWindow.dismiss();
						View panelProfile = findViewById(R.id.panelNfc);
						panelProfile.setVisibility(View.GONE);

						View panelSettings = findViewById(R.id.panelVisual);
						panelSettings.setVisibility(View.VISIBLE);
						
						View panelSnappic1 = findViewById(R.id.panelSnappic1);
						panelSnappic1.setVisibility(View.VISIBLE);
						
						View panelSnappic2 = findViewById(R.id.panelSnappic2);
						panelSnappic2.setVisibility(View.GONE);

						View panelPrivacy = findViewById(R.id.panelNetwork);
						panelPrivacy.setVisibility(View.GONE);
						
						View panelResult = findViewById(R.id.panelResult);
						panelResult.setVisibility(View.GONE);
					}
				});
               popupWindow.showAsDropDown(btnOpenPopup, 50, -30);
         
		}});
		/* STEP 2 */

/*		btnVISUAL.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// DO STUFF
				View panelProfile = findViewById(R.id.panelNfc);
				panelProfile.setVisibility(View.GONE);

				View panelSettings = findViewById(R.id.panelVisual);
				panelSettings.setVisibility(View.VISIBLE);
				
				View panelSnappic1 = findViewById(R.id.panelSnappic1);
				panelSnappic1.setVisibility(View.VISIBLE);
				
				View panelSnappic2 = findViewById(R.id.panelSnappic2);
				panelSnappic2.setVisibility(View.GONE);

				View panelPrivacy = findViewById(R.id.panelNetwork);
				panelPrivacy.setVisibility(View.GONE);
				
				View panelResult = findViewById(R.id.panelResult);
				panelResult.setVisibility(View.GONE);
			}
		});	
*/
		final Button snapPic = (Button) findViewById(R.id.snappic);
		snapPic.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// add view for button
				View panelSnappic1 = findViewById(R.id.panelSnappic1);
				panelSnappic1.setVisibility(View.GONE);
				
				View panelSnappic2 = findViewById(R.id.panelSnappic2);
				panelSnappic2.setVisibility(View.VISIBLE);     
		}});
		
		final Button visualScanEnter = (Button) findViewById(R.id.enter);
		visualScanEnter.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE); 

				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                           InputMethodManager.HIDE_NOT_ALWAYS);
				
				View panelProfile = findViewById(R.id.panelNfc);
				panelProfile.setVisibility(View.GONE);

				View panelSettings = findViewById(R.id.panelVisual);
				panelSettings.setVisibility(View.GONE);

				View panelPrivacy = findViewById(R.id.panelNetwork);
				panelPrivacy.setVisibility(View.VISIBLE);  
				
				View panelResult = findViewById(R.id.panelResult);
				panelResult.setVisibility(View.GONE);
		}});

		final Button visualScanContinue = (Button) findViewById(R.id.next);
		visualScanContinue.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// add view for button
				View panelProfile = findViewById(R.id.panelNfc);
				panelProfile.setVisibility(View.GONE);

				View panelSettings = findViewById(R.id.panelVisual);
				panelSettings.setVisibility(View.GONE);

				View panelPrivacy = findViewById(R.id.panelNetwork);
				panelPrivacy.setVisibility(View.VISIBLE);  
				
				View panelResult = findViewById(R.id.panelResult);
				panelResult.setVisibility(View.GONE);
		}});
		
		final Button visualScanSkip = (Button) findViewById(R.id.skip);
		visualScanSkip.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// add view for button
				View panelProfile = findViewById(R.id.panelNfc);
				panelProfile.setVisibility(View.GONE);

				View panelSettings = findViewById(R.id.panelVisual);
				panelSettings.setVisibility(View.GONE);

				View panelPrivacy = findViewById(R.id.panelNetwork);
				panelPrivacy.setVisibility(View.VISIBLE);  
				
				View panelResult = findViewById(R.id.panelResult);
				panelResult.setVisibility(View.GONE);
		}});
		
		/* STEP 3*/
		final Button networkScanSkip = (Button) findViewById(R.id.temporaryskip);
		networkScanSkip.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// add view for button
				View panelProfile = findViewById(R.id.panelNfc);
				panelProfile.setVisibility(View.GONE);

				View panelSettings = findViewById(R.id.panelVisual);
				panelSettings.setVisibility(View.GONE);

				View panelPrivacy = findViewById(R.id.panelNetwork);
				panelPrivacy.setVisibility(View.GONE);  
				
				View panelResult = findViewById(R.id.panelResult);
				panelResult.setVisibility(View.VISIBLE);
		}});
/*		btnNETWORK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// DO STUFF
				View panelProfile = findViewById(R.id.panelNfc);
				panelProfile.setVisibility(View.GONE);

				View panelSettings = findViewById(R.id.panelVisual);
				panelSettings.setVisibility(View.GONE);

				View panelPrivacy = findViewById(R.id.panelNetwork);
				panelPrivacy.setVisibility(View.VISIBLE);
				
				View panelResult = findViewById(R.id.panelResult);
				panelResult.setVisibility(View.GONE);

			}
		});
*/
		final Button scanAccept = (Button) findViewById(R.id.accept);
		scanAccept.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// DETAILS/HISTORY POPUP or adds scan details to history???
				
		}});
		final Button scanReject = (Button) findViewById(R.id.reject);
		scanReject.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// add view for button
				View panelProfile = findViewById(R.id.panelNfc);
				panelProfile.setVisibility(View.VISIBLE);

				View panelSettings = findViewById(R.id.panelVisual);
				panelSettings.setVisibility(View.GONE);

				View panelPrivacy = findViewById(R.id.panelNetwork);
				panelPrivacy.setVisibility(View.GONE);  
				
				View panelResult = findViewById(R.id.panelResult);
				panelResult.setVisibility(View.GONE);
		}});
		final Button scanReset = (Button) findViewById(R.id.reset);
		scanReset.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// add view for button
				View panelProfile = findViewById(R.id.panelNfc);
				panelProfile.setVisibility(View.VISIBLE);

				View panelSettings = findViewById(R.id.panelVisual);
				panelSettings.setVisibility(View.GONE);

				View panelPrivacy = findViewById(R.id.panelNetwork);
				panelPrivacy.setVisibility(View.GONE);  
				
				View panelResult = findViewById(R.id.panelResult);
				panelResult.setVisibility(View.GONE);
		}});
		
		/* STEP 4 */
		
/*		btnRESULT.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// DO STUFF
				View panelProfile = findViewById(R.id.panelNfc);
				panelProfile.setVisibility(View.GONE);

				View panelSettings = findViewById(R.id.panelVisual);
				panelSettings.setVisibility(View.GONE);

				View panelPrivacy = findViewById(R.id.panelNetwork);
				panelPrivacy.setVisibility(View.GONE);
				
				View panelResult = findViewById(R.id.panelResult);
				panelResult.setVisibility(View.VISIBLE);

			}
		});	
*/	
		

	}

}