package main;

import java.util.ArrayList;
import java.util.List;

public class PrivateUtilities {
	public PrivateUtilities() {
	}

	public Color specular(Color finalColor, Object winningObject, Vector3D intersectionPosition, Light lightSource,
			double ambientLight, Vector3D eye) {
		Vector3D n = winningObject.getNormalAt(intersectionPosition);

		final Color Ca = new Color(ambientLight);
		final Color Cl = Main.white;

		final Vector3D l = new Vector3D(lightSource.getPosition());
		final int p = winningObject.getPhongConstant();
		final Color Cp = new Color(winningObject.getSpecularHighlight());
		final Vector3D e = new Vector3D(eye);
		// r = 2n(n dot l) - l

		// color = Cr (Ca + Cl Max(0, n dot l)) + ClCp max(0, e dot r) ^ p
		final Color diffuse = finalColor
				.multiply(Cl.multiply(Math.max(0, n.dot(winningObject instanceof Triangle ? l.negative() : l))));
		Color specular = new Color(0);
		if (!diffuse.equals(specular)) {
			final Vector3D r = new Vector3D(n.multiply(2).multiply(n.dot(l)).sub(l));
			specular = Cl.multiply(Cp).multiply(Math.pow(Math.max(0, e.dot(r)), p));
		}
		final Color ambient = Ca.multiply(winningObject.getColor()).multiply(Cl);
		final Color surfaceColor = diffuse.add(specular);
		return surfaceColor.add(ambient);

	}

	public Color checkShadows(Color finalColor, Object winningObject, Vector3D intersectionPosition, Light lightSource,
			ArrayList<Object> sceneObjects, int indexOfWinningObject) {
		Vector3D distanceToLight = new Vector3D(
				lightSource.getPosition().add(intersectionPosition.negative()).normalize());
		if (finalColor == null)
			return null;
		float distanceToLightMagnitude = (float) distanceToLight.magnitude();

		Ray shadowRay = new Ray(intersectionPosition, lightSource.getPosition());

		List<Double> secondaryIntersections = new ArrayList<>();
		for (int objectIndex = 0; objectIndex < sceneObjects.size(); objectIndex++) {
			if (objectIndex != indexOfWinningObject) {
				secondaryIntersections.add(sceneObjects.get(objectIndex).findIntersection(shadowRay));
			}

		}

		for (int c = 0; c < secondaryIntersections.size(); c++) {
			if (secondaryIntersections.get(c) > 0) {
				if (secondaryIntersections.get(c) <= distanceToLightMagnitude) {
					return new Color(Main.black);
				}
			}

		}
		return new Color(finalColor);
	}

	public Color reflective(Object winningObject, Vector3D intersectionPosition, Vector3D intersectingRayDirection,
			ArrayList<Object> objects, Light lightSource) {
		Vector3D winningObjectNormal = winningObject.getNormalAt(intersectionPosition);
		float accuracy = (float) 0.0000001;
		double dot1 = winningObjectNormal.dot(intersectingRayDirection.negative());
		Vector3D scalar1 = winningObjectNormal.multiply(dot1);
		Vector3D add1 = scalar1.add(intersectingRayDirection);
		Vector3D scalar2 = add1.multiply(2);
		Vector3D add2 = intersectingRayDirection.negative().add(scalar2);
		Vector3D reflectionDirection = add2.normalize();

		Ray reflectionRay = new Ray(intersectionPosition, reflectionDirection);

		// determine what the ray intersects with first
		List<Double> reflectionIntersections = new ArrayList<>();
		for (int reflectionIndex = 0; reflectionIndex < objects.size(); reflectionIndex++) {
			reflectionIntersections.add(objects.get(reflectionIndex).findIntersection(reflectionRay));
		}
		int indexOfWinningObjectWithReflection = PublicUtilities
				.winningObjectIndex((ArrayList<Double>) reflectionIntersections);
		Color finalColor = null;
		if (indexOfWinningObjectWithReflection != -1) {
			// reflection ray missed everything else
			if (reflectionIntersections.get(indexOfWinningObjectWithReflection) > accuracy) {
				// determine the position and direction at the point of
				// intersection with the reflection ray
				// the ray only affects the color if it reflected off something
				Object obj = objects.get(indexOfWinningObjectWithReflection);

				Vector3D reflectionIntersectionPosition = intersectionPosition.add(
						reflectionDirection.multiply(reflectionIntersections.get(indexOfWinningObjectWithReflection)));
				Vector3D reflectionIntersectionRayDirection = reflectionDirection;

				// check to see if there should be a shadow
				Color shadowColor = checkShadows(obj.getColor(), obj, reflectionIntersectionPosition, lightSource,
						objects, indexOfWinningObjectWithReflection);
				if (shadowColor != null && shadowColor.equals(Main.black)) {
					return Main.black;
				}
				Color reflectionIntersectionColor = reflective(obj, reflectionIntersectionPosition,
						reflectionIntersectionRayDirection, objects, lightSource);
				if (reflectionIntersectionColor != null) {
					if (obj.getColor() != null) {
						finalColor = obj.getColor();
					} else {
						// Takes care of the circles on the side
						finalColor = reflectionIntersectionColor.multiply(winningObject.getReflective());
					}

				}
			} else {
				// takes care of specs on the triangle reflections
				finalColor = winningObject.getColor();
			}
		} else {
			// takes care of the surrounding color of the sphere
			finalColor = Main.backgroundColor.multiply(winningObject.getReflective());
		}

		return finalColor;

	}

	public Color translucent(Color finalColor) {
		return finalColor;
	}

}
