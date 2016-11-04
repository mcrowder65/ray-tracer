package main;

public class Vector3D {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(java.lang.Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector3D other = (Vector3D) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}

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

	@Override
	public String toString() {
		return "Vector3D [x=" + x + ", y=" + y + ", z=" + z + "]";
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
