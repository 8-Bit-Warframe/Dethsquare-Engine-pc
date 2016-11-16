package com.ezardlabs.dethsquare.util;

import com.ezardlabs.dethsquare.util.audio.OggMusic;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

public class AudioUtils {
	private static HashMap<Integer, AudioThread> playingAudio = new HashMap<>();

	private static class AudioThread extends Thread {
		private final String path;
		private boolean loop = false;
		private int volume = 100;
		private byte[] data;
		private OggMusic ogg;

		private AudioThread(String path) {
			this.path = path;
		}

		private void setLoop(boolean loop) {
			this.loop = loop;
		}

		private void setVolume(int volume) {
			this.volume = volume;
			if (ogg != null) ogg.setVolume(volume);
		}

		@Override
		public void run() {
			ogg = new OggMusic(Thread.currentThread());
			ogg.setVolume(volume);
			ogg.setMute(false);
			try {
				data = IOUtils.toByteArray(
						Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			do {
				ogg.playOgg(new ByteArrayInputStream(data));
			} while (loop);
		}
	}

	public static void playAudio(final int id, final String path) {
		AudioThread at = new AudioThread(path);
		playingAudio.put(id, at);
		at.start();
	}

	public static void setAudioLoop(int id, boolean loop) {
		playingAudio.get(id).setLoop(loop);
	}

	public static void setAudioVolume(int id, int volume) {
		playingAudio.get(id).setVolume(volume);
	}

	public static void stopAudio(int id) {
		if (playingAudio.containsKey(id)) {
			playingAudio.remove(id).stop();
		}
	}

	public static void stopAllAudio() {
		playingAudio.values().forEach(Thread::stop);
	}
}
