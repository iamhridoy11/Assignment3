package com.corebit.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {
    DatabaseReference db;
    float gxv;
    float gyv;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private Button saveBtn;
    private Button listBtn;

    private Display mDisplay;


    private TextView valueOfX;
    private TextView valueOfY;
    public Button roundedButton;

    float x,y;

    String timestamp;
    List<listshow> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn = (Button) findViewById(R.id.saveButton);
        listBtn = (Button) findViewById(R.id.listViewButton);
        db = FirebaseDatabase.getInstance().getReference("Values");

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = db.push().getKey();
                String gx = Float.toString(gxv);
                String gy = Float.toString(gyv);

                listshow timexy = new listshow(gx, gy);
                db.child(id).setValue(timexy);

                




            }
        });



        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ListDetails.class);
                startActivity(intent);


            }
        });


        valueOfX = findViewById(R.id.valueX);
        valueOfY = findViewById(R.id.valueY);
        roundedButton = findViewById(R.id.roundBtn);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        x = valueOfX.getX()+320;
        y = valueOfY.getY()+325;

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        mDisplay = wm.getDefaultDisplay();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAccelerometer != null) {
            mSensorManager.registerListener(this, mAccelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        gxv  = event.values[0];
        gyv  = event.values[1];


        valueOfX.setText("X: "+String.valueOf(gxv));
        valueOfY.setText("Y: "+String.valueOf(gyv));

        roundedButton.animate().x(x + gxv*50).y(y + gyv*50).setDuration(500).start();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {

    }
}
