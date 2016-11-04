package main;

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

	public Vector3D multiply(double scalar) {
		return new Vector3D(x * scalar, y * scalar, z * scalar);
	}

	public Vector3D sub(Vector3D vector) {
		return new Vector3D(this.x - vector.x, this.y - vector.y, this.z - vector.z);
	}

	public double dot(Vector3D vector) {
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}

	double magnitude() {
		return Math.sqrt((x * x) + (y * y) + (z * z));
	}

	public Vector3D normalize() {
		double magnitude = this.magnitude();
		return new Vector3D(x / magnitude, y / magnitude, z / magnitude);
	}

	public Vector3D negative() {
		return new Vector3D(-x, -y, -z);
	}

	public Vector3D cross(Vector3D vector) {
		return new Vector3D(this.y * vector.z - this.z * vector.y, this.z * vector.x - this.x * vector.z,
				this.x * vector.y - this.y * vector.x);
	}
}
