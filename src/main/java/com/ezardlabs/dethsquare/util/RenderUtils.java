package com.ezardlabs.dethsquare.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class RenderUtils {
	private static final ArrayList<BufferedImage> images = new ArrayList<>();

	public static int[] loadImage(String path) {
		try {
			path = new URI(path).normalize().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if (Thread.currentThread().getContextClassLoader().getResourceAsStream(path) == null) {
			throw new Error("Image at " + path + " could not be found");
		}
		BufferedImage temp;
		try {
			images.add(temp = ImageIO.read(new BufferedInputStream(
					Thread.currentThread().getContextClassLoader().getResourceAsStream(path))));
		} catch (IOException e) {
			e.printStackTrace();
			return new int[3];
		}
		return new int[]{images.size() - 1,
				temp.getWidth(),
				temp.getHeight()};
	}

	public static void render(int textureName, FloatBuffer vertexBuffer, FloatBuffer uvBuffer,
			int numIndices, ShortBuffer indexBuffer, float cameraPosX, float cameraPosY,
			float scale) {
		BufferedImage temp = images.get(textureName);
		float[] vertices = new float[vertexBuffer.capacity()];
		vertexBuffer.position(0);
		vertexBuffer.get(vertices);
		float[] uvs = new float[uvBuffer.capacity()];
		uvBuffer.position(0);
		uvBuffer.get(uvs);
		for (int i = 0; i < numIndices / 6; i++) {
			BaseGame.graphics.drawImage(temp, (int) (vertices[i * 12] - (cameraPosX * scale)),
					(int) (vertices[(i * 12) + 4] - (cameraPosY * scale)),
					(int) (vertices[(i * 12) + 6] - (cameraPosX * scale)),
					(int) (vertices[(i * 12) + 10] - (cameraPosY * scale)),
					(int) (uvs[i * 8] * temp.getWidth()),
					(int) (uvs[(i * 8) + 5] * temp.getHeight()),
					(int) (uvs[(i * 8) + 4] * temp.getWidth()),
					(int) (uvs[(i * 8) + 1] * temp.getHeight()), BaseGame.imageObserver);
		}
	}

	public static void destroyAllTextures(HashMap<String, int[]> textures) {
		images.clear();
	}
}
