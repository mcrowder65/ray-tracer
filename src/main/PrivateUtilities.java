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

	final float accuracy = (float) 0.0000001;
	final int MAX_DEPTH = 5;
	final double bias = 1e-4;

	public Color reflectiveAndTranslucent(Object winningObject, Vector3D intersectionPosition,
			Vector3D intersectingRayDirection, ArrayList<Object> objects, Light lightSource, int depth) {
		Vector3D N = winningObject.getNormalAt(intersectionPosition);
		if (winningObject.getReflective() != null) {

			double dot1 = N.dot(intersectingRayDirection.negative());
			Vector3D scalar1 = N.multiply(dot1);
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
					// the ray only affects the color if it reflected off
					// something
					Object obj = objects.get(indexOfWinningObjectWithReflection);

					Vector3D reflectionIntersectionPosition = intersectionPosition.add(reflectionDirection
							.multiply(reflectionIntersections.get(indexOfWinningObjectWithReflection)));
					Vector3D reflectionIntersectionRayDirection = reflectionDirection;

					// check to see if there should be a shadow
					Color shadowColor = checkShadows(obj.getColor(), obj, reflectionIntersectionPosition, lightSource,
							objects, indexOfWinningObjectWithReflection);
					if (shadowColor != null && shadowColor.equals(Main.black)) {
						return Main.black;
					}
					Color reflectionIntersectionColor = reflectiveAndTranslucent(obj, reflectionIntersectionPosition,
							reflectionIntersectionRayDirection, objects, lightSource, depth);
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
		} else if (winningObject.getTranslucent() != null && depth < MAX_DEPTH) {
			Color finalColor = null;
			double NdotI = intersectingRayDirection.dot(N);
			double ior, n1, n2, cos_t;
			if (NdotI > 0) {
				n1 = 1;
				n2 = 1.52;
				N = N.negative();
			} else {
				n1 = 1.52;
				n2 = 1;
				NdotI = -NdotI;
			}
			ior = n2 / n1;
			cos_t = ior * ior * (1 - NdotI * NdotI);
			Ray ray_refracted = new Ray(intersectionPosition, intersectingRayDirection.refract(N, ior, NdotI, cos_t));
			List<Double> refractionIntersections = new ArrayList<>();
			for (int refractionIndex = 0; refractionIndex < objects.size(); refractionIndex++) {
				refractionIntersections.add(objects.get(refractionIndex).findIntersection(ray_refracted));
			}
			int indexOfWinningObjectWithRefraction = PublicUtilities
					.winningObjectIndex((ArrayList<Double>) refractionIntersections);
			if (indexOfWinningObjectWithRefraction != -1) {
				// reflection ray missed everything else
				if (refractionIntersections.get(indexOfWinningObjectWithRefraction) > accuracy) {
					// determine the position and direction at the point of
					// intersection with the reflection ray
					// the ray only affects the color if it reflected off
					// something
					Object obj = objects.get(indexOfWinningObjectWithRefraction);

					Vector3D refractionIntersectionPosition = intersectionPosition.add(ray_refracted.origin
							.multiply(refractionIntersections.get(indexOfWinningObjectWithRefraction)));
					Vector3D refractionIntersectionRayDirection = ray_refracted.direction;

					Color refractionIntersectionColor = reflectiveAndTranslucent(obj, refractionIntersectionPosition,
							refractionIntersectionRayDirection, objects, lightSource, depth + 1);
					if (refractionIntersectionColor != null) {
						// System.out.println("refractionIntersectionColor not
						// null");
						finalColor = refractionIntersectionColor.multiply(winningObject.getTranslucent());

					} else {
						finalColor = new Color(winningObject.getTranslucent());
					}
				}
			}

			return finalColor;
		}
		return winningObject.getColor();

	}

	private double mix(double a, double b, double mix) {
		return b * mix + a * (1 - mix);
	}

	public Color test(Object winningObject, Vector3D rayorig, Vector3D raydir, ArrayList<Object> objects,
			Light lightSource, int depth) {
		Vector3D N = winningObject.getNormalAt(rayorig);
		Color surfaceColor = new Color(0);
		Vector3D phit = rayorig;
		Vector3D nhit = winningObject.getNormalAt(rayorig);
		boolean inside = false;
		if (raydir.dot(nhit) > 0) {
			nhit = nhit.negative();
			inside = true;
		}
		if ((winningObject.getTranslucent() != null || winningObject.getReflective() != null) && depth < MAX_DEPTH) {
			double facingratio = raydir.negative().dot(nhit);
			double fresneleffect = mix(Math.pow(1 - facingratio, 3), 1, .1);
			Vector3D refldir = raydir.sub(nhit.multiply(2).multiply(raydir.dot(nhit)));
			Color reflection = test(winningObject, phit.add(nhit.multiply(bias)), refldir, objects, lightSource,
					depth + 1);

			// TODO reflection trace
			Color refraction = new Color(0);
			if (winningObject.getTranslucent() != null) {
				double ior = 1.1;
				double eta = inside ? ior : 1 / ior;
				double cosi = nhit.negative().dot(raydir);
				double k = 1 - eta * eta * (1 - cosi * cosi);
				Vector3D refrdir = raydir.multiply(eta).add(nhit.multiply(eta * cosi - Math.sqrt(k))).normalize();
				refraction = test(winningObject, phit.sub(nhit.multiply(bias)), refrdir, objects, lightSource,
						depth + 1);
			}
			Color reflectionPart = reflection.multiply(fresneleffect);
			Color refractionPart = winningObject.getTranslucent() != null
					? refraction.multiply(1 - fresneleffect).multiply(winningObject.getTranslucent()) : new Color(1);

			surfaceColor = reflectionPart.multiply(refractionPart);
		}
		return surfaceColor;

	}
}
