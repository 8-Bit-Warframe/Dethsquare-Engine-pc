package com.ezardlabs.dethsquare.util;

import com.ezardlabs.dethsquare.GameObject;
import com.ezardlabs.dethsquare.Input;
import com.ezardlabs.dethsquare.Input.KeyCode;
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
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_A:
						Input.setKeyDown(KeyCode.A);
						break;
					case KeyEvent.VK_B:
						Input.setKeyDown(KeyCode.B);
						break;
					case KeyEvent.VK_C:
						Input.setKeyDown(KeyCode.C);
						break;
					case KeyEvent.VK_D:
						Input.setKeyDown(KeyCode.D);
						break;
					case KeyEvent.VK_E:
						Input.setKeyDown(KeyCode.E);
						break;
					case KeyEvent.VK_F:
						Input.setKeyDown(KeyCode.F);
						break;
					case KeyEvent.VK_G:
						Input.setKeyDown(KeyCode.G);
						break;
					case KeyEvent.VK_H:
						Input.setKeyDown(KeyCode.H);
						break;
					case KeyEvent.VK_I:
						Input.setKeyDown(KeyCode.I);
						break;
					case KeyEvent.VK_J:
						Input.setKeyDown(KeyCode.J);
						break;
					case KeyEvent.VK_K:
						Input.setKeyDown(KeyCode.K);
						break;
					case KeyEvent.VK_L:
						Input.setKeyDown(KeyCode.L);
						break;
					case KeyEvent.VK_M:
						Input.setKeyDown(KeyCode.M);
						break;
					case KeyEvent.VK_N:
						Input.setKeyDown(KeyCode.N);
						break;
					case KeyEvent.VK_O:
						Input.setKeyDown(KeyCode.O);
						break;
					case KeyEvent.VK_P:
						Input.setKeyDown(KeyCode.P);
						break;
					case KeyEvent.VK_Q:
						Input.setKeyDown(KeyCode.Q);
						break;
					case KeyEvent.VK_R:
						Input.setKeyDown(KeyCode.R);
						break;
					case KeyEvent.VK_S:
						Input.setKeyDown(KeyCode.S);
						break;
					case KeyEvent.VK_T:
						Input.setKeyDown(KeyCode.T);
						break;
					case KeyEvent.VK_U:
						Input.setKeyDown(KeyCode.U);
						break;
					case KeyEvent.VK_V:
						Input.setKeyDown(KeyCode.V);
						break;
					case KeyEvent.VK_W:
						Input.setKeyDown(KeyCode.W);
						break;
					case KeyEvent.VK_X:
						Input.setKeyDown(KeyCode.X);
						break;
					case KeyEvent.VK_Y:
						Input.setKeyDown(KeyCode.Y);
						break;
					case KeyEvent.VK_Z:
						Input.setKeyDown(KeyCode.Z);
						break;
					case KeyEvent.VK_0:
						Input.setKeyDown(KeyCode.ALPHA_0);
						break;
					case KeyEvent.VK_1:
						Input.setKeyDown(KeyCode.ALPHA_1);
						break;
					case KeyEvent.VK_2:
						Input.setKeyDown(KeyCode.ALPHA_2);
						break;
					case KeyEvent.VK_3:
						Input.setKeyDown(KeyCode.ALPHA_3);
						break;
					case KeyEvent.VK_4:
						Input.setKeyDown(KeyCode.ALPHA_4);
						break;
					case KeyEvent.VK_5:
						Input.setKeyDown(KeyCode.ALPHA_5);
						break;
					case KeyEvent.VK_6:
						Input.setKeyDown(KeyCode.ALPHA_6);
						break;
					case KeyEvent.VK_7:
						Input.setKeyDown(KeyCode.ALPHA_7);
						break;
					case KeyEvent.VK_8:
						Input.setKeyDown(KeyCode.ALPHA_8);
						break;
					case KeyEvent.VK_9:
						Input.setKeyDown(KeyCode.ALPHA_9);
						break;
					case KeyEvent.VK_SPACE:
						Input.setKeyDown(KeyCode.SPACE);
						break;
					case KeyEvent.VK_ENTER:
						Input.setKeyDown(KeyCode.RETURN);
						break;
					case KeyEvent.VK_ESCAPE:
						Input.setKeyDown(KeyCode.ESCAPE);
						break;
					case KeyEvent.VK_BACK_SPACE:
						Input.setKeyDown(KeyCode.BACKSPACE);
						break;
					case KeyEvent.VK_DELETE:
						Input.setKeyDown(KeyCode.DELETE);
						break;
					case KeyEvent.VK_F1:
						Input.setKeyDown(KeyCode.F1);
						break;
					case KeyEvent.VK_F2:
						Input.setKeyDown(KeyCode.F2);
						break;
					case KeyEvent.VK_F3:
						Input.setKeyDown(KeyCode.F3);
						break;
					case KeyEvent.VK_F4:
						Input.setKeyDown(KeyCode.F4);
						break;
					case KeyEvent.VK_F5:
						Input.setKeyDown(KeyCode.F5);
						break;
					case KeyEvent.VK_F6:
						Input.setKeyDown(KeyCode.F6);
						break;
					case KeyEvent.VK_F7:
						Input.setKeyDown(KeyCode.F7);
						break;
					case KeyEvent.VK_F8:
						Input.setKeyDown(KeyCode.F8);
						break;
					case KeyEvent.VK_F9:
						Input.setKeyDown(KeyCode.F9);
						break;
					case KeyEvent.VK_F10:
						Input.setKeyDown(KeyCode.F10);
						break;
					case KeyEvent.VK_F11:
						Input.setKeyDown(KeyCode.F11);
						break;
					case KeyEvent.VK_F12:
						Input.setKeyDown(KeyCode.F12);
						break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_A:
						Input.setKeyUp(KeyCode.A);
						break;
					case KeyEvent.VK_B:
						Input.setKeyUp(KeyCode.B);
						break;
					case KeyEvent.VK_C:
						Input.setKeyUp(KeyCode.C);
						break;
					case KeyEvent.VK_D:
						Input.setKeyUp(KeyCode.D);
						break;
					case KeyEvent.VK_E:
						Input.setKeyUp(KeyCode.E);
						break;
					case KeyEvent.VK_F:
						Input.setKeyUp(KeyCode.F);
						break;
					case KeyEvent.VK_G:
						Input.setKeyUp(KeyCode.G);
						break;
					case KeyEvent.VK_H:
						Input.setKeyUp(KeyCode.H);
						break;
					case KeyEvent.VK_I:
						Input.setKeyUp(KeyCode.I);
						break;
					case KeyEvent.VK_J:
						Input.setKeyUp(KeyCode.J);
						break;
					case KeyEvent.VK_K:
						Input.setKeyUp(KeyCode.K);
						break;
					case KeyEvent.VK_L:
						Input.setKeyUp(KeyCode.L);
						break;
					case KeyEvent.VK_M:
						Input.setKeyUp(KeyCode.M);
						break;
					case KeyEvent.VK_N:
						Input.setKeyUp(KeyCode.N);
						break;
					case KeyEvent.VK_O:
						Input.setKeyUp(KeyCode.O);
						break;
					case KeyEvent.VK_P:
						Input.setKeyUp(KeyCode.P);
						break;
					case KeyEvent.VK_Q:
						Input.setKeyUp(KeyCode.Q);
						break;
					case KeyEvent.VK_R:
						Input.setKeyUp(KeyCode.R);
						break;
					case KeyEvent.VK_S:
						Input.setKeyUp(KeyCode.S);
						break;
					case KeyEvent.VK_T:
						Input.setKeyUp(KeyCode.T);
						break;
					case KeyEvent.VK_U:
						Input.setKeyUp(KeyCode.U);
						break;
					case KeyEvent.VK_V:
						Input.setKeyUp(KeyCode.V);
						break;
					case KeyEvent.VK_W:
						Input.setKeyUp(KeyCode.W);
						break;
					case KeyEvent.VK_X:
						Input.setKeyUp(KeyCode.X);
						break;
					case KeyEvent.VK_Y:
						Input.setKeyUp(KeyCode.Y);
						break;
					case KeyEvent.VK_Z:
						Input.setKeyUp(KeyCode.Z);
						break;
					case KeyEvent.VK_0:
						Input.setKeyUp(KeyCode.ALPHA_0);
						break;
					case KeyEvent.VK_1:
						Input.setKeyUp(KeyCode.ALPHA_1);
						break;
					case KeyEvent.VK_2:
						Input.setKeyUp(KeyCode.ALPHA_2);
						break;
					case KeyEvent.VK_3:
						Input.setKeyUp(KeyCode.ALPHA_3);
						break;
					case KeyEvent.VK_4:
						Input.setKeyUp(KeyCode.ALPHA_4);
						break;
					case KeyEvent.VK_5:
						Input.setKeyUp(KeyCode.ALPHA_5);
						break;
					case KeyEvent.VK_6:
						Input.setKeyUp(KeyCode.ALPHA_6);
						break;
					case KeyEvent.VK_7:
						Input.setKeyUp(KeyCode.ALPHA_7);
						break;
					case KeyEvent.VK_8:
						Input.setKeyUp(KeyCode.ALPHA_8);
						break;
					case KeyEvent.VK_9:
						Input.setKeyUp(KeyCode.ALPHA_9);
						break;
					case KeyEvent.VK_SPACE:
						Input.setKeyUp(KeyCode.SPACE);
						break;
					case KeyEvent.VK_ENTER:
						Input.setKeyUp(KeyCode.RETURN);
						break;
					case KeyEvent.VK_ESCAPE:
						Input.setKeyUp(KeyCode.ESCAPE);
						break;
					case KeyEvent.VK_BACK_SPACE:
						Input.setKeyUp(KeyCode.BACKSPACE);
						break;
					case KeyEvent.VK_DELETE:
						Input.setKeyUp(KeyCode.DELETE);
						break;
					case KeyEvent.VK_F1:
						Input.setKeyUp(KeyCode.F1);
						break;
					case KeyEvent.VK_F2:
						Input.setKeyUp(KeyCode.F2);
						break;
					case KeyEvent.VK_F3:
						Input.setKeyUp(KeyCode.F3);
						break;
					case KeyEvent.VK_F4:
						Input.setKeyUp(KeyCode.F4);
						break;
					case KeyEvent.VK_F5:
						Input.setKeyUp(KeyCode.F5);
						break;
					case KeyEvent.VK_F6:
						Input.setKeyUp(KeyCode.F6);
						break;
					case KeyEvent.VK_F7:
						Input.setKeyUp(KeyCode.F7);
						break;
					case KeyEvent.VK_F8:
						Input.setKeyUp(KeyCode.F8);
						break;
					case KeyEvent.VK_F9:
						Input.setKeyUp(KeyCode.F9);
						break;
					case KeyEvent.VK_F10:
						Input.setKeyUp(KeyCode.F10);
						break;
					case KeyEvent.VK_F11:
						Input.setKeyUp(KeyCode.F11);
						break;
					case KeyEvent.VK_F12:
						Input.setKeyUp(KeyCode.F12);
						break;
				}
			}
		});
		setFocusable(true);
		onResize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		create();
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
		Input.update();
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
