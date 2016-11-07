package main;

public class Triangle extends Object {
	Vector3D A, B, C;
	Color color;

	public Triangle(Vector3D A, Vector3D B, Vector3D C, Color color) {
		this.A = new Vector3D(A);
		this.B = new Vector3D(B);
		this.C = new Vector3D(C);
		this.color = new Color(color);
	}

	@Override
	public Color getColor() {
		return new Color(color);
	}

	@Override
	public Vector3D getNormalAt(Vector3D point) {
		return getTriangleNormal();
	}

	private Vector3D getTriangleNormal() {
		Vector3D CA = new Vector3D(C.x - A.x, C.y - A.y, C.z - A.z);
		Vector3D BA = new Vector3D(B.x - A.x, B.y - A.y, B.z - A.z);
		Vector3D normal = CA.cross(BA).normalize();
		return new Vector3D(normal);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((A == null) ? 0 : A.hashCode());
		result = prime * result + ((B == null) ? 0 : B.hashCode());
		result = prime * result + ((C == null) ? 0 : C.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
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
		Triangle other = (Triangle) obj;
		if (A == null) {
			if (other.A != null)
				return false;
		} else if (!A.equals(other.A))
			return false;
		if (B == null) {
			if (other.B != null)
				return false;
		} else if (!B.equals(other.B))
			return false;
		if (C == null) {
			if (other.C != null)
				return false;
		} else if (!C.equals(other.C))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}

	public double getTriangleDistance() {
		Vector3D normal = getTriangleNormal();
		double distance = normal.dot(A);
		return distance;
	}

	@Override
	public double findIntersection(Ray ray) {
		Vector3D normal = getTriangleNormal();
		double distance = getTriangleDistance();
		double a = ray.direction.dot(normal);
		if (a == 0) {
			// ray is parallel to Triangle
			return -1;
		} else {
			double b = normal.dot(ray.origin.add(normal.multiply(distance).negative()));
			double distanceToPlane = -1 * b / a;
			double Qx = ray.direction.multiply(distanceToPlane).x + ray.origin.x;
			double Qy = ray.direction.multiply(distanceToPlane).y + ray.origin.y;
			double Qz = ray.direction.multiply(distanceToPlane).z + ray.origin.z;

			Vector3D Q = new Vector3D(Qx, Qy, Qz);
			Vector3D CA = new Vector3D(C.x - A.x, C.y - A.y, C.z - A.z);
			Vector3D QA = new Vector3D(Q.x - A.x, Q.y - A.y, Q.z - A.z);
			double test1 = (CA.cross(QA)).dot(normal);

			Vector3D BC = new Vector3D(B.x - C.x, B.y - C.y, B.z - C.z);
			Vector3D QC = new Vector3D(Q.x - C.x, Q.y - C.y, Q.z - C.z);
			double test2 = (BC.cross(QC)).dot(normal);

			Vector3D AB = new Vector3D(A.x - B.x, A.y - B.y, A.z - B.z);
			Vector3D QB = new Vector3D(Q.x - B.x, Q.y - B.y, Q.z - B.z);
			double test3 = (AB.cross(QB)).dot(normal);
			// [CAxQA] . n >= 0
			// [BCxQC] . n >= 0
			// [ABxQB] . n >= 0

			if (test1 >= 0 && test2 >= 0 && test3 >= 0) {
				// inside triangle
				return -1 * b / a;
			} else {
				// outside triangle
				return -1;
			}
		}
	}

}
