package utility;

public class Point3D {
	public double x, y, z;

	public Point3D() {
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}

	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point3D(Point3D point) {
		this.x = point.x;
		this.y = point.y;
		this.z = point.z;
	}

	public Point3D add(Point3D point) {
		return new Point3D(this.x + point.x, this.y + point.y, this.z + point.z);
	}

	public Point3D sub(Point3D point) {
		return new Point3D(this.x - point.x, this.y - point.y, this.z - point.z);
	}
}
