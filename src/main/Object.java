package main;

public abstract class Object {
	public abstract Color getColor();

	public abstract Vector3D getNormalAt(Vector3D point);

	public abstract double findIntersection(Ray ray);

}
