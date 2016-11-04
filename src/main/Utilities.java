package main;

import java.util.ArrayList;

public class Utilities {
	static int winningObjectIndex(ArrayList<Double> intersections) {

		// prevent unnessary calculations
		if (intersections.size() == 0) {
			// if there are no intersections
			return -1;
		} else if (intersections.size() == 1) {
			if (intersections.get(0) > 0) {
				// if that intersection is greater than zero then its our index
				// of minimum value
				return 0;
			} else {
				// otherwise the only intersection value is negative
				return -1;
			}
		} else {
			// otherwise there is more than one intersection
			// first find the maximum value

			double max = 0;
			for (int i = 0; i < intersections.size(); i++) {
				if (max < intersections.get(i)) {
					max = intersections.get(i);
				}
			}
			int indexOfMinimumValue = -1;
			// then starting from the maximum value find the minimum positive
			// value
			if (max > 0) {
				// we only want positive intersections
				for (int index = 0; index < intersections.size(); index++) {
					if (intersections.get(index) > 0 && intersections.get(index) <= max) {
						max = intersections.get(index);
						indexOfMinimumValue = index;
					}
				}
				return indexOfMinimumValue;
			} else {
				// all the intersections were negative
				return -1;
			}
		}
	}
}
