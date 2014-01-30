package com.example.accordionwidget;

import java.io.IOException;
import java.nio.charset.Charset;

import com.example.accordionwidget.R;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;

public class ScanActivity extends Activity {
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private TextView mText;
    private NfcAdapter mAdapter;
    private View panelNFC;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
	//	addListenerOnButton();
		
		getWindow().setFormat(PixelFormat.UNKNOWN);

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
		
		mText = (TextView) findViewById(R.id.step1);
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        try {
            ndef.addDataType("*/*");
        } catch (MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        mFilters = new IntentFilter[] {
                ndef,
        };
        // Setup a tech list for all NfcF tags
        mTechLists = new String[][] { new String[] { MifareUltralight.class.getName() } };
		/* while (panelNFC is visible) {
			enable nfc scanning
			first display "Place coin near device to scan"
			change text "Scanning..."
			do the nfc stuff in background
			
		}*/
		while (panelNFC.isShown()) {
			//do stuff when visible
	        Intent intent = getIntent();
	        resolveIntent(intent);
	        // add which code
	        
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

							View panelPrivacy = findViewById(R.id.panelNetwork);
							panelPrivacy.setVisibility(View.GONE);
							
							View panelResult = findViewById(R.id.panelResult);
							panelResult.setVisibility(View.GONE);
							
							Button buttonTakePicture = (Button) findViewById(R.id.scanqrcode);
							buttonTakePicture.setOnClickListener(new Button.OnClickListener() {

								@Override
								public void onClick(View arg0) {
									Intent intent = new Intent("com.google.zxing.client.android.SCAN");
				                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				                    startActivityForResult(intent, 0);
								}
							});
						}
					});
	               popupWindow.showAsDropDown(btnOpenPopup, 50, -30);	         
			}});
		}
		
		/* STEP 2 */
	
		final Button visualScanEnter = (Button) findViewById(R.id.scanqrcode);
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
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan
                //TextView serialNumber = (TextView) findViewById(R.id.entrytext);
                //serialNumber.setText(contents);
                
                // maybe show a picture instead
                
                Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format , Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
                
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Toast toast = Toast.makeText(this, "Scan was Cancelled!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
                
            }
        }
    }
	
	void resolveIntent(Intent intent) {
        String action = intent.getAction();
        // Check if triggered by tag
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            // Get TAG instance
            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            // Get Mifare from tag instance above
            MifareUltralight mfc = MifareUltralight.get(tagFromIntent);
            try {
				mfc.connect();
				// read byte pages 5-8 
	            byte[] payload = mfc.readPages(5);
	            String Payload2String = new String(payload, Charset.forName("US-ASCII")).trim();
                Log.e("NFC payload", Payload2String);
	            System.out.println(Payload2String);
	            mText.setText(Payload2String);
	            // once NFC payload is set to text, turn off the panel
	            //panelNFC.setVisibility(View.GONE);
	            
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}       
            }   
    }
	
	@Override
    public void onResume() {
        super.onResume();
        mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
    }
    // this works 
    @Override
    public void onNewIntent(Intent intent) {
        Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);
        resolveIntent(intent);           
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.disableForegroundDispatch(this);
    }

}