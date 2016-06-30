package com.ezardlabs.dethsquare.util;

import com.ezardlabs.dethsquare.AudioSource.AudioClip;
import com.ezardlabs.dethsquare.Camera;
import com.ezardlabs.dethsquare.Screen;
import com.ezardlabs.dethsquare.util.audio.OggMusic;

import org.apache.commons.io.IOUtils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

public class Utils {
	public static final Platform PLATFORM = Platform.DESKTOP;
	public static String assetsPath = "assets/";
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

	public static int[] loadImage(String path) {
		if(overrideAssetsPath != null) {
			path = overrideAssetsPath + path;
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

	public static File loadFile(String path) throws IOException {
		if(overrideAssetsPath != null) {
			path = overrideAssetsPath + path;
		} else {
			path = assetsPath + path;
		}
		return new File(path);
	}

	public static void render(int textureName, FloatBuffer vertexBuffer, FloatBuffer uvBuffer,
			int numIndices, ShortBuffer indexBuffer) {
		temp = images.get(textureName);
		float[] vertices = new float[vertexBuffer.capacity()];
		vertexBuffer.position(0);
		vertexBuffer.get(vertices);
		float[] uvs = new float[uvBuffer.capacity()];
		uvBuffer.position(0);
		uvBuffer.get(uvs);
		for (int i = 0; i < numIndices / 6; i++) {
			BaseGame.graphics.drawImage(temp,
					(int) (vertices[i * 12] - (Camera.main.transform.position.x * Screen.scale)),
					(int) (vertices[(i * 12) + 4] -
							(Camera.main.transform.position.y * Screen.scale)),
					(int) (vertices[(i * 12) + 6] -
							(Camera.main.transform.position.x * Screen.scale)),
					(int) (vertices[(i * 12) + 10] -
							(Camera.main.transform.position.y * Screen.scale)),
					(int) (uvs[i * 8] * temp.getWidth()),
					(int) (uvs[(i * 8) + 5] * temp.getHeight()),
					(int) (uvs[(i * 8) + 4] * temp.getWidth()),
					(int) (uvs[(i * 8) + 1] * temp.getHeight()), BaseGame.imageObserver);
		}
	}

	public static void onScreenSizeChanged(int width, int height) {
		Camera.main.bounds.set(0, 0, width, height);
	}

	public static void setCameraPosition(Camera camera) {
		if (camera != null) {
		}
	}

	private static HashMap<AudioClip, Thread> playingAudio = new HashMap<>();

	public static void playAudio(final AudioClip audioClip) {
		playAudio(audioClip, false);
	}

	public static void playAudio(final AudioClip audioClip, final boolean loop) {
		Thread t = new Thread() {
			byte[] data;

			@Override
			public void run() {
				OggMusic ogg = new OggMusic(Thread.currentThread());
				ogg.setVolume(100);
				ogg.setMute(false);
				try {
					data = IOUtils.toByteArray(Thread.currentThread().getContextClassLoader()
											  .getResourceAsStream(audioClip.getPath()));
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
				do {
					ogg.playOgg(new ByteArrayInputStream(data));
				} while(loop);
			}
		};
		playingAudio.put(audioClip, t);
		t.start();
	}

	public static void stopAudio(AudioClip audioClip) {
		if (playingAudio.containsKey(audioClip)) {
			playingAudio.remove(audioClip).stop();
		}
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