package main;

import java.util.List;

import geometry.GeometricObject;
import utility.Color;
import utility.Point2D;
import utility.Ray;

public class Tracer {
	public void trace(int x, int y) {
		Ray ray = null;
		double min = Double.MAX_VALUE;
		List<GeometricObject> objs = Main.world.objects;
		Color color = new Color(0.0F, 0.0F, 0.0F);

		for (int row = 0; row < Main.sampler.samples; row++) {
			for (int col = 0; col < Main.sampler.samples; col++) {
				Point2D point = Main.sampler.sample(row, col, x, y);
				ray = Main.projection.createRay(point);
			}
		}

		for (int i = 0; i < objs.size(); i++) {
			double temp = objs.get(i).hit(ray);
			if (temp != 0.0 && temp < min) {
				color = objs.get(i).color;
				min = temp;
			}
		}
		Main.image.buffer.setRGB(x, Main.world.viewplane.height - y - 1, color.toInteger());
	}
}
