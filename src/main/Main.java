package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static Image image;
	static final Color black = new Color(0, 0, 0);
	static final Color white = new Color(1.0, 1.0, 1.0);
	static final Color blue = new Color(0, 0, 1);
	static final Color green = new Color(0, 1, 0);
	static final Color yellow = new Color(1, 1, 0);
	static final Color red = new Color(1, 0, 0);

	static Color getColorAt(Vector3D intersectionPosition, Vector3D intersectionRayDirection,
			ArrayList<Object> sceneObjects, int indexOfWinningObject, Light lightSource, double ambientLight) {
		// phong
		// r = 2n (n dot l) - l
		// cl dot cp max(0, edotr) ^ p
		Color winningObjectColor = new Color(sceneObjects.get(indexOfWinningObject).getColor());
		Vector3D winningObjectNormal = new Vector3D(
				sceneObjects.get(indexOfWinningObject).getNormalAt(intersectionPosition));
		Color finalColor = winningObjectColor.scale(ambientLight);
		Vector3D lightDirection = lightSource.position;

		// test for shadows
		// testing for shadows, cast a ray to the light
		Vector3D distanceToLight = lightSource.position.add(intersectionPosition.negative()).normalize();
		float distanceToLightMagnitude = (float) distanceToLight.magnitude();

		Ray shadowRay = new Ray(intersectionPosition,
				lightSource.position.add(intersectionPosition.negative()).normalize());

		List<Double> secondaryIntersections = new ArrayList<>();
		for (int objectIndex = 0; objectIndex < sceneObjects.size(); objectIndex++) {
			if (objectIndex != indexOfWinningObject) {
				secondaryIntersections.add(sceneObjects.get(objectIndex).findIntersection(shadowRay));
			}

		}

		for (int c = 0; c < secondaryIntersections.size(); c++) {
			if (secondaryIntersections.get(c) > 0) {
				if (secondaryIntersections.get(c) <= distanceToLightMagnitude) {
					return black;
				}
			}

		}
		// TODO implement specularhighlight
		// if (0 < winningObjectColor.get && winningObjectColor.special <= 1) {
		// float cosineAngle = (float) winningObjectNormal.dot(lightDirection);
		// finalColor = new
		// Color(finalColor.add(winningObjectColor.multiply(lightSource.color)).scale(cosineAngle));
		// // special [0-1]
		// double dot1 =
		// winningObjectNormal.dot(intersectionRayDirection.negative());
		// Vector3D scalar1 = new Vector3D(winningObjectNormal.multiply(dot1));
		// Vector3D add1 = new Vector3D(scalar1.add(intersectionRayDirection));
		// Vector3D scalar2 = new Vector3D(add1.multiply(2));
		// Vector3D add2 = new
		// Vector3D(intersectionRayDirection.negative().add(scalar2));
		// Vector3D reflection_direction = new Vector3D(add2.normalize());
		//
		// double specular = reflection_direction.dot(lightDirection);
		// if (specular > 0) {
		// specular = Math.pow(specular, 10);
		// finalColor = finalColor.add(lightSource.color.scale(specular *
		// winningObjectColor.special));
		// }
		// }

		return finalColor.clip();

	}

	public static void main(String[] args) {
		exec("rm scene.png");
		exec("pkill Preview");

		final int width = 640;
		final int height = 480;
		Image image = new Image("scene.png", width, height);
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
			Sphere sceneSphere2 = new Sphere(new Vector3D(.2, 0, -.1), 0.075, red);
			Sphere sceneSphere3 = new Sphere(new Vector3D(-.6, 0, 0), 0.3, green);
			Triangle sceneTriangle1 = new Triangle(new Vector3D(.3, -.3, -.4), new Vector3D(0, .3, -.1),
					new Vector3D(-.3, -.3, .2), blue);
			Triangle sceneTriangle2 = new Triangle(new Vector3D(-.2, .1, .1), new Vector3D(-.2, -.5, .2),
					new Vector3D(-.2, .1, -.3), yellow);
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
			Sphere sceneSphere1 = new Sphere(new Vector3D(0, .3, 0), 0.2, new Color(1, 1, 1));
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

				Vector3D cameraRayOrigin = sceneCamera.position;
				Vector3D cameraRayDirection = cameraDirection
						.add(cameraRight.multiply(xAmount - 0.5).add(cameraDown.multiply(yAmount - 0.5))).normalize();

				Ray cameraRay = new Ray(cameraRayOrigin, cameraRayDirection);
				List<Double> intersections = new ArrayList<>();

				for (int index = 0; index < sceneObjects.size(); index++) {
					double intersection = sceneObjects.get(index).findIntersection(cameraRay);
					intersections.add(intersection);
				}
				int indexOfWinningObject = Utilities.winningObjectIndex((ArrayList<Double>) intersections);
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
					Color intersectionColor = getColorAt(intersectionPosition, intersectingRayDirection,
							(ArrayList<Object>) sceneObjects, indexOfWinningObject, lightSource, ambientLight);
					pixel = new RGB(intersectionColor.red, intersectionColor.green, intersectionColor.blue);
				}
				image.buffer.setRGB(x, y, pixel.toInteger());
			}
		}
		image.write("PNG");
		exec("open scene.png");
		if (!type.equals("tutorial")) {
			exec("open examples/" + type + ".png");
		}

	}

	private static void exec(String command) {
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			System.out.println(command + " failed.");
		}
	}

}
