package com.lib.media;

import com.lib.Debug;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class CSoundAssets {

	private Context context;
	private MediaPlayer mediaPlayer;
	private String path;

	public CSoundAssets(Context context, String path) {
		this.context = context;
		this.path = path;
		mediaPlayer = new MediaPlayer();
	}

	public void playLooping() {
		try {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
				mediaPlayer.release();
				mediaPlayer = new MediaPlayer();
			}
			mediaPlayer.reset();
			AssetFileDescriptor descriptor = context.getAssets().openFd(path);
			mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(),
					descriptor.getLength());
			descriptor.close();
			mediaPlayer.prepare();
			mediaPlayer.setVolume(100, 100);
			mediaPlayer.setLooping(true);
			mediaPlayer.start();
		} catch (Exception e) {
			Debug.e("Lỗi " + e.toString());
		}
	}

	public void play() {
		try {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
				mediaPlayer.release();
				mediaPlayer = new MediaPlayer();
			}
			mediaPlayer.reset();
			AssetFileDescriptor descriptor = context.getAssets().openFd(path);
			mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(),
					descriptor.getLength());
			descriptor.close();
			mediaPlayer.prepare();
			mediaPlayer.setVolume(100, 100);
			mediaPlayer.setLooping(false);
			mediaPlayer.start();
		} catch (Exception e) {
			Debug.e("Lỗi " + e.toString());
		}
	}

	public void stop() {
		mediaPlayer.stop();
	}
}
