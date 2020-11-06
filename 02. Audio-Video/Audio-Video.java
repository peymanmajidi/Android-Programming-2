package com.jabarian.adabazi;

import android.content.Context;
import android.media.MediaActionSound;
import android.media.MediaPlayer;

public class Player {
    public static MediaPlayer media;
    public static void Play(Context context, int file)
    {
        if(media !=null)
        {
            if(media.isPlaying())
            {
                media.seekTo(0);
                return;
            }

            media.start();
            return;
        }
        media = MediaPlayer.create(context, file);
        media.start(); // play
        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                media.release();
                media = null;
            }
        });
    }

    public static void Pause()
    {
        if(media !=null)
        {
            if(media.isPlaying())
                media.pause();

        }
    }
    public static void Stop()
    {
        if(media !=null)
        {
            if(media.isPlaying())
                media.stop();
            media.release(); // ***********
        }

    }
    public static void Beep()
    {
        MediaActionSound sound = new MediaActionSound();
        sound.play(MediaActionSound.STOP_VIDEO_RECORDING); // beeeeeeeeep
    }

}

// --------------------------------------------

public static void Video()
{
        VideoView videoview = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.mask);
        videoview.setVideoURI(uri);
        videoview.start();
}

