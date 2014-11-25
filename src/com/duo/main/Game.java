package com.duo.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	public static final long serialVersionUID = 1L;

	public static final int width = 600;
	public static final int height = 400;

	BufferedImage img;

	public Game() {
		Dimension dim = new Dimension(width, height);
		setPreferredSize(dim);
		setMaximumSize(dim);
		setMinimumSize(dim);
		JFrame frame = new JFrame("DUO");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		new Thread(this).start();
	}

	public static void main(String[] main) {
		new Game();
	}

	public void run() {
		double last = System.nanoTime();
		double delta = 0;
		double fps = 1000000.0 / 60.0;
		init();
		while (true) {
			double now = System.nanoTime();
			delta += (now - last) / fps;
			last = now;
			while (delta >= 1) {
				update();
				delta -= 1;
			}
			draw();
		}
	}

	public void init() {
		img = Sprites.getImage("/dangos/dangos.png", 0, 0, 16, 12);
		img = Sprites.changeColor(img, new int[][] { { 255, 0, 255 } }, new int[][] { { 255, 0, 0 } });
	}

	public void update() {

	}

	public void draw() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.drawImage(img, 20, 20, 64, 48, null);

		g.dispose();
		bs.show();
	}

}
