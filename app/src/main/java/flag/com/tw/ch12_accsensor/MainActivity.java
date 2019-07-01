package flag.com.tw.ch12_accsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sm;
    TextView txv;
    Sensor sr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sr = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        txv = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        txv.setText(String.format("X軸： %1.2f\nY軸：%1.2f\nZ軸：%1.2f", event.values[0], event.values[1], event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume(){
        super.onResume();

        sm.registerListener(this, sr, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onStop(){
        super.onStop();

        sm.unregisterListener(this);
    }
}
