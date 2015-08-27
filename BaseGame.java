package com.ezardlabs.dethsquare.util;

import com.ezardlabs.dethsquare.Collider;
import com.ezardlabs.dethsquare.GameObject;
import com.ezardlabs.dethsquare.Input;
import com.ezardlabs.dethsquare.Input.OnKeyListener;
import com.ezardlabs.dethsquare.Renderer;
import com.ezardlabs.dethsquare.Screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public abstract class BaseGame extends JFrame {
//	private BufferedImage buffer;
	private VolatileImage vBuffer;
	public static Graphics2D graphics;
	public static ImageObserver imageObserver;

	public BaseGame() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setUndecorated(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				for (OnKeyListener okl : Input.onKeyListeners) {
					okl.onKeyTyped(e.getKeyChar());
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				for (OnKeyListener okl : Input.onKeyListeners) {
					okl.onKeyDown(e.getKeyChar());
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				for (OnKeyListener okl : Input.onKeyListeners) {
					okl.onKeyUp(e.getKeyChar());
				}
			}
		});
		create();
		GameObject.startAll();
		Renderer.init();
		Collider.init();
		setVisible(true);
		onResize(getWidth(), getHeight());
	}

	@Override
	public void paint(Graphics g) {
		if (vBuffer == null) repaint();
		update();
		do {
			int returnCode = vBuffer.validate(getGraphicsConfiguration());
			if (returnCode == VolatileImage.IMAGE_RESTORED) {
				renderOffscreen();
			} else if (returnCode == VolatileImage.IMAGE_INCOMPATIBLE) {
				vBuffer = createVolatileImage(getWidth(), getHeight());
				renderOffscreen();
			}
			renderOffscreen();
			g.drawImage(vBuffer, 0, 0, this);
		} while (vBuffer.contentsLost());
		repaint();
	}

	private void renderOffscreen() {
		do {
			if (vBuffer.validate(getGraphicsConfiguration()) == VolatileImage.IMAGE_INCOMPATIBLE) {
				vBuffer = createVolatileImage(getWidth(), getHeight());
			}
			graphics = vBuffer.createGraphics();
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, getWidth(), getHeight());
			long start = System.currentTimeMillis();
			render();
			System.out.println(System.currentTimeMillis() - start);
			graphics.dispose();
		} while (vBuffer.contentsLost());
	}

	public abstract void create();

	void update() {
		GameObject.updateAll();
	}

	void render() {
		Renderer.renderAll();
	}

	void onResize(int width, int height) {
		Screen.scale = (float) width / 1920f;
		Screen.width = width;
		Screen.height = height;
//		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		vBuffer = createVolatileImage(width, height);
//		graphics = (Graphics2D) buffer.getGraphics();
		imageObserver = this;
	}
}
