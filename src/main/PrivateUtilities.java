package main;

import java.util.ArrayList;
import java.util.List;

public class PrivateUtilities {
	public PrivateUtilities() {
	}

	public Color color(Color finalColor, Object winningObject, Vector3D intersectionPosition, Light lightSource,
			double ambientLight, Vector3D eye) {

		Vector3D n = winningObject.getNormalAt(intersectionPosition);
		Color Ca = new Color(ambientLight);
		Color Cl = new Color(lightSource.getColor());

		Vector3D l = new Vector3D(lightSource.getPosition());
		final int p = winningObject.getPhongConstant();
		Color Cp = new Color(winningObject.getSpecularHighlight());
		Vector3D e = new Vector3D(eye);
		// r = 2n(n dot l) - l

		// color = Cr (Ca + Cl Max(0, n dot l)) + ClCp max(0, e dot r) ^ p
		// Cr(Ca + Cl max (0, n dot l))
		Color diffuse = finalColor.multiply(Ca.add(Cl.multiply(Math.max(0, n.dot(l)))));
		Vector3D r = new Vector3D(n.multiply(2).multiply(n.dot(l)).sub(l));
		Color specular = Cl.multiply(Cp).multiply(Math.pow(Math.max(0, e.dot(r)), p));
		Color surfaceColor = diffuse.add(specular);
		return surfaceColor;

	}

	public Color checkShadows(Color finalColor, Object winningObject, Vector3D intersectionPosition, Light lightSource,
			ArrayList<Object> sceneObjects, int indexOfWinningObject) {
		Vector3D distanceToLight = new Vector3D(
				lightSource.getPosition().add(intersectionPosition.negative()).normalize());
		float distanceToLightMagnitude = (float) distanceToLight.magnitude();

		Ray shadowRay = new Ray(intersectionPosition,
				lightSource.getPosition().add(intersectionPosition.negative()).normalize());

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
