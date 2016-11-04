package main;

public class Sphere extends Object {
	private Vector3D center;
	private double radius;
	private Color color;

	public Vector3D getCenter() {
		return center;
	}

	public void setCenter(Vector3D center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	Sphere(Vector3D center, double radius, Color color) {
		this.center = new Vector3D(center);
		this.radius = radius;
		this.color = new Color(color);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public Vector3D getNormalAt(Vector3D point) {
		Vector3D normal = point.add(center.negative()).normalize();
		return new Vector3D(normal);
	}

	@Override
	public double findIntersection(Ray ray) {
		double b = (2 * (ray.origin.x - center.x) * ray.direction.x) + (2 * (ray.origin.y - center.y) * ray.direction.y)
				+ (2 * (ray.origin.z - center.z) * ray.direction.z);
		double c = Math.pow(ray.origin.x - center.x, 2) + Math.pow(ray.origin.y - center.y, 2)
				+ Math.pow(ray.origin.z - center.z, 2) - (radius * radius);
		double discriminant = b * b - 4 * c;

		if (discriminant > 0) {
			// the ray intersects the sphere

			// used for rounding errors
			double arbitrary = 0.000001;
			// the first root
			double root1 = ((-1 * b - Math.sqrt(discriminant)) / 2) - arbitrary;
			if (root1 > 0) {
				// the first root is the smallest positive root
				return root1;
			} else {
				// the second root is the smallest positive root
				double root2 = ((Math.sqrt(discriminant) - b) / 2) - arbitrary;
				return root2;
			}

		} else {
			// the ray missed the sphere
			return -1;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((center == null) ? 0 : center.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		long temp;
		temp = Double.doubleToLongBits(radius);
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
		Sphere other = (Sphere) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}

}
