package com.example.metrognome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int BPM = 60;
    private Metronome metronome = new Metronome(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView bpmView = (TextView) findViewById(R.id.bpmView);
        bpmView.setText(""+BPM);
    }

    public void stepUpBPM(View view) {

    }

    public void stepDownBPM(View view) {

    }
}
