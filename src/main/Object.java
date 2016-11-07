package main;

public abstract class Object {
	private RGB specularHighlight;
	private RGB materialDiffuse;
	private int phongConstant;

	public int getPhongConstant() {
		return phongConstant;
	}

	public void setPhongConstant(int phongConstant) {
		this.phongConstant = phongConstant;
	}

	public RGB getSpecularHighlight() {
		if (specularHighlight == null) {
			System.out.println("specularHighlight is null!!!!");
		}
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
