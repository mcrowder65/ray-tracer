package main;

public class Color {
	public double red, green, blue, special;

	public Color(double red, double green, double blue, double special) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.special = special;
	}

	public Color(Color color) {

		this.red = color.red;
		this.green = color.green;
		this.blue = color.blue;
		this.special = color.special;
	}

	public double brightness() {
		return (red + green + blue) / 3;
	}

	Color scale(double scalar) {
		return new Color(red * scalar, green * scalar, blue * scalar, special);
	}

	public Color add(Color color) {
		return new Color(red + color.red, green + color.green, blue + color.blue, special);
	}

	public Color multiply(Color color) {
		return new Color(red * color.red, green * color.green, blue * color.blue, special);
	}

	Color average(Color color) {
		return new Color((red + color.red) / 2, (green + color.green) / 2, (blue + color.blue) / 2, special);
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

		return new Color(red, green, blue, special);
	}

}
