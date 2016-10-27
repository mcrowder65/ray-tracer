package projection;

import utility.Point2D;
import utility.Point3D;
import utility.Ray;
import utility.Vector3D;

public abstract class Projection {
	public Point3D eye;
	public Point3D lookat;
	public double distance;
	public Vector3D u, v, w;

	public abstract Ray createRay(Point2D point);
}
