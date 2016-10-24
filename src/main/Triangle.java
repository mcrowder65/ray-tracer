package main;

public class Triangle {
	Tuple vertexOne;
	Tuple vertexTwo;
	Tuple vertexThree;
	Tuple materialDiffuse;
	Tuple specularHighlight;
	double phongConstant;

	public Triangle(Tuple vertexOne, Tuple vertexTwo, Tuple vertexThree, Tuple materialDiffuse, Tuple specularHighlight,
			double phongConstant) {
		this.vertexOne = vertexOne;
		this.vertexTwo = vertexTwo;
		this.vertexThree = vertexThree;
		this.materialDiffuse = materialDiffuse;
		this.specularHighlight = specularHighlight;
		this.phongConstant = phongConstant;
	}
}
