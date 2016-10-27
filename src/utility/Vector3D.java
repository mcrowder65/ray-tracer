package utility;

public class Vector3D {
	public double x, y, z;

	public Vector3D() {
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}

	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3D(Vector3D vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}

	public Vector3D add(Vector3D vector) {
		return new Vector3D(this.x + vector.x, this.y + vector.y, this.z + vector.z);
	}

	public Vector3D sub(Vector3D vector) {
		return new Vector3D(this.x - vector.x, this.y - vector.y, this.z - vector.z);
	}

	public double dot(Vector3D vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}

	public double dot(Point3D point) {
		return this.x * point.x + this.y * point.y + this.z * point.z;
	}

}
