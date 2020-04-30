package com.example.metrognome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.R.drawable;

public class MainActivity extends AppCompatActivity {
    private final int MIN_BPM = 40;
    private final int MAX_BPM = 240;
    private boolean play = false;
    private Metronome metronome;
    private EditText bpmView;
    private SeekBar bpmBar;

    //update all views
    private void onBPMChange(int bpm) {
        metronome.setBPM(bpm);

        bpmView = findViewById(R.id.bpmView);
        bpmView.setTag("notFromUser");
        bpmView.setText(String.valueOf(bpm));
        bpmView.setTag(null);

        bpmBar = (SeekBar) findViewById(R.id.bpmBar);
        bpmBar.setProgress(bpm - MIN_BPM);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metronome = new Metronome(this);

        bpmView = findViewById(R.id.bpmView);
        bpmView.setText(String.valueOf(metronome.getBPM()));
        bpmView.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String numberText = bpmView.getText().toString();
                if (!numberText.equals("") && bpmView.getTag() == null) {
                    int someInt = Integer.parseInt(bpmView.getText().toString());
                    onBPMChange(someInt);
                }
            }
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });

        bpmBar = (SeekBar) findViewById(R.id.bpmBar);
        bpmBar.setMax(MAX_BPM - MIN_BPM);
        bpmBar.setProgress(metronome.getBPM() - MIN_BPM);
        bpmBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    bpmView.setText(String.valueOf(progress));
                    onBPMChange(progress + MIN_BPM);
                }
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void stepUpBPM(View view) {
        int diff = 15;
        if (metronome.getBPM() + diff <= MAX_BPM) {
            metronome.increaseBPM(diff);
        } else {
            metronome.setBPM(MAX_BPM);
        }
        onBPMChange(metronome.getBPM());
    }

    public void stepDownBPM(View view) {
        int diff = 15;
        if (metronome.getBPM() - diff >= MIN_BPM) {
            metronome.decreaseBPM(diff);
        } else {
            metronome.setBPM(MIN_BPM);
        }
        onBPMChange(metronome.getBPM());
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
