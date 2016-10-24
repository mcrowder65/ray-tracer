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
}
