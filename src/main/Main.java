package main;

import java.util.ArrayList;
import java.util.List;

public class Main {

	// TODO implement phong
	// TODO implement specular
	// TODO implement ambient
	static Image image;
	static final Color black = new Color(0, 0, 0);
	static final Color white = new Color(1.0, 1.0, 1.0);
	static final Color blue = new Color(0, 0, 1);
	static final Color green = new Color(0, 1, 0);
	static final Color yellow = new Color(1, 1, 0);
	static final Color red = new Color(1, 0, 0);
	static final int width = 640;
	static final int height = 480;
	static final String imageName = "scene.png";

	public static void main(String[] args) {

		PublicUtilities.exec("rm " + imageName);
		PublicUtilities.exec("pkill Preview");

		Image image = new Image(imageName, width, height);
		double ambientLight = -1;
		Vector3D cameraPosition = null;
		Vector3D lookAt = null;
		List<Object> sceneObjects = new ArrayList<>();

		Vector3D Y = new Vector3D(0, -1, 0);
		String type = "diffuse";
		Light lightSource = null;
		RGB backgroundColor = new RGB(0.2, 0.2, 0.2);

		if (type.equals("diffuse")) {
			ambientLight = .2;
			cameraPosition = new Vector3D(0, 0, 1);
			lookAt = new Vector3D(0, 0, 0);

			Vector3D lightPosition = new Vector3D(1, 0, 0);
			lightSource = new Light(lightPosition, white);
			// spheres
			Sphere sceneSphere1 = new Sphere(new Vector3D(.35, 0, -.1), 0.05, new Color(1, 1, 1));
			sceneSphere1.setSpecularHighlight(new RGB(1, 1, 1));
			sceneSphere1.setMaterialDiffuse(new RGB(1, 1, 1));
			sceneSphere1.setPhongConstant(4);

			Sphere sceneSphere2 = new Sphere(new Vector3D(.2, 0, -.1), 0.075, red);
			sceneSphere2.setSpecularHighlight(new RGB(.5, 1, .5));
			sceneSphere2.setMaterialDiffuse(new RGB(1, 0, 0));
			sceneSphere2.setPhongConstant(32);

			Sphere sceneSphere3 = new Sphere(new Vector3D(-.6, 0, 0), 0.3, green);
			sceneSphere3.setSpecularHighlight(new RGB(.5, 1, .5));
			sceneSphere3.setMaterialDiffuse(new RGB(0, 1, 0));
			sceneSphere3.setPhongConstant(32);

			Triangle sceneTriangle1 = new Triangle(new Vector3D(.3, -.3, -.4), new Vector3D(0, .3, -.1),
					new Vector3D(-.3, -.3, .2), blue);
			sceneTriangle1.setSpecularHighlight(new RGB(1, 1, 1));
			sceneTriangle1.setMaterialDiffuse(new RGB(0, 0, 1));
			sceneTriangle1.setPhongConstant(32);

			Triangle sceneTriangle2 = new Triangle(new Vector3D(-.2, .1, .1), new Vector3D(-.2, -.5, .2),
					new Vector3D(-.2, .1, -.3), yellow);
			sceneTriangle2.setSpecularHighlight(new RGB(1, 1, 1));
			sceneTriangle2.setMaterialDiffuse(new RGB(1, 1, 0));
			sceneTriangle2.setPhongConstant(4);

			sceneObjects.add(sceneSphere1);
			sceneObjects.add(sceneSphere2);
			sceneObjects.add(sceneSphere3);
			sceneObjects.add(sceneTriangle1);
			sceneObjects.add(sceneTriangle2);
		} else if (type.equals("scenell")) {
			ambientLight = .1;
			cameraPosition = new Vector3D(0, 0, 1.2);
			lookAt = new Vector3D(0, 0, 0);

			Vector3D lightPosition = new Vector3D(0, 1, 0);
			lightSource = new Light(lightPosition, white);
			Sphere sceneSphere1 = new Sphere(new Vector3D(0, .3, 0), 0.2, white);
			Triangle sceneTriangle1 = new Triangle(new Vector3D(0, -.5, .5), new Vector3D(1, .5, 0),
					new Vector3D(0, -.5, -.5), blue);
			Triangle sceneTriangle2 = new Triangle(new Vector3D(0, -.5, .5), new Vector3D(0, -.5, -.5),
					new Vector3D(-1, .5, 0), yellow);
			sceneObjects.add(sceneSphere1);
			sceneObjects.add(sceneTriangle1);
			sceneObjects.add(sceneTriangle2);

		} else if (type.equals("tutorial")) {
			Y = new Vector3D(0, 1, 0);
			ambientLight = .2;
			cameraPosition = new Vector3D(3, 1.5, -4);
			lookAt = new Vector3D(0, 0, 0);
			Vector3D lightPosition = new Vector3D(-7, 10, -10);
			lightSource = new Light(lightPosition, white);
			Sphere sceneSphere1 = new Sphere(new Vector3D(0, 0, 0), 1, green);
			Triangle sceneTriangle1 = new Triangle(new Vector3D(3, 0, 0), new Vector3D(0, 3, 0), new Vector3D(0, 0, 3),
					blue);
			Triangle sceneTriangle2 = new Triangle(new Vector3D(-2, 0, 0), new Vector3D(-5, 3, 0),
					new Vector3D(-5, 0, 3), blue);
			sceneObjects.add(sceneSphere1);
			sceneObjects.add(sceneTriangle1);
			sceneObjects.add(sceneTriangle2);
		}
		double aspectratio = (double) width / (double) height;

		Vector3D differenceBetween = new Vector3D(cameraPosition.x - lookAt.x, cameraPosition.y - lookAt.y,
				cameraPosition.z - lookAt.z);

		Vector3D cameraDirection = new Vector3D(differenceBetween.negative().normalize());
		Vector3D cameraRight = new Vector3D(Y.cross(cameraDirection).normalize());
		Vector3D cameraDown = new Vector3D(cameraRight.cross(cameraDirection));
		Camera sceneCamera = new Camera(cameraPosition, cameraDirection, cameraRight, cameraDown);
		double xAmount, yAmount;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				xAmount = ((x + 0.5) / width) * aspectratio - (((width - height) / (double) height) / 2);
				yAmount = ((height - y) + 0.5) / height;

				Vector3D cameraRayOrigin = sceneCamera.getPosition();
				Vector3D cameraRayDirection = cameraDirection
						.add(cameraRight.multiply(xAmount - 0.5).add(cameraDown.multiply(yAmount - 0.5))).normalize();

				Ray cameraRay = new Ray(cameraRayOrigin, cameraRayDirection);
				List<Double> intersections = new ArrayList<>();

				for (int index = 0; index < sceneObjects.size(); index++) {
					double intersection = sceneObjects.get(index).findIntersection(cameraRay);
					intersections.add(intersection);
				}
				int indexOfWinningObject = PublicUtilities.winningObjectIndex((ArrayList<Double>) intersections);
				// no intersection, set to background color.
				RGB pixel = null;
				if (indexOfWinningObject == -1) {
					pixel = new RGB(backgroundColor.r, backgroundColor.g, backgroundColor.b);
				} else {
					// determines the position and direction vectors at the
					// point of intersection
					Vector3D intersectionPosition = new Vector3D(
							cameraRayOrigin.add(cameraRayDirection.multiply(intersections.get(indexOfWinningObject))));
					Vector3D intersectingRayDirection = new Vector3D(cameraRayDirection);
					// index coresponds to an object in our scene
					Color intersectionColor = PublicUtilities.getPixel(intersectionPosition, intersectingRayDirection,
							(ArrayList<Object>) sceneObjects, indexOfWinningObject, lightSource, ambientLight, lookAt);
					pixel = new RGB(intersectionColor.red, intersectionColor.green, intersectionColor.blue);
				}
				image.buffer.setRGB(x, y, pixel.toInteger());
			}
		}
		image.write("PNG");
		PublicUtilities.exec("open " + imageName);
		if (!type.equals("tutorial")) {
			PublicUtilities.exec("open examples/" + type + ".png");
		}

	}

}
