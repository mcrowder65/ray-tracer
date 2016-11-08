package main;

import java.util.ArrayList;
import java.util.List;

public class PrivateUtilities {
	public PrivateUtilities() {
	}

	private float mix(float a, float b, float mix) {
		return b * mix + a * (1 - mix);
	}

	final private static int MAX_RAY_DEPTH = 5;
	float bias = (float) 1e-4;

	public Color colorThisShiz(Color finalColor, Object winningObject, Vector3D intersectionPosition, Light lightSource,
			double ambientLight, Vector3D eye, Vector3D raydir, Vector3D rayorig, ArrayList<Object> objects,
			int depth) {
		final Vector3D n = winningObject.getNormalAt(intersectionPosition);
		if (winningObject.getReflective() != null && depth < MAX_RAY_DEPTH) {
			float facingratio = (float) raydir.negative().dot(n);
			// change the mix value to tweak the effect
			float fresneleffect = mix((float) Math.pow(1 - facingratio, 3), 1, (float) 0.1);
			// compute reflection direction (not need to normalize because all
			// vectors
			// are already normalized)
			Vector3D refldir = eye.sub(n).multiply(2).multiply(raydir.dot(n));
			refldir.normalize();
			// const Vec3f &rayorig,
			// const Vec3f &raydir,
			// const std::vector<Sphere> &spheres,
			// const int &depth)

			Color reflection = colorThisShiz(finalColor, winningObject, intersectionPosition, lightSource, ambientLight,
					eye, intersectionPosition.add(n.multiply(bias)), refldir, objects, depth + 1);
			// if the sphere is also transparent compute refraction ray
			// (transmission)

			// the result is a mix of reflection and refraction (if the sphere
			// is transparent)
			finalColor = (reflection.multiply(fresneleffect).multiply(winningObject.getColor()));
			return finalColor;
		} else {

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

	}

	public Color checkShadows(Color finalColor, Object winningObject, Vector3D intersectionPosition, Light lightSource,
			ArrayList<Object> sceneObjects, int indexOfWinningObject) {
		Vector3D distanceToLight = new Vector3D(
				lightSource.getPosition().add(intersectionPosition.negative()).normalize());
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
}
