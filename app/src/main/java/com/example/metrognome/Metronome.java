package com.example.metrognome;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Metronome {
    private int BPM = 60;

    public Metronome(Context context) {
        play(context);
    }

    public void increaseBPM(int diff) {
        BPM += diff;
    }

    public void decreaseBPM(int diff) {
        BPM -= diff;
    }

    private void play(final Context context) {
        TimerTask clickTask = new TimerTask() {
            public void run() {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.sound1);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    };
                });
                System.out.println("Task performed on " + new Date());
            }
        };
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(clickTask, 0, 60000/BPM);
    }

}
