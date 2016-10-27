package main;

import java.awt.image.BufferedImage;
import java.io.File;

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

		Sphere sphere = new Sphere(new Point3D(0.0, 0.0, 0.0), 60.0, new Color(1.0F, 0.0F, 0.0F));
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Ray ray = new Ray(new Point3D(x - width / 2 + .5, y - height / 2 + .5, 100),
						new Vector3D(0.0, 0.0, -1.0));
				if (sphere.hit(ray) != 0.0) {
					buffer.setRGB(x, y, sphere.color.toInteger());
				} else {
					buffer.setRGB(x, y, 0);
				}

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
