package main;

public class Light {
	private Vector3D position;
	private Color color;

	public Vector3D getPosition() {
		return position;
	}

	public void setPosition(Vector3D position) {
		this.position = position;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		Light other = (Light) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	Light(Vector3D position, Color color) {
		this.position = new Vector3D(position);
		this.color = new Color(color);
	}

}
