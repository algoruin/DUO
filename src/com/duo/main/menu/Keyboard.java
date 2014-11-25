package com.duo.main.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Keyboard implements KeyListener {

	public static LinkedList<Integer> keys;

	public Keyboard() {
		keys = new LinkedList<Integer>();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		boolean added = false;
		for (int i = 0; i < keys.size(); i += 1) {
			if (keys.get(i) == e.getKeyCode()) {
				added = true;
			}
		}
		if (!added) {
			keys.add(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (int i = 0; i < keys.size(); i += 1) {
			if (keys.get(i) == e.getKeyCode()) {
				keys.remove(i);
				return;
			}
		}
	}

	public static boolean isKeyDown(int key) {
		for (int i = 0; i < keys.size(); i += 1) {
			if (keys.get(i) == key) {
				return true;
			}
		}
		return false;
	}

}
