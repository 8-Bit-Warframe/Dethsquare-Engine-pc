package com.ezardlabs.dethsquare.util;

import java.util.ArrayList;

public interface GameListeners {
	ArrayList<UpdateListener> updateListeners = new ArrayList<>();
	ArrayList<RenderListener> renderListeners = new ArrayList<>();
	ArrayList<ResizeListener> resizeListeners = new ArrayList<>();
	ArrayList<MouseListener> mouseListeners = new ArrayList<>();
	ArrayList<KeyListener> keyListeners = new ArrayList<>();

	ScreenSize screenSize = new ScreenSize();

	class ScreenSize {
		public int width = 0;
		public int height = 0;

		@Override
		public String toString() {
			return width + ", " + height;
		}
	}

	static void addUpdateListener(UpdateListener updateListener) {
		updateListeners.add(updateListener);
	}

	static void removeUpdateListener(UpdateListener updateListener) {
		updateListeners.remove(updateListener);
	}

	static void addRenderListener(RenderListener renderListener) {
		renderListeners.add(renderListener);
	}

	static void removeRenderListener(RenderListener renderListener) {
		renderListeners.remove(renderListener);
	}

	static void addResizeListener(ResizeListener resizeListener) {
		resizeListeners.add(resizeListener);
	}

	static void removeResizeListener(ResizeListener resizeListener) {
		resizeListeners.remove(resizeListener);
	}

	static void addMouseListener(MouseListener mouseListener) {
		mouseListeners.add(mouseListener);
	}

	static void removeMouseListener(MouseListener mouseListener) {
		mouseListeners.remove(mouseListener);
	}

	static void addKeyListener(KeyListener keyListener) {
		keyListeners.add(keyListener);
	}

	static void removeKeyListener(KeyListener keyListener) {
		keyListeners.remove(keyListener);
	}

	interface UpdateListener {
		void onUpdate();
	}

	interface RenderListener {
		void onRender();
	}

	interface ResizeListener {
		void onResize(int width, int height);
	}

	interface MouseListener {
		void onMove(int x, int y);
		void onButtonDown(int index);
		void onButtonUp(int index);
	}

	interface KeyListener {
		void onKeyDown(String key);
		void onKeyUp(String key);
	}
}
