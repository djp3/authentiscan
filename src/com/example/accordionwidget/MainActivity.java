// This is MainActivity 4/4/13

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
import android.widget.PopupWindow;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	//	addListenerOnButton();

		Button btnNFC = (Button) findViewById(R.id.btnNFC);
		Button btnVISUAL = (Button) findViewById(R.id.btnVISUAL);
		Button btnNETWORK = (Button) findViewById(R.id.btnNETWORK);
		Button btnRESULT = (Button) findViewById(R.id.btnRESULT);

		final View panelNFC = findViewById(R.id.panelNfc);
		panelNFC.setVisibility(View.GONE);

		View panelVisual = findViewById(R.id.panelVisual);
		panelVisual.setVisibility(View.GONE);

		View panelNetwork = findViewById(R.id.panelNetwork);
		panelNetwork.setVisibility(View.GONE);
		
		View panelResult = findViewById(R.id.panelResult);
		panelResult.setVisibility(View.GONE);
		
		/* STEP 1*/
		btnNFC.setOnClickListener(new OnClickListener() {
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
		
		final Button btnOpenPopup = (Button) findViewById(R.id.scan);
		btnOpenPopup.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
						.getSystemService(LAYOUT_INFLATER_SERVICE);
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
		btnVISUAL.setOnClickListener(new OnClickListener() {
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

		/* STEP 3*/
		btnNETWORK.setOnClickListener(new OnClickListener() {
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
		final Button scanAccept = (Button) findViewById(R.id.accept);
		scanAccept.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// DETAILS/HISTORY POPUP
				
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
		
		/* STEP 4 */
		btnRESULT.setOnClickListener(new OnClickListener() {
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
	
		

	}

}