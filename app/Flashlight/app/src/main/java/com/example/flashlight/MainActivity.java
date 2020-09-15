package com.example.flashlight;

//import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends Activity {
    //parameters get initiated
    private ImageButton btn;

    private Camera mcamera;
    private boolean FlashOn;
    private boolean Flash;
    private Parameters fparams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // flash switch button(on / off)
        btn = (ImageButton) findViewById(R.id.btn);
        /*
         * First check if device is supporting flashlight or not
         */
        Flash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!Flash) {
            // if the device doesn't support flash
            // Show alert message and close the application
            AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                    .create();
            alert.setTitle("Error");
            alert.setMessage("Sorry, your device doesn't support flash light!");
            alert.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // closing the application
                    finish();
                }
            });
            alert.show();
            return;
        }
        // get the camera
        getCamera();

        // displaying button image
        toggleButtonImage();
        /*
         * Switch button click event to toggle flash on/off
         */
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (FlashOn) {
                    // turn off flash
                    turnOffFlash();
                } else {
                    // turn on flash
                    turnOnFlash();
                }
            }
        });
    }//endof onCreate()

    /*
     * Get the camera
     */
    private void getCamera() {
        if (mcamera == null) {
            try {
                mcamera = Camera.open();
                fparams = mcamera.getParameters();
            } catch (RuntimeException e) {
                Log.e("Camera Error. Failed ", e.getMessage());
            }
        }
    }

    /*
     * Turning On flash
     */
    private void turnOnFlash() {
        if (!FlashOn) {
            if (mcamera == null || fparams == null) {
                return;
            }
            // play sound
//            playSound();

            fparams = mcamera.getParameters();
            fparams.setFlashMode(Parameters.FLASH_MODE_TORCH);
            mcamera.setParameters(fparams);
            mcamera.startPreview();
            FlashOn = true;

            // changing button/switch image
            toggleButtonImage();
        }

    }

    /*
     * Turning Off flash
     */
    private void turnOffFlash() {
        if (FlashOn) {
            if (mcamera == null || fparams == null) {
                return;
            }
            // play sound
            // playSound();

            fparams = mcamera.getParameters();
            fparams.setFlashMode(Parameters.FLASH_MODE_OFF);
            mcamera.setParameters(fparams);
            mcamera.stopPreview();
            FlashOn = false;

            // changing button/switch image
            toggleButtonImage();
        }
    }
    /*
     * Toggle switch button images changing image states to on / off
     */
    private void toggleButtonImage() {
        if (FlashOn) {
            btn.setImageResource(R.drawable.btn_on);
        } else {
            btn.setImageResource(R.drawable.btn_off);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // on pause turn off the flash
        turnOffFlash();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // on resume turn on the flash
        if (Flash)
            turnOnFlash();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // on starting the app get the camera params
        getCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // on stop release the camera
        if (mcamera != null) {
            mcamera.release();
            mcamera = null;
        }
    }

}//end of MainActivity