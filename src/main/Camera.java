package main;

public class Camera {
	private Vector3D position, direction, right, down;

	Camera(Vector3D position, Vector3D direction, Vector3D right, Vector3D down) {
		this.position = new Vector3D(position);
		this.direction = new Vector3D(direction);
		this.right = new Vector3D(right);
		this.down = new Vector3D(down);
	}

	public Vector3D getPosition() {
		return position;
	}

	public void setPosition(Vector3D position) {
		this.position = position;
	}

	public Vector3D getDirection() {
		return direction;
	}

	public void setDirection(Vector3D direction) {
		this.direction = direction;
	}

	public Vector3D getRight() {
		return right;
	}

	public void setRight(Vector3D right) {
		this.right = right;
	}

	public Vector3D getDown() {
		return down;
	}

	public void setDown(Vector3D down) {
		this.down = down;
	}
}
