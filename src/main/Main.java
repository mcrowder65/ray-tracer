package main;

import scene.World;
import utility.Image;

public class Main {
	public static World world;
	public static Image image;
	public static Tracer tracer;

	public static void main(String[] args) {
		long start = System.nanoTime();
		world = new World(1600, 900, 1.0);
		image = new Image("Image.png");
		tracer = new Tracer();

		for (int y = 0; y < world.viewplane.height; y++) {
			for (int x = 0; x < world.viewplane.width; x++) {
				tracer.trace(x, y);
			}
		}
		image.write("PNG");

		long end = System.nanoTime();
		System.out.println("Loop Time: " + (end - start) / 1000000000.0F);

	}

}
