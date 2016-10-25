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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((materialDiffuse == null) ? 0 : materialDiffuse.hashCode());
		long temp;
		temp = Double.doubleToLongBits(phongConstant);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((specularHighlight == null) ? 0 : specularHighlight.hashCode());
		result = prime * result + ((vertexOne == null) ? 0 : vertexOne.hashCode());
		result = prime * result + ((vertexThree == null) ? 0 : vertexThree.hashCode());
		result = prime * result + ((vertexTwo == null) ? 0 : vertexTwo.hashCode());
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
		Triangle other = (Triangle) obj;
		if (materialDiffuse == null) {
			if (other.materialDiffuse != null)
				return false;
		} else if (!materialDiffuse.equals(other.materialDiffuse))
			return false;
		if (Double.doubleToLongBits(phongConstant) != Double.doubleToLongBits(other.phongConstant))
			return false;
		if (specularHighlight == null) {
			if (other.specularHighlight != null)
				return false;
		} else if (!specularHighlight.equals(other.specularHighlight))
			return false;
		if (vertexOne == null) {
			if (other.vertexOne != null)
				return false;
		} else if (!vertexOne.equals(other.vertexOne))
			return false;
		if (vertexThree == null) {
			if (other.vertexThree != null)
				return false;
		} else if (!vertexThree.equals(other.vertexThree))
			return false;
		if (vertexTwo == null) {
			if (other.vertexTwo != null)
				return false;
		} else if (!vertexTwo.equals(other.vertexTwo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[vertexOne=" + vertexOne + ", vertexTwo=" + vertexTwo + ", vertexThree=" + vertexThree
				+ ", materialDiffuse=" + materialDiffuse + ", specularHighlight=" + specularHighlight
				+ ", phongConstant=" + phongConstant + "]";
	}
}
