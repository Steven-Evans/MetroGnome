package com.example.metrognome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.R.drawable;

public class MainActivity extends AppCompatActivity {
    private boolean play = false;
    private Metronome metronome;
    private EditText bpmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metronome = new Metronome(this);
        bpmView = findViewById(R.id.bpmView);

        bpmView.setText(String.valueOf(metronome.getBPM()));
        //bpmView.setText(""+BPM);

        bpmView.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String numberText = bpmView.getText().toString();
                //System.out.println("ay" + numberText + s.toString());
                if (numberText.equals("")) {

                } else {
                    int someInt = Integer.parseInt(bpmView.getText().toString());
                    metronome.setBPM(someInt);
                }
            }
        });
    }

    public void stepUpBPM(View view) {
        metronome.increaseBPM(15);
    }

    public void stepDownBPM(View view) {
        metronome.decreaseBPM(15);
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
