package main;

import projection.Perspective;
import projection.Projection;
import sampling.RegularSample;
import sampling.Sampler;
import scene.World;
import utility.Image;
import utility.Point3D;

public class Main {
	public static World world;
	public static Image image;
	public static Tracer tracer;
	public static Sampler sampler;
	public static Projection projection;

	public static void main(String[] args) {
		long start = System.nanoTime();
		world = new World(1600, 900, 1.0);
		image = new Image("Image.png");
		tracer = new Tracer();
		sampler = new RegularSample(4);
		projection = new Perspective(new Point3D(0, 0, 1), new Point3D(0.0, 0.0, 0.0), 28);
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
