package com.duo.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprites {

	public static BufferedImage getImage(String path, int x, int y, int w, int h) {
		try {
			return ImageIO.read(Sprites.class.getResource(path)).getSubimage(x, y, w, h);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage changeType(BufferedImage img) {
		BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = res.getGraphics();
		g.drawImage(img, 0, 0, null);
		return res;
	}

	public static BufferedImage changeColor(BufferedImage src, int[][] srclr, int[][] reclr) {
		BufferedImage res = changeType(src);
		for (int x = 0; x < res.getWidth(); x += 1) {
			for (int y = 0; y < res.getHeight(); y += 1) {
				Color clr = new Color(res.getRGB(x, y));
				for (int i = 0; i < srclr.length; i += 1) {
					if (clr.getRed() == srclr[i][0] && clr.getGreen() == srclr[i][1] && clr.getBlue() == srclr[i][2]) {
						res.setRGB(x, y, new Color(reclr[i][0], reclr[i][1], reclr[i][2]).getRGB());
					}
				}
			}
		}
		return res;
	}
}
