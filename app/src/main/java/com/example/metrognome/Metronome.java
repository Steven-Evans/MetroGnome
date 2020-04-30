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
    private int BPM;
    private boolean playing = false;
    private final Runnable clicker;
    private ScheduledFuture<?> clickerHandle;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Metronome(final Context context) {
        this(context, 60);
    }

    public Metronome(final Context context, int initialBPM) {
        BPM = initialBPM;
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

    public int getBPM() {
        return BPM;
    }

    public void setBPM(int BPM) {
        this.BPM = BPM;
        if (playing) {
            play();
        }
    }

    public void increaseBPM(int diff) {
        BPM += diff;
        if (playing) {
            play();
        }
    }

    public void decreaseBPM(int diff) {
        BPM -= diff;
        if (playing) {
            play();
        }
    }

    public void play() {
        if (clickerHandle != null) {
            clickerHandle.cancel(true);
        }
        if (BPM > 0) {
            clickerHandle = scheduler.scheduleAtFixedRate(clicker, 500, 60000/BPM, TimeUnit.MILLISECONDS);
        }
        playing = true;
    }

    public void pause() {
        clickerHandle.cancel(true);
        playing = false;
    }
}
