package com.xiong.shock;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * @author: xiong
 * @time: 2017/12/18
 * @说明: 响铃And震动
 */

public class MediaPlayerUtil {

    private static MediaPlayer mMediaPlayer = new MediaPlayer();
    private static int loopCount = 1;

    //开始播放
    public static void playRing(final Activity activity, final int count, final boolean IsVibrate) {
        if (!mMediaPlayer.isPlaying()) {
            try {
                Uri alert = Uri.parse("android.resource://" + activity.getPackageName() + "/" + R.raw.beep);//用于获取手机默认铃声的Uri
                mMediaPlayer.setDataSource(activity, alert);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);//告诉mediaPlayer播放的是铃声流
                mMediaPlayer.setLooping(false);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
                if (IsVibrate) {
                    VibrateUtil.vibrate(activity, new long[]{0, 2000, 1000, 2000}, 2);
                }
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        try {
                            if (loopCount == count) {
                                if (IsVibrate) {
                                    VibrateUtil.vibrateCancle(activity);
                                }
                                mMediaPlayer.reset();
                                loopCount = 1;
                            } else {
                                mMediaPlayer.start();
                                loopCount++;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //停止播放
    public static void stopRing(Activity activity) {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                VibrateUtil.vibrateCancle(activity);
            }
        }
    }
}
