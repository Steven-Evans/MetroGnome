package com.example.metrognome;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;

public class Metrognome extends Metronome {

    private int animationCounter = 1;

    public Metrognome(final Context context, final ImageView gnomeView) {
        super(context);

        final int[] frames = context.getResources().getIntArray(R.array.frames);
        final int maxFrames = frames.length;
        setGnomeImage(context, gnomeView, frames[animationCounter]);

        Runnable gnomeClicker = new Runnable() {
            public void run() {
                final MediaPlayer mp = MediaPlayer.create(context, R.raw.sound1);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    };
                });

               /* InputStream is = context.getResources().openRawResource(R.raw.);
                int sizeOfInputStram = is.available();*/

                setGnomeImage(context, gnomeView, frames[animationCounter]);
                animationCounter++;
                animationCounter %= maxFrames;
            }
        };
        super.setRunnable(gnomeClicker);
    }

    private void setGnomeImage(Context context, ImageView gnomeView, int index) {
        try {
            Bitmap bmp= BitmapFactory.decodeStream(context.getAssets().open("gnome-child-dance_"+index+".png"));
            gnomeView.setImageBitmap(bmp);
        } catch (IOException e) {
            Log.e("Metrognome", e.getMessage());
        }
    }

    @Override
    public void setBPM(int BPM) {
        super.setBPM(BPM);
    }

    @Override
    public void increaseBPM(int diff) {
        super.increaseBPM(diff);
    }

    @Override
    public void decreaseBPM(int diff) {
        super.decreaseBPM(diff);
    }
}
