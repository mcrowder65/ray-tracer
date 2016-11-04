package main;

public class Light {
	Vector3D position;
	Color color;

	Light(Vector3D position, Color color) {
		this.position = new Vector3D(position);
		this.color = new Color(color);
	}

}
