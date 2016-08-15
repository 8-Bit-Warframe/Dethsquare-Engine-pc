package com.ezardlabs.dethsquare.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public abstract class BaseGame extends JFrame implements GameListeners {
	private VolatileImage vBuffer;
	public static Graphics2D graphics;
	public static ImageObserver imageObserver;

	public BaseGame() {
		setTitle("Lost Sector");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addKeyListener(new java.awt.event.KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				for (KeyListener keyListener : keyListeners) {
					switch (e.getKeyCode()) {
						case KeyEvent.VK_A:
							keyListener.onKeyDown("A");
							break;
						case KeyEvent.VK_B:
							keyListener.onKeyDown("B");
							break;
						case KeyEvent.VK_C:
							keyListener.onKeyDown("C");
							break;
						case KeyEvent.VK_D:
							keyListener.onKeyDown("D");
							break;
						case KeyEvent.VK_E:
							keyListener.onKeyDown("E");
							break;
						case KeyEvent.VK_F:
							keyListener.onKeyDown("F");
							break;
						case KeyEvent.VK_G:
							keyListener.onKeyDown("G");
							break;
						case KeyEvent.VK_H:
							keyListener.onKeyDown("H");
							break;
						case KeyEvent.VK_I:
							keyListener.onKeyDown("I");
							break;
						case KeyEvent.VK_J:
							keyListener.onKeyDown("J");
							break;
						case KeyEvent.VK_K:
							keyListener.onKeyDown("K");
							break;
						case KeyEvent.VK_L:
							keyListener.onKeyDown("L");
							break;
						case KeyEvent.VK_M:
							keyListener.onKeyDown("M");
							break;
						case KeyEvent.VK_N:
							keyListener.onKeyDown("N");
							break;
						case KeyEvent.VK_O:
							keyListener.onKeyDown("O");
							break;
						case KeyEvent.VK_P:
							keyListener.onKeyDown("P");
							break;
						case KeyEvent.VK_Q:
							keyListener.onKeyDown("Q");
							break;
						case KeyEvent.VK_R:
							keyListener.onKeyDown("R");
							break;
						case KeyEvent.VK_S:
							keyListener.onKeyDown("S");
							break;
						case KeyEvent.VK_T:
							keyListener.onKeyDown("T");
							break;
						case KeyEvent.VK_U:
							keyListener.onKeyDown("U");
							break;
						case KeyEvent.VK_V:
							keyListener.onKeyDown("V");
							break;
						case KeyEvent.VK_W:
							keyListener.onKeyDown("W");
							break;
						case KeyEvent.VK_X:
							keyListener.onKeyDown("X");
							break;
						case KeyEvent.VK_Y:
							keyListener.onKeyDown("Y");
							break;
						case KeyEvent.VK_Z:
							keyListener.onKeyDown("Z");
							break;
						case KeyEvent.VK_0:
							keyListener.onKeyDown("ALPHA_0");
							break;
						case KeyEvent.VK_1:
							keyListener.onKeyDown("ALPHA_1");
							break;
						case KeyEvent.VK_2:
							keyListener.onKeyDown("ALPHA_2");
							break;
						case KeyEvent.VK_3:
							keyListener.onKeyDown("ALPHA_3");
							break;
						case KeyEvent.VK_4:
							keyListener.onKeyDown("ALPHA_4");
							break;
						case KeyEvent.VK_5:
							keyListener.onKeyDown("ALPHA_5");
							break;
						case KeyEvent.VK_6:
							keyListener.onKeyDown("ALPHA_6");
							break;
						case KeyEvent.VK_7:
							keyListener.onKeyDown("ALPHA_7");
							break;
						case KeyEvent.VK_8:
							keyListener.onKeyDown("ALPHA_8");
							break;
						case KeyEvent.VK_9:
							keyListener.onKeyDown("ALPHA_9");
							break;
						case KeyEvent.VK_SPACE:
							keyListener.onKeyDown("SPACE");
							break;
						case KeyEvent.VK_ENTER:
							keyListener.onKeyDown("RETURN");
							break;
						case KeyEvent.VK_ESCAPE:
							keyListener.onKeyDown("ESCAPE");
							break;
						case KeyEvent.VK_BACK_SPACE:
							keyListener.onKeyDown("BACKSPACE");
							break;
						case KeyEvent.VK_DELETE:
							keyListener.onKeyDown("DELETE");
							break;
						case KeyEvent.VK_F1:
							keyListener.onKeyDown("F1");
							break;
						case KeyEvent.VK_F2:
							keyListener.onKeyDown("F2");
							break;
						case KeyEvent.VK_F3:
							keyListener.onKeyDown("F3");
							break;
						case KeyEvent.VK_F4:
							keyListener.onKeyDown("F4");
							break;
						case KeyEvent.VK_F5:
							keyListener.onKeyDown("F5");
							break;
						case KeyEvent.VK_F6:
							keyListener.onKeyDown("F6");
							break;
						case KeyEvent.VK_F7:
							keyListener.onKeyDown("F7");
							break;
						case KeyEvent.VK_F8:
							keyListener.onKeyDown("F8");
							break;
						case KeyEvent.VK_F9:
							keyListener.onKeyDown("F9");
							break;
						case KeyEvent.VK_F10:
							keyListener.onKeyDown("F10");
							break;
						case KeyEvent.VK_F11:
							keyListener.onKeyDown("F11");
							break;
						case KeyEvent.VK_F12:
							keyListener.onKeyDown("F12");
							break;
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				for (KeyListener keyListener : keyListeners) {
					switch (e.getKeyCode()) {
						case KeyEvent.VK_A:
							keyListener.onKeyUp("A");
							break;
						case KeyEvent.VK_B:
							keyListener.onKeyUp("B");
							break;
						case KeyEvent.VK_C:
							keyListener.onKeyUp("C");
							break;
						case KeyEvent.VK_D:
							keyListener.onKeyUp("D");
							break;
						case KeyEvent.VK_E:
							keyListener.onKeyUp("E");
							break;
						case KeyEvent.VK_F:
							keyListener.onKeyUp("F");
							break;
						case KeyEvent.VK_G:
							keyListener.onKeyUp("G");
							break;
						case KeyEvent.VK_H:
							keyListener.onKeyUp("H");
							break;
						case KeyEvent.VK_I:
							keyListener.onKeyUp("I");
							break;
						case KeyEvent.VK_J:
							keyListener.onKeyUp("J");
							break;
						case KeyEvent.VK_K:
							keyListener.onKeyUp("K");
							break;
						case KeyEvent.VK_L:
							keyListener.onKeyUp("L");
							break;
						case KeyEvent.VK_M:
							keyListener.onKeyUp("M");
							break;
						case KeyEvent.VK_N:
							keyListener.onKeyUp("N");
							break;
						case KeyEvent.VK_O:
							keyListener.onKeyUp("O");
							break;
						case KeyEvent.VK_P:
							keyListener.onKeyUp("P");
							break;
						case KeyEvent.VK_Q:
							keyListener.onKeyUp("Q");
							break;
						case KeyEvent.VK_R:
							keyListener.onKeyUp("R");
							break;
						case KeyEvent.VK_S:
							keyListener.onKeyUp("S");
							break;
						case KeyEvent.VK_T:
							keyListener.onKeyUp("T");
							break;
						case KeyEvent.VK_U:
							keyListener.onKeyUp("U");
							break;
						case KeyEvent.VK_V:
							keyListener.onKeyUp("V");
							break;
						case KeyEvent.VK_W:
							keyListener.onKeyUp("W");
							break;
						case KeyEvent.VK_X:
							keyListener.onKeyUp("X");
							break;
						case KeyEvent.VK_Y:
							keyListener.onKeyUp("Y");
							break;
						case KeyEvent.VK_Z:
							keyListener.onKeyUp("Z");
							break;
						case KeyEvent.VK_0:
							keyListener.onKeyUp("ALPHA_0");
							break;
						case KeyEvent.VK_1:
							keyListener.onKeyUp("ALPHA_1");
							break;
						case KeyEvent.VK_2:
							keyListener.onKeyUp("ALPHA_2");
							break;
						case KeyEvent.VK_3:
							keyListener.onKeyUp("ALPHA_3");
							break;
						case KeyEvent.VK_4:
							keyListener.onKeyUp("ALPHA_4");
							break;
						case KeyEvent.VK_5:
							keyListener.onKeyUp("ALPHA_5");
							break;
						case KeyEvent.VK_6:
							keyListener.onKeyUp("ALPHA_6");
							break;
						case KeyEvent.VK_7:
							keyListener.onKeyUp("ALPHA_7");
							break;
						case KeyEvent.VK_8:
							keyListener.onKeyUp("ALPHA_8");
							break;
						case KeyEvent.VK_9:
							keyListener.onKeyUp("ALPHA_9");
							break;
						case KeyEvent.VK_SPACE:
							keyListener.onKeyUp("SPACE");
							break;
						case KeyEvent.VK_ENTER:
							keyListener.onKeyUp("RETURN");
							break;
						case KeyEvent.VK_ESCAPE:
							keyListener.onKeyUp("ESCAPE");
							break;
						case KeyEvent.VK_BACK_SPACE:
							keyListener.onKeyUp("BACKSPACE");
							break;
						case KeyEvent.VK_DELETE:
							keyListener.onKeyUp("DELETE");
							break;
						case KeyEvent.VK_F1:
							keyListener.onKeyUp("F1");
							break;
						case KeyEvent.VK_F2:
							keyListener.onKeyUp("F2");
							break;
						case KeyEvent.VK_F3:
							keyListener.onKeyUp("F3");
							break;
						case KeyEvent.VK_F4:
							keyListener.onKeyUp("F4");
							break;
						case KeyEvent.VK_F5:
							keyListener.onKeyUp("F5");
							break;
						case KeyEvent.VK_F6:
							keyListener.onKeyUp("F6");
							break;
						case KeyEvent.VK_F7:
							keyListener.onKeyUp("F7");
							break;
						case KeyEvent.VK_F8:
							keyListener.onKeyUp("F8");
							break;
						case KeyEvent.VK_F9:
							keyListener.onKeyUp("F9");
							break;
						case KeyEvent.VK_F10:
							keyListener.onKeyUp("F10");
							break;
						case KeyEvent.VK_F11:
							keyListener.onKeyUp("F11");
							break;
						case KeyEvent.VK_F12:
							keyListener.onKeyUp("F12");
							break;
					}
				}
			}
		});
		addMouseListener(new java.awt.event.MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				for (MouseListener mouseListener : mouseListeners) {
					mouseListener.onButtonDown(e.getButton());
				}
//				switch(e.getButton()) {
//					case MouseEvent.BUTTON1:
//						Input.setKeyDown(KeyCode.MOUSE_LEFT);
//						break;
//					case MouseEvent.BUTTON2:
//						Input.setKeyDown(KeyCode.MOUSE_MIDDLE);
//						break;
//					case MouseEvent.BUTTON3:
//						Input.setKeyDown(KeyCode.MOUSE_RIGHT);
//						break;
//				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				for (MouseListener mouseListener : mouseListeners) {
					mouseListener.onButtonUp(e.getButton());
				}
//				switch(e.getButton()) {
//					case MouseEvent.BUTTON1:
//						Input.setKeyUp(KeyCode.MOUSE_LEFT);
//						break;
//					case MouseEvent.BUTTON2:
//						Input.setKeyUp(KeyCode.MOUSE_MIDDLE);
//						break;
//					case MouseEvent.BUTTON3:
//						Input.setKeyUp(KeyCode.MOUSE_RIGHT);
//						break;
//				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				for (MouseListener mouseListener : mouseListeners) {
					mouseListener.onMove(e.getX(), e.getY());
				}
//				Input.mousePosition.set(e.getX(), e.getY());
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				for (MouseListener mouseListener : mouseListeners) {
					mouseListener.onMove(e.getX(), e.getY());
				}
//				Input.mousePosition.set(e.getX(), e.getY());
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

	private void update() {
		for (int i = 0; i < updateListeners.size(); i++) {
			updateListeners.get(i).onUpdate();
		}
//		Input.update();
//		GameObject.updateAll();
	}

	private void render() {
		renderListeners.forEach(RenderListener::onRender);
//		Renderer.renderAll();
	}

	private void onResize(int width, int height) {
		screenSize.width = width;
		screenSize.height = height;
		for (ResizeListener resizeListener : resizeListeners) {
			resizeListener.onResize(width, height);
		}
//		Screen.scale = (float) width / 1920f;
//		Screen.width = width;
//		Screen.height = height;
		vBuffer = createVolatileImage(width, height);
		imageObserver = this;
	}
}
