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
    private boolean play = false;
    private Metronome metronome;
    private EditText bpmView;
    private SeekBar bpmBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metronome = new Metronome(this);
        bpmView = findViewById(R.id.bpmView);
        bpmBar = (SeekBar) findViewById(R.id.bpmBar);
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

        bpmBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //metronome.setBPM(progress);
                bpmView.setText(String.valueOf(progress));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue,
                //        Toast.LENGTH_SHORT).show();
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
