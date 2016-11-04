package main;

public class Color {
	public double red, green, blue;

	public Color(double red, double green, double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public Color(Color color) {

		this.red = color.red;
		this.green = color.green;
		this.blue = color.blue;
	}

	public double brightness() {
		return (red + green + blue) / 3;
	}

	Color scale(double scalar) {
		return new Color(red * scalar, green * scalar, blue * scalar);
	}

	public Color add(Color color) {
		return new Color(red + color.red, green + color.green, blue + color.blue);
	}

	public Color multiply(Color color) {
		return new Color(red * color.red, green * color.green, blue * color.blue);
	}

	Color average(Color color) {
		return new Color((red + color.red) / 2, (green + color.green) / 2, (blue + color.blue) / 2);
	}

	Color clip() {
		double alllight = red + green + blue;
		double excesslight = alllight - 3;
		if (excesslight > 0) {
			red = red + excesslight * (red / alllight);
			green = green + excesslight * (green / alllight);
			blue = blue + excesslight * (blue / alllight);
		}
		if (red > 1) {
			red = 1;
		}
		if (green > 1) {
			green = 1;
		}
		if (blue > 1) {
			blue = 1;
		}
		if (red < 0) {
			red = 0;
		}
		if (green < 0) {
			green = 0;
		}
		if (blue < 0) {
			blue = 0;
		}

		return new Color(red, green, blue);
	}

}
