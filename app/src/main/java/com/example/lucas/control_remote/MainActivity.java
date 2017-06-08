package com.example.lucas.control_remote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private float currentXPosition = 0.0F;
    private float currentYPosition = 0.0F;
    private final int THRESHOLD = 4;
    private String ip = "192.168.1.7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void qrScanner(View view){
        try{
            mScannerView = new ZXingScannerView(this);
            setContentView(mScannerView);
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void handleResult(Result rawResult) {
        mScannerView.removeAllViews();
        mScannerView.stopCamera();
        setContentView(R.layout.activity_main);

        ip = rawResult.getText();
        Toast.makeText(this, ip,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPause(){
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        int x, y = 0;
        int action = me.getAction();
        if (action == MotionEvent.ACTION_MOVE) {
            x = getDirection(currentXPosition, me.getX());
            y = getDirection(currentYPosition, me.getY());

            if(x != 0 || y != 0) {
                currentXPosition = me.getX();
                currentYPosition = me.getY();

                try {
                    new RetrieveFeedTask().execute("mouse", ip, Integer.toString(x),
                                                                Integer.toString(y));
                } catch (Exception e) {
                    String msg = e.getMessage();
                }
            }
        }

        return true;
    }

    private int getDirection(float lastPosition, float currentPosition){
        int result = 0;
        if(!((lastPosition - THRESHOLD) <= currentPosition && currentPosition <= (lastPosition + THRESHOLD))) {
            if (lastPosition > currentPosition) {
                result = -1;
            } else if (lastPosition < currentPosition) {
                result = 1;
            }
        }

        return result;
    }

    public void btnEnterListener(View view){
        new RetrieveFeedTask().execute("click", ip);
    }

    public void btnNextListener(View view){
        new RetrieveFeedTask().execute("right", ip);
    }

    public void btnPrevListener(View view){
        new RetrieveFeedTask().execute("left", ip);
    }

    public void btnUpListener(View view){
        new RetrieveFeedTask().execute("up", ip);
    }

    public void btnDownListener(View view){
        new RetrieveFeedTask().execute("down", ip);
    }

    public void btnAudioUpListener(View view){
        new RetrieveFeedTask().execute("increase", ip);
    }

    public void btnAudioDownListener(View view){
        new RetrieveFeedTask().execute("lower", ip);
    }
}