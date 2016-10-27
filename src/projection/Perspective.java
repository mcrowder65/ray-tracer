package projection;

import main.Main;
import utility.Point2D;
import utility.Point3D;
import utility.Ray;

public class Perspective extends Projection {
	public Perspective(Point3D eye, Point3D lookat, double FOV) {
		this.eye = new Point3D(eye);
		this.lookat = new Point3D(lookat);
		this.distance = Main.world.viewplane.height / 2 / Math.tan(Math.toRadians(FOV));

	}

	@Override
	public Ray createRay(Point2D point) {
		// TODO Auto-generated method stub
		return null;
	}

}
