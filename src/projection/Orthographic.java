package projection;

import main.Main;
import utility.Point2D;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public class Orthographic extends Projection {

	@Override
	public Ray createRay(Point2D point) {
		double size = Main.world.viewplane.size;
		Point3D origin = new Point3D(size * point.x, size * point.y, 100);
		Vector3D direction = new Vector3D(0.0, 0.0, -1.0);
		return new Ray(origin, direction);
	}
}
