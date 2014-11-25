package com.duo.main.menu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.duo.main.Game;
import com.duo.main.Sprites;

public class Menu {

	public static BufferedImage img, dango;
	public ArrayList<BufferedImage> dangos;
	public int current;
	public int trgb, timer;
	public int[] rgb;
	boolean pressed = false;

	public Menu() {
		current = 0;
		trgb = 0;
		rgb = new int[] { 255, 0, 0 };
		dangos = new ArrayList<BufferedImage>();
		dangos.add(Sprites.getImage("/dangos/dangos.png", 16, 0, 16, 12));
		dangos.add(Sprites.getImage("/dangos/dangos.png", 0, 12, 16, 12));

		img = dangos.get(current);
		dango = Sprites.changeColor(img, new int[][] { { 255, 0, 255 } }, new int[][] { rgb });
	}

	public void update() {
		if (Keyboard.isKeyDown(KeyEvent.VK_LEFT)) {
			if (trgb == 0 && !pressed) {
				current -= 1;
				pressed = true;
			} else {
				if (trgb != 0) {
					if (timer >= 100) {
						rgb[trgb - 1] -= 1;
						timer = 0;
					}
					timer += 1;
				}
			}
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_RIGHT)) {
			if (trgb == 0 && !pressed) {
				current += 1;
				pressed = true;
			} else {
				if (trgb != 0) {
					if (timer >= 100) {
						rgb[trgb - 1] += 1;
						timer = 0;
					}
					timer += 1;
				}
			}
		}

		if (Keyboard.isKeyDown(KeyEvent.VK_DOWN) && !pressed) {
			trgb += 1;
			pressed = true;
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_UP) && !pressed) {
			trgb -= 1;
			pressed = true;
		}
		if (!Keyboard.isKeyDown(KeyEvent.VK_RIGHT) && !Keyboard.isKeyDown(KeyEvent.VK_LEFT) && !Keyboard.isKeyDown(KeyEvent.VK_DOWN) && !Keyboard.isKeyDown(KeyEvent.VK_UP)) {
			pressed = false;
		}
		if (current >= dangos.size()) {
			current = 0;
		}
		if (current < 0) {
			current = dangos.size() - 1;
		}
		if (trgb >= 4) {
			trgb = 0;
		}
		if (trgb < 0) {
			trgb = 3;
		}
		for (int i = 0; i < rgb.length; i += 1) {
			if (rgb[i] >= 256) {
				rgb[i] = 0;
			}
			if (rgb[i] < 0) {
				rgb[i] = 255;
			}
		}
		img = dangos.get(current);
		dango = Sprites.changeColor(img, new int[][] { { 255, 0, 255 } }, new int[][] { rgb });
	}

	public void draw(Graphics g) {
		g.drawImage(dango, (Game.width / 2) - 32, (Game.height / 2) - 24, 64, 48, null);
	}

}
