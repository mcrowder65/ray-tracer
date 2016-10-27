package scene;

import java.util.ArrayList;
import java.util.List;

import geometry.GeometricObject;
import geometry.Sphere;
import utility.Color;
import utility.Point3D;

public class World {
	public ViewPlane viewplane;
	public List<GeometricObject> objects;
	public Color background;

	public World(int width, int height, double size) {
		viewplane = new ViewPlane(width, height, size);
		background = new Color(0.0F, 0.0F, 0.0F);
		objects = new ArrayList<>();
		objects.add(new Sphere(new Point3D(0.0, 0.0, 0.0), 50, new Color(1.0F, 0.0F, 0.0F)));
		objects.add(new Sphere(new Point3D(-200.0, 0.0, 0.0), 50, new Color(0.0F, 0.0F, 0.0F)));
		objects.add(new Sphere(new Point3D(200.0, 0.0, 0.0), 50, new Color(0.0F, 0.0F, 0.0F)));
	}
}
