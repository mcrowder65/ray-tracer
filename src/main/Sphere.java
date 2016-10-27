package main;

public class Sphere {
	Tuple center;
	double radius;
	Tuple materialDiffuse;
	Tuple materialReflective;
	Tuple specularHighlight;
	double phongConstant;

	public Sphere(Tuple center, double radius, Tuple materialDiffuse, Tuple specularHighlight, double phongConstant) {
		this.center = center;
		this.radius = radius;
		this.materialDiffuse = materialDiffuse;
		this.specularHighlight = specularHighlight;
		this.phongConstant = phongConstant;
	}

	public Sphere(Tuple center, double radius, Tuple materialReflective) {
		this.center = center;
		this.radius = radius;
		this.materialReflective = materialReflective;
	}

	/**
	 * Looks at point and sphere to see if the point is in the sphere.
	 * 
	 * @param point
	 *            Tuple representation of a point.
	 * @return boolean
	 */
	public boolean isPointInSphere(Tuple point) {
		// (Xp - Xc)^2 + (Yp - Yc)^2 + (Zp - Zc)^2 = r^2
		final double power = 2;
		double xSquared = Math.pow(point.x - center.x, power);
		double ySquared = Math.pow(point.y - center.y, power);
		double zSquared = Math.pow(point.z - center.z, power);
		double rSquared = Math.pow(radius, power);
		boolean result = xSquared + ySquared + zSquared == rSquared;
		return result;
	}

	public boolean isRayInSphere(Ray ray) {
		double x = ray.origin.x + ray.direction.x;
		double y = ray.origin.y + ray.direction.y;
		double z = ray.origin.z + ray.direction.z;
		double Xd = ray.direction.x;
		double Yd = ray.direction.y;
		double Zd = ray.direction.z;
		double Xo = ray.origin.x;
		double Yo = ray.origin.y;
		double Zo = ray.origin.z;
		double Xc = center.x;
		double Yc = center.y;
		double Zc = center.z;
		// A = Xd^2 + Yd^2 + Zd^2 = 1
		double A = pow(Xd) + pow(Yd) + pow(Zd); // = 1
		if (A != 1) {
			return false;
		}
		// B = 2(XdXo -XdXc + YdYo - YdYc + ZdZo - ZdZc)
		double B = 2 * (Xd * Xo - Xd * Xc + Yd * Yo - Yd * Yc + Zd * Zo - Zd * Zc);

		// C = Xo^2 - 2XoXc + Xc^2 + Yo^2 - 2YoYc + Yc^2 + Zo^2 - 2ZoZc + Zc^2 -
		// r^2
		double C = pow(Xo) - 2 * Xo * Xc + pow(Xc) + pow(Yo) - 2 * Yo * Yc + pow(Yc) + pow(Zo) - 2 * Zo * Zc + pow(Zc)
				- pow(this.radius);
		// 0 = At^2 + Bt + C

		// t1 = (-B + sqrt(B^2 - 4AC)) / 2
		double t1 = (-B + sqrt(pow(B) - 4 * A * C)) / 2;

		// t2 = (-B - sqrt(B^2 - 4AC)) / 2
		double t2 = (-B - sqrt(pow(B) - 4 * A * C)) / 2;
		// TODO finish this
		if (t1 == -B / 2) {

		}
		if (t2 == -B / 2) {

		}

		if (t1 < 0 && t2 > 0) {
			// t2 correct answer
		}

		if (t1 > 0 && t2 > 0) {
			// t1 is correct answer
		}
		// https://docs.google.com/presentation/d/1WB8-fzuHbwogCQVe6BDky4l3IAaAqsLTWcEud7_cUVM/edit#slide=id.p13
		return false;
	}

	/**
	 * Returns the power of double a to 2
	 * 
	 * @param a
	 *            double
	 * @return double
	 */
	private double pow(double a) {
		return Math.pow(a, 2);
	}

	/**
	 * Made a function sqrt so I don't have to call Math.sqrt
	 * 
	 * @param a
	 *            double
	 * @return sqrt of double a
	 */
	private double sqrt(double a) {
		return Math.sqrt(a);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((center == null) ? 0 : center.hashCode());
		result = prime * result + ((materialDiffuse == null) ? 0 : materialDiffuse.hashCode());
		result = prime * result + ((materialReflective == null) ? 0 : materialReflective.hashCode());
		long temp;
		temp = Double.doubleToLongBits(phongConstant);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((specularHighlight == null) ? 0 : specularHighlight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
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
		if (materialDiffuse == null) {
			if (other.materialDiffuse != null)
				return false;
		} else if (!materialDiffuse.equals(other.materialDiffuse))
			return false;
		if (materialReflective == null) {
			if (other.materialReflective != null)
				return false;
		} else if (!materialReflective.equals(other.materialReflective))
			return false;
		if (Double.doubleToLongBits(phongConstant) != Double.doubleToLongBits(other.phongConstant))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		if (specularHighlight == null) {
			if (other.specularHighlight != null)
				return false;
		} else if (!specularHighlight.equals(other.specularHighlight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[center=" + center + ", radius=" + radius + ", materialDiffuse=" + materialDiffuse
				+ ", materialReflective=" + materialReflective + ", specularHighlight=" + specularHighlight
				+ ", phongConstant=" + phongConstant + "]";
	}
}
