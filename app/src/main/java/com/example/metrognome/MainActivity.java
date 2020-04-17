package com.example.metrognome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.R.drawable;

public class MainActivity extends AppCompatActivity {
    private int BPM = 60;
    private boolean play = false;
    private Metronome metronome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metronome = new Metronome(this);
        TextView bpmView = (TextView) findViewById(R.id.bpmView);
        bpmView.setText(""+BPM);
    }

    public void stepUpBPM(View view) {

    }

    public void stepDownBPM(View view) {

    }

    public void playPause(View v) {
        ImageButton button = (ImageButton) v;
        if(play) {
            play = false;
            button.setImageResource(drawable.ic_media_play);
            metronome.pause();
        } else {
            play = true;
            button.setImageResource(drawable.ic_media_pause);
            metronome.play();
        }
    }
}
