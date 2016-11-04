package main;

import java.util.ArrayList;
import java.util.List;

public class PrivateUtilities {
	public PrivateUtilities() {
	}

	public Color specularHighlight(Color finalColor, Object winningObject, Vector3D intersectionPosition,
			Vector3D intersectionRayDirection, Light lightSource, ArrayList<Object> sceneObjects,
			int indexOfWinningObject) {
		if (finalColor.equals(Main.black)) {
			return new Color(finalColor);
		}

		// TODO implement specularhighlight, check slides #16
		// special [0-1]
		Vector3D winningObjectNormal = sceneObjects.get(indexOfWinningObject).getNormalAt(intersectionPosition);
		Vector3D lightDirection = lightSource.getPosition().add(intersectionPosition.negative()).normalize();
		double dot1 = winningObjectNormal.dot(intersectionRayDirection.negative());
		Vector3D scalar1 = new Vector3D(winningObjectNormal.multiply(dot1));
		Vector3D add1 = new Vector3D(scalar1.add(intersectionRayDirection));
		Vector3D scalar2 = new Vector3D(add1.multiply(2));
		Vector3D add2 = new Vector3D(intersectionRayDirection.negative().add(scalar2));
		Vector3D reflection_direction = new Vector3D(add2.normalize());

		double specular = reflection_direction.dot(lightDirection);
		if (specular > 0) {
			specular = Math.pow(specular, 10);
			RGB temp = new RGB();
			temp.r = lightSource.getColor().red * winningObject.getSpecularHighlight().r;
			temp.g = lightSource.getColor().green * winningObject.getSpecularHighlight().g;
			temp.b = lightSource.getColor().blue * winningObject.getSpecularHighlight().b;
			finalColor = new Color(temp.r, temp.g, temp.b);

			// finalColor =
			// finalColor.add(lightSource.getColor().scale(specular));

		}
		return new Color(finalColor);
	}

	public Color checkShadows(Color finalColor, Object winningObject, Vector3D intersectionPosition, Light lightSource,
			ArrayList<Object> sceneObjects, int indexOfWinningObject) {
		Vector3D distanceToLight = lightSource.getPosition().add(intersectionPosition.negative()).normalize();
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
