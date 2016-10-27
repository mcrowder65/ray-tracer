package geometry;

import utility.Color;
import utility.Normal;
import utility.Point3D;
import utility.Ray;

public class Plane extends GeometricObject {

	Point3D point;
	Normal normal;

	public Plane(Point3D point, Normal normal, Color color) {
		this.point = new Point3D(point);
		this.normal = new Normal(normal);
		this.color = new Color(color);

	}

	@Override
	public double hit(Ray ray) {
		double t = point.sub(ray.origin).dot(normal) / ray.direction.dot(normal);
		if (t > 10E-9) {
			return t;
		}
		return 0.0;
	}
}
