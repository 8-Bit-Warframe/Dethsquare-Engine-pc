package com.ezardlabs.dethsquare.util;

import java.nio.charset.Charset;
import java.util.prefs.Preferences;

public class Utils {
	public static final Platform PLATFORM = Platform.DESKTOP;
	private static final Preferences prefs = Preferences.userRoot().node("com/ezardlabs/lostsector");

	public enum Platform {
		ANDROID,
		DESKTOP
	}

	public static void init() {
		// Not used on this platform
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