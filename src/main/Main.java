package main;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		long start = System.nanoTime();
		int width = 1600;
		int height = 900;
		File image = new File("Image.png");
		BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

			}
		}
		try {
			ImageIO.write(buffer, "PNG", image);
		} catch (Exception e) {
			System.out.println("could not write image.");
		}

		long end = System.nanoTime();
		System.out.println("Loop Time: " + (end - start) / 1000000000.0F);
	}

}
