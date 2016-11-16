package com.ezardlabs.dethsquare.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class Utils {
	public static final Platform PLATFORM = Platform.DESKTOP;
	public static String assetsPath = "app/src/main/java/resources/";
	public static String overrideAssetsPath = null;
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

	public static BufferedReader getReader(String path) throws IOException {
		if(overrideAssetsPath != null) {
			path = overrideAssetsPath + path;
		}
		return new BufferedReader(new InputStreamReader(
				Thread.currentThread().getContextClassLoader()
					  .getResourceAsStream(path)));
	}

	/*public static void onScreenSizeChanged(int width, int height) {
		Camera.main.bounds.set(0, 0, width, height);
	}

	public static void setCameraPosition(Camera camera) {
		if (camera != null) {
		}
	}*/

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