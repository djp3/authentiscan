package com.example.accordionwidget;

import java.io.IOException;

import com.example.accordionwidget.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;

public class ScanActivity extends Activity implements SurfaceHolder.Callback {
	
	private Camera camera;
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private boolean previewing = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
	//	addListenerOnButton();
		
		getWindow().setFormat(PixelFormat.UNKNOWN);
		surfaceView = (SurfaceView) findViewById(R.id.camerapreview);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		// controlInflater = LayoutInflater.from(getBaseContext());
		// View viewControl = controlInflater.inflate(R.layout.control, null);

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
						
						if (previewing) {
							camera.stopPreview();
							previewing = false;
						}

						if (camera != null) {
							try {
								camera.setPreviewDisplay(surfaceHolder);
								camera.startPreview();
								previewing = true;
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						final ShutterCallback myShutterCallback = new ShutterCallback() {

							@Override
							public void onShutter() {
								// TODO Auto-generated method stub

							}
						};

						final PictureCallback myPictureCallback_RAW = new PictureCallback() {

							@Override
							public void onPictureTaken(byte[] arg0, Camera arg1) {
								// TODO Auto-generated method stub

							}
						};

						final PictureCallback myPictureCallback_JPG = new PictureCallback() {

							@Override
							public void onPictureTaken(byte[] arg0, Camera arg1) {
								// TODO Auto-generated method stub
								Bitmap bitmapPicture = BitmapFactory.decodeByteArray(arg0, 0,
										arg0.length);
							}
						};
						
						Button buttonTakePicture = (Button) findViewById(R.id.snappic);
						buttonTakePicture.setOnClickListener(new Button.OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								camera.takePicture(myShutterCallback, myPictureCallback_RAW,
										myPictureCallback_JPG);

							}
						});
						
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

		
		
/*		OLD SNAPPIC (automatically shows serial number)
 * 		final Button snapPic = (Button) findViewById(R.id.snappic);
		snapPic.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// add view for button
				View panelSnappic1 = findViewById(R.id.panelSnappic1);
				panelSnappic1.setVisibility(View.GONE);
				
				View panelSnappic2 = findViewById(R.id.panelSnappic2);
				panelSnappic2.setVisibility(View.VISIBLE);     
		}});
*/		
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
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		if (previewing) {
			camera.stopPreview();
			previewing = false;
		}

		if (camera != null) {
			try {
				camera.setPreviewDisplay(surfaceHolder);
				camera.startPreview();
				previewing = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		camera = Camera.open();
		Parameters params = camera.getParameters();

		if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
			params.set("orientation", "portrait");
			camera.setDisplayOrientation(90);
		}

//		try {
//			camera.setPreviewDisplay(holder);
//			camera.startPreview();
//		} catch (IOException exception) {
//			camera.release();
//			camera = null;
//		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		camera.stopPreview();
		camera.release();
		camera = null;
		previewing = false;
	}

}