package main;

import java.util.List;

import geometry.GeometricObject;
import utility.Color;
import utility.Point2D;
import utility.Ray;

public class Tracer {
	public void trace(int x, int y) {
		boolean hit = false;
		List<GeometricObject> objs = Main.world.objects;
		Color color = new Color(0.0F, 0.0F, 0.0F);

		for (int row = 0; row < Main.sampler.samples; row++) {
			for (int col = 0; col < Main.sampler.samples; col++) {
				Point2D point = Main.sampler.sample(row, col, x, y);
				Ray ray = Main.projection.createRay(point);
				double min = Double.MAX_VALUE;
				Color tempColor = new Color(Main.world.background);
				for (int i = 0; i < objs.size(); i++) {
					double temp = objs.get(i).hit(ray);
					if (temp != 0.0 && temp < min) {
						min = temp;
						hit = true;
						tempColor = objs.get(i).color;
					}
				}

				color.add(tempColor);
			}
		}
		color.divide(Main.sampler.samples * Main.sampler.samples);
		Main.image.buffer.setRGB(x, Main.world.viewplane.height - y - 1,
				hit ? color.toInteger() : Main.world.background.toInteger());
	}
}
