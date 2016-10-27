package scene;

import java.util.ArrayList;
import java.util.List;

import geometry.GeometricObject;
import geometry.Plane;
import geometry.Sphere;
import utility.Color;
import utility.Normal;
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
		objects.add(new Sphere(new Point3D(-150.0, 0.0, 0.0), 50, new Color(0.0F, 1.0F, 0.0F)));
		objects.add(new Sphere(new Point3D(150.0, 0.0, 0.0), 50, new Color(0.0F, 0.0F, 1.0F)));
		objects.add(new Plane(new Point3D(0.0, 0.0, 0.0), new Normal(0.0, 1.0, 0.2), new Color(1.0F, 1.0F, 0.0F)));
	}
}
