package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

import geometry.Sphere;
import utility.Color;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public class Main {

	public static void main(String[] args) {
		long start = System.nanoTime();
		int width = 1600;
		int height = 900;
		File image = new File("Image.png");
		BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Random random = new Random();

		Sphere sphere = new Sphere(new Point3D(0.0, 0.0, 0.0), 60.0, new Color(1.0F, 0.0F, 0.0F));
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color color = new Color(0.0F, 0.0F, 0.0F);
				int sample = 8;
				for (int row = 0; row < sample; row++) {
					for (int col = 0; col < sample; col++) {

						Ray ray = new Ray(
								new Point3D(0.25 * (x - width / 2 + (col + random.nextFloat()) / sample),
										0.25 * (y - height / 2 + (row + random.nextFloat()) / sample), 100),
								new Vector3D(0.0, 0.0, -1.0));
						if (sphere.hit(ray) != 0.0) {
							color.add(sphere.color);
						} else {
							// color.add(world.background.color);
						}
					}
				}
				color.divide(64); // get average of pixel
				buffer.setRGB(x, y, color.toInteger());

			}
		}
		try {
			ImageIO.write(buffer, "PNG", image);
			long end = System.nanoTime();
			System.out.println("Loop Time: " + (end - start) / 1000000000.0F);
		} catch (Exception e) {
			System.out.println("could not write image.");
		}

	}

}
