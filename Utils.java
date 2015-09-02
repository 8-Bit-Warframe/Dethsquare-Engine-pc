package com.ezardlabs.dethsquare.util;

import com.ezardlabs.dethsquare.Camera;
import com.ezardlabs.dethsquare.Screen;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Utils {
	public static final Platform PLATFORM = Platform.DESKTOP;
	private static final ArrayList<BufferedImage> images = new ArrayList<>();
	private static BufferedImage temp = null;

	public enum Platform {
		ANDROID,
		DESKTOP
	}

	public static int[] loadImage(String path) {
		if (Thread.currentThread().getContextClassLoader()
				  .getResourceAsStream(path.replace("assets/", "")) == null)
			throw new Error("Image at " + path + " could not be " +
					"found");
		try {
			images.add(temp = ImageIO.read(new BufferedInputStream(
					Thread.currentThread().getContextClassLoader()
						  .getResourceAsStream(path.replace("assets/", "")))));
		} catch (IOException e) {
			e.printStackTrace();
			return new int[3];
		}
		return new int[]{images.size() - 1, temp.getWidth(), temp.getHeight()};
	}

	public static BufferedReader getReader(String path) throws IOException {
		return new BufferedReader(new InputStreamReader(
				Thread.currentThread().getContextClassLoader()
					  .getResourceAsStream(path.replace("assets/", ""))));
	}

	public static void render(int textureName, FloatBuffer vertexBuffer, FloatBuffer uvBuffer,
			short[] indices, ShortBuffer indexBuffer) {
		temp = images.get(textureName);
		float[] vertices = new float[vertexBuffer.capacity()];
		vertexBuffer.position(0);
		vertexBuffer.get(vertices);
		float[] uvs = new float[uvBuffer.capacity()];
		uvBuffer.position(0);
		uvBuffer.get(uvs);
		for (int i = 0; i < vertices.length / 12; i++) {
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
}