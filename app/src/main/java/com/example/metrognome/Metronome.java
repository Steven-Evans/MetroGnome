package com.example.metrognome;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Metronome {
    private int BPM = 60;
    private final Runnable clicker;
    private ScheduledFuture<?> clickerHandle;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Metronome(final Context context) {
        clicker = new Runnable() {
            public void run() {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.sound1);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    };
                });
            }
        };
    }

    public void increaseBPM(int diff) {
        BPM += diff;
        play();
    }

    public void decreaseBPM(int diff) {
        BPM -= diff;
        play();
    }

    public void play() {
        if (clickerHandle != null) {
            clickerHandle.cancel(true);
        }
        clickerHandle = scheduler.scheduleAtFixedRate(clicker, 0, 60000/BPM, TimeUnit.MILLISECONDS);
    }

    public void pause() {
        clickerHandle.cancel(true);
    }
}
