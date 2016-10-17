package com.ezardlabs.dethsquare.util;

import com.ezardlabs.dethsquare.util.audio.OggMusic;

import org.apache.commons.io.IOUtils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

public class Utils {
	public static final Platform PLATFORM = Platform.DESKTOP;
	public static String assetsPath = "app/src/main/java/resources/";
	public static String overrideAssetsPath = null;
	private static final ArrayList<BufferedImage> images = new ArrayList<>();
	private static BufferedImage temp = null;
	private static final Preferences prefs = Preferences.userRoot().node("com/ezardlabs/lostsector");

	public enum Platform {
		ANDROID,
		DESKTOP
	}

	public static void init() {

	}

	public static String[] getAllFileNames(String dirPath) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Thread
				.currentThread().getContextClassLoader().getResourceAsStream(dirPath + "/files" +
						".lsd")))) {
			ArrayList<String> list = new ArrayList<>();
			String temp;
			while((temp = reader.readLine()) != null) {
				list.add(temp);
			}
			return list.toArray(new String[list.size()]);
		} catch (IOException e) {
			System.err.println("Failed to load " + dirPath + "files.lsd");
			e.printStackTrace();
			return new String[0];
		}
	}

	public static int[] loadImage(String path) {
		if(overrideAssetsPath != null) {
			path = overrideAssetsPath + path;
		}
		try {
			path = new URI(path).normalize().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if (Thread.currentThread().getContextClassLoader()
				  .getResourceAsStream(path) == null)
			throw new Error("Image at " + path + " could not be " +
					"found");
		try {
			images.add(temp = ImageIO.read(new BufferedInputStream(
					Thread.currentThread().getContextClassLoader()
						  .getResourceAsStream(path))));
		} catch (IOException e) {
			e.printStackTrace();
			return new int[3];
		}
		return new int[]{images.size() - 1, temp.getWidth(), temp.getHeight()};
	}

	public static BufferedReader getReader(String path) throws IOException {
		if(overrideAssetsPath != null) {
			path = overrideAssetsPath + path;
		}
		return new BufferedReader(new InputStreamReader(
				Thread.currentThread().getContextClassLoader()
					  .getResourceAsStream(path)));
	}

	public static void render(int textureName, FloatBuffer vertexBuffer, FloatBuffer uvBuffer,
			int numIndices, ShortBuffer indexBuffer, float cameraPosX, float cameraPosY, float
			scale) {
		temp = images.get(textureName);
		float[] vertices = new float[vertexBuffer.capacity()];
		vertexBuffer.position(0);
		vertexBuffer.get(vertices);
		float[] uvs = new float[uvBuffer.capacity()];
		uvBuffer.position(0);
		uvBuffer.get(uvs);
		for (int i = 0; i < numIndices / 6; i++) {
//			System.out.println(textureName + ", " + ((int) (vertices[i * 12] - (cameraPosX *
//					scale))) + ", " + ((int) (vertices[(i * 12) + 4] - (cameraPosY * scale))));
			BaseGame.graphics.drawImage(temp,
					(int) (vertices[i * 12] - (cameraPosX * scale)),
					(int) (vertices[(i * 12) + 4] -
							(cameraPosY * scale)),
					(int) (vertices[(i * 12) + 6] -
							(cameraPosX * scale)),
					(int) (vertices[(i * 12) + 10] -
							(cameraPosY * scale)),
					(int) (uvs[i * 8] * temp.getWidth()),
					(int) (uvs[(i * 8) + 5] * temp.getHeight()),
					(int) (uvs[(i * 8) + 4] * temp.getWidth()),
					(int) (uvs[(i * 8) + 1] * temp.getHeight()), BaseGame.imageObserver);
		}
	}

	/*public static void onScreenSizeChanged(int width, int height) {
		Camera.main.bounds.set(0, 0, width, height);
	}

	public static void setCameraPosition(Camera camera) {
		if (camera != null) {
		}
	}*/

	public static void destroyAllTextures(HashMap<String, int[]> textures) {
		images.clear();
	}

	private static HashMap<Integer, AudioThread> playingAudio = new HashMap<>();

	static class AudioThread extends Thread {
		private final String path;
		private boolean loop = false;
		private int volume = 100;
		private byte[] data;
		private OggMusic ogg;

		AudioThread(String path) {
			this.path = path;
		}

		void setLoop(boolean loop) {
			this.loop = loop;
		}

		void setVolume(int volume) {
			this.volume = volume;
			if (ogg != null) ogg.setVolume(volume);
		}

		@Override
		public void run() {
			ogg = new OggMusic(Thread.currentThread());
			ogg.setVolume(volume);
			ogg.setMute(false);
			try {
				data = IOUtils.toByteArray(Thread.currentThread().getContextClassLoader()
												 .getResourceAsStream(path));
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			do {
				ogg.playOgg(new ByteArrayInputStream(data));
			} while(loop);
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

	public static void setBoolean(String key, boolean value) {
		prefs.putBoolean(key, value);
	}

	public static void setInt(String key, int value) {
		prefs.putInt(key, value);
	}

	public static void setFloat(String key, float value) {
		prefs.putFloat(key, value);
	}

	public static void setString(String key, String value) {
		prefs.putByteArray(key, value.getBytes(Charset.forName("UTF-8")));
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		return prefs.getBoolean(key, defaultValue);
	}

	public static int getInt(String key, int defaultValue) {
		return prefs.getInt(key, defaultValue);
	}

	public static float getFloat(String key, float defaultValue) {
		return prefs.getFloat(key, defaultValue);
	}

	public static String getString(String key, String defaultValue) {
		return new String(prefs.getByteArray(key, defaultValue.getBytes(Charset.forName("UTF-8"))), Charset.forName("UTF-8"));
	}
}