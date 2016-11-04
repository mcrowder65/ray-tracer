package main;

public class Camera {
	Vector3D position, direction, right, down;

	Camera(Vector3D position, Vector3D direction, Vector3D right, Vector3D down) {
		this.position = new Vector3D(position);
		this.direction = new Vector3D(direction);
		this.right = new Vector3D(right);
		this.down = new Vector3D(down);
	}
}
