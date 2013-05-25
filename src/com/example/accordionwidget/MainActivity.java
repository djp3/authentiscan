// This is MainActivity 4/4/13

package com.example.accordionwidget;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import com.example.accordionwidget.R;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.app.PendingIntent;
import android.util.Log;
import android.view.Menu;
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
import android.content.IntentFilter.MalformedMimeTypeException;

public class MainActivity extends Activity {
	public static final String MIME_TEXT_PLAIN = "text/plain";
	public static final String TAG = "";
	private TextView mTextView;
	private NfcAdapter mNfcAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	//	addListenerOnButton();
		mTextView = (TextView) findViewById(R.id.step1);
		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

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
		final Button btnOpenPopup = (Button) findViewById(R.id.nfcnext);
		btnOpenPopup.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				LayoutInflater layoutInflater = 
						(LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
				View popupView = layoutInflater.inflate(R.layout.popup, null);
				final PopupWindow popupWindow = new PopupWindow(popupView,
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

				Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
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
	@Override
	protected void onResume() {
		super.onResume();
		setupForegroundDispatch(this, mNfcAdapter);
	}
	
	@Override
	protected void onPause() {
		stopForegroundDispatch(this, mNfcAdapter);
		super.onPause();
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		/*
		 * Gets called when the user attaches a tag to the device.
		 */
		handleIntent(intent);
	}
	
	private void handleIntent(Intent intent) {
		String action = intent.getAction();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
			String type = intent.getType();
			if (MIME_TEXT_PLAIN.equals(type)) {
				Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
				new NdefReaderTask().execute(tag);
				
			} else {
				Log.d(TAG, "Wrong mime type: " + type);
			}
		}
	}
	
	public static void setupForegroundDispatch(final Activity activity, NfcAdapter adapter) {
		final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

		final PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);

		IntentFilter[] filters = new IntentFilter[1];
		String[][] techList = new String[][]{};

		// Notice that this is the same filter as in our manifest.
		filters[0] = new IntentFilter();
		filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
		filters[0].addCategory(Intent.CATEGORY_DEFAULT);
		try {
			filters[0].addDataType(MIME_TEXT_PLAIN);
		} catch (MalformedMimeTypeException e) {
			throw new RuntimeException("Check your mime type.");
		}
		
		adapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
	}

	public static void stopForegroundDispatch(final Activity activity, NfcAdapter adapter) {
		adapter.disableForegroundDispatch(activity);
	}
	
	private class NdefReaderTask extends AsyncTask<Tag, Void, String> {

		@Override
		protected String doInBackground(Tag... params) {
			Tag tag = params[0];
			
			Ndef ndef = Ndef.get(tag);
			if (ndef == null) {
				// NDEF is not supported by this Tag. 
				return null;
			}

			NdefMessage ndefMessage = ndef.getCachedNdefMessage();

			NdefRecord[] records = ndefMessage.getRecords();
			for (NdefRecord ndefRecord : records) {
				if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
					try {
						return readText(ndefRecord);
					} catch (UnsupportedEncodingException e) {
						Log.e(TAG, "Unsupported Encoding", e);
					}
				}
			}
			return null;
		}
		
		private String readText(NdefRecord record) throws UnsupportedEncodingException {
			byte[] payload = record.getPayload();
			String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
			int languageCodeLength = payload[0] & 0063;
			return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
		}
		
		@Override
		protected void onPostExecute(String result) {
			if (result != null) {
				mTextView.setText("Tag: " + result);
			}
		}
	}	
	}