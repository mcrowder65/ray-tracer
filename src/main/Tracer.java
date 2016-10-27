package main;

import java.util.List;

import geometry.GeometricObject;
import utility.Color;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public class Tracer {
	public void trace(int x, int y) {
		Ray ray = new Ray(new Point3D(1.0 * (x - Main.world.viewplane.width / 2 + 0.5),
				1.0 * (y - Main.world.viewplane.height / 2 + .5), 100), new Vector3D(0.0, 0.0, -1.0));
		double min = Double.MAX_VALUE;
		List<GeometricObject> objs = Main.world.objects;
		Color color = Main.world.background;
		for (int i = 0; i < objs.size(); i++) {
			double temp = objs.get(i).hit(ray);
			if (temp != 0.0 && temp < min) {
				color = objs.get(i).color;
				min = temp;
			}
		}
		Main.image.buffer.setRGB(x, y, color.toInteger());
	}
}
