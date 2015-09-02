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
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public abstract class BaseGame extends JFrame {
	private VolatileImage vBuffer;
	public static Graphics2D graphics;
	public static ImageObserver imageObserver;

	public BaseGame() {
		setTitle("Lost Sector");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
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
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
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
		setFocusable(true);
		onResize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		create();
		GameObject.startAll();
		Renderer.init();
		Collider.init();
		setVisible(true);
		vBuffer = createVolatileImage(getWidth(), getHeight());
	}

	@Override
	public void paint(Graphics g) {
		long start = System.currentTimeMillis();
		if (vBuffer == null) {
			repaint();
			return;
		}
		update();
		do {
			if (vBuffer.validate(getGraphicsConfiguration()) == VolatileImage.IMAGE_INCOMPATIBLE) {
				vBuffer = createVolatileImage(getWidth(), getHeight());
			}
			graphics = vBuffer.createGraphics();
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, getWidth(), getHeight());
			render();
			graphics.dispose();
		} while (vBuffer.contentsLost());
		g.drawImage(vBuffer, 0, 0, this);
		if (System.currentTimeMillis() - start < 16) {
			try {
				Thread.sleep(16 - (System.currentTimeMillis() - start));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		repaint();
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
		vBuffer = createVolatileImage(width, height);
		imageObserver = this;
	}
}
