package main;

public class Ray {
	public Vector3D origin;
	public Vector3D direction;

	public Ray(Vector3D origin, Vector3D direction) {
		this.origin = new Vector3D(origin);
		this.direction = new Vector3D(direction);
	}
}
