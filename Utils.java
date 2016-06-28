package com.ezardlabs.dethsquare.util;

import com.ezardlabs.dethsquare.AudioSource.AudioClip;
import com.ezardlabs.dethsquare.Camera;
import com.ezardlabs.dethsquare.Screen;
import com.ezardlabs.dethsquare.Vector2;
import com.ezardlabs.dethsquare.util.audio.OggMusic;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.prefs.Preferences;

public class Utils {
	public static final Platform PLATFORM = Platform.DESKTOP;
	private static final Preferences prefs = Preferences.userRoot()
														.node("com/ezardlabs/lostsector");
	private static GL2 gl2;

	public enum Platform {
		ANDROID,
		DESKTOP
	}

	public static void init() {

	}

	public static void setGL2(GL2 gl2) {
		Utils.gl2 = gl2;
	}

	public static int[] loadImage(String path) {
		int[] returnVals = new int[3];
		Texture t = null;

		try {
			t = TextureIO.newTexture(Thread.currentThread().getContextClassLoader()
										   .getResourceAsStream(path.replace("assets/", "")), false,
					".png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (t != null) {
			returnVals[0] = t.getTextureObject();
			returnVals[1] = t.getWidth();
			returnVals[2] = t.getHeight();
		}
		return returnVals;
	}

	public static BufferedReader getReader(String path) throws IOException {
		return new BufferedReader(new InputStreamReader(
				Thread.currentThread().getContextClassLoader()
					  .getResourceAsStream(path.replace("assets/", ""))));
	}

	public static int loadShader(int type, String shaderCode) {
		int shader = gl2.glCreateShader(type);

		gl2.glShaderSource(shader, 1, new String[]{shaderCode}, null, 0);
		gl2.glCompileShader(shader);

		return shader;
	}

	private static int vPositionLoc;
	private static int texCoordLoc;
	private static int mtrxLoc;
	private static int textureLoc;

	private static boolean inited = false;

	public static void render(int textureName, FloatBuffer vertexBuffer, FloatBuffer uvBuffer,
			int numIndices, ShortBuffer indexBuffer) {
		if (!inited) {
			vPositionLoc = gl2.glGetAttribLocation(ShaderTools.spImage, "vPosition");
			texCoordLoc = gl2.glGetAttribLocation(ShaderTools.spImage, "a_texCoord");
			mtrxLoc = gl2.glGetUniformLocation(ShaderTools.spImage, "uMVPMatrix");
			textureLoc = gl2.glGetUniformLocation(ShaderTools.spImage, "s_texture");
			inited = true;
		}

		gl2.glBindTexture(GL2.GL_TEXTURE_2D, textureName);

		gl2.glVertexAttribPointer(vPositionLoc, 3, GL2.GL_FLOAT, false, 0, vertexBuffer);
		gl2.glEnableVertexAttribArray(vPositionLoc);

		gl2.glVertexAttribPointer(texCoordLoc, 2, GL2.GL_FLOAT, false, 0, uvBuffer);
		gl2.glEnableVertexAttribArray(texCoordLoc);

		gl2.glUniformMatrix4fv(mtrxLoc, 1, false, mtrxProjectionAndView, 0);

		gl2.glUniform1i(textureLoc, 0);

		gl2.glDrawElements(GL2.GL_TRIANGLES, numIndices, GL2.GL_UNSIGNED_SHORT, indexBuffer);

		gl2.glDisableVertexAttribArray(vPositionLoc);
		gl2.glDisableVertexAttribArray(texCoordLoc);
	}

	public static void onScreenSizeChanged(int width, int height) {
		gl2.glViewport(0, 0, width, height);

		for (int i = 0; i < 16; i++) {
			mtrxProjection[i] = 0.0f;
			mtrxView[i] = 0.0f;
			mtrxProjectionAndView[i] = 0.0f;
		}

		// Setup our screen width and height for normal sprite translation.
		Matrix.orthoM(mtrxProjection, 0, 0f, width, height, 0.0f, 0, 50);

		// Set the camera position (view matrix)
		Matrix.setLookAtM(mtrxView, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

		// Calculate the projection and view transformation
		Matrix.multiplyMM(mtrxProjectionAndView, 0, mtrxProjection, 0, mtrxView, 0);

		Camera.main.bounds.set(0, 0, width, height);
	}

	private static final float[] mtrxProjection = new float[16];
	private static final float[] mtrxView = new float[16];
	private static final float[] mtrxProjectionAndView = new float[16];

	public static void setCameraPosition(Camera camera) {
		camera.transform.position = new Vector2();
		if (camera != null) {
			Matrix.setLookAtM(mtrxView, 0, 0f, 0f, 1f, 0f, 0f, -100f, 0f, 1.0f, 0.0f);
			Matrix.translateM(mtrxView, 0, (int) -camera.transform.position.x * Screen.scale,
					(int) -camera.transform.position.y * Screen.scale, 0);
			Matrix.multiplyMM(mtrxProjectionAndView, 0, mtrxProjection, 0, mtrxView, 0);
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
				} while (loop);
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
		return new String(prefs.getByteArray(key, defaultValue.getBytes(Charset.forName("UTF-8"))),
				Charset.forName("UTF-8"));
	}
}