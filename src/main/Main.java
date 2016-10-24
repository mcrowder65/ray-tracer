package main;

import java.util.List;

public class Main {
	static Tuple lookAt;
	static Tuple lookFrom;
	static Tuple lookUp;
	static int fieldOfView;
	static Tuple directionToLight;
	static Tuple lightColor;
	static Tuple ambientLight;
	static Tuple backgroundColor;
	static List<Sphere> spheres;
	static List<Triangle> triangles;

	private static void initDiffuse() {
		lookAt = new Tuple(0, 0, 0);
		lookFrom = new Tuple(0, 0, 1);
		lookUp = new Tuple(0, 1, 0);
		fieldOfView = 28;
		directionToLight = new Tuple(1, 0, 0);
		lightColor = new Tuple(1, 1, 1);
		ambientLight = new Tuple(.1, .1, .1);
		backgroundColor = new Tuple(.2, .2, .2);
		spheres.add(new Sphere(new Tuple(.35, 0, -.1), .05, new Tuple(1, 1, 1), new Tuple(1, 1, 1), 4));
		spheres.add(new Sphere(new Tuple(.2, 0, -.1), 0.075, new Tuple(1, 0, 0), new Tuple(.5, 1, .5), 32));
		spheres.add(new Sphere(new Tuple(-.6, 0, 0), .3, new Tuple(0, 1, 0), new Tuple(0.5, 1, 0.5), 32));
		triangles.add(new Triangle(new Tuple(.3, -.3, -.4), new Tuple(0, .3, -.1), new Tuple(-.3, -.3, .2),
				new Tuple(0, 0, 1), new Tuple(1, 1, 1), 32));
		triangles.add(new Triangle(new Tuple(-.2, .1, .1), new Tuple(-.2, -.5, .2), new Tuple(-.2, .1, -.3),
				new Tuple(1, 1, 0), new Tuple(1, 1, 1), 4));

	}

	private static void initScenell() {
		lookAt = new Tuple(0, 0, 0);
		lookFrom = new Tuple(0, 0, 1.2);
		lookUp = new Tuple(0, 1, 0);
		fieldOfView = 55;
		directionToLight = new Tuple(0, 1, 0);
		lightColor = new Tuple(1, 1, 1);
		ambientLight = new Tuple(0, 0, 0);
		backgroundColor = new Tuple(.2, .2, .2);
		spheres.add(new Sphere(new Tuple(0, 0.3, 0), .2, new Tuple(.75, .75, .75)));
		triangles.add(new Triangle(new Tuple(0, -.5, .5), new Tuple(1, .5, 0), new Tuple(0, -.5, -.5),
				new Tuple(0, 0, 1), new Tuple(1, 1, 1), 4));
		triangles.add(new Triangle(new Tuple(0, -.5, .5), new Tuple(0, -.5, -.5), new Tuple(-1, .5, 0),
				new Tuple(1, 1, 0), new Tuple(1, 1, 1), 4));
	}

	public static void main(String[] args) {
	}

}
