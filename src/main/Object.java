package main;

public abstract class Object {
	private RGB specularHighlight;
	private int phongConstant;
	private RGB reflective;
	private RGB translucent;

	public RGB getTranslucent() {
		return translucent;
	}

	public void setTranslucent(RGB translucent) {
		this.translucent = translucent;
	}

	public RGB getReflective() {
		return reflective;
	}

	public void setReflective(RGB reflective) {
		this.reflective = reflective;
	}

	public int getPhongConstant() {
		return phongConstant;
	}

	public void setPhongConstant(int phongConstant) {
		this.phongConstant = phongConstant;
	}

	public RGB getSpecularHighlight() {
		return specularHighlight;
	}

	public void setSpecularHighlight(RGB specularHighlight) {
		this.specularHighlight = specularHighlight;
	}

	public abstract boolean equals(java.lang.Object obj);

	public abstract int hashCode();

	public abstract Color getColor();

	public abstract Vector3D getNormalAt(Vector3D point);

	public abstract double findIntersection(Ray ray);

}
