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

	public Color(RGB temp) {
		if (temp != null) {
			this.red = temp.r;
			this.green = temp.g;
			this.blue = temp.b;
		}

	}

	public Color(double temp) {
		this.red = temp;
		this.green = temp;
		this.blue = temp;
	}

	public Color multiply(RGB temp) {
		return new Color(temp).multiply(this);
	}

	public double brightness() {
		return (red + green + blue) / 3;
	}

	public Color multiply(double scalar) {
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

	@Override
	public String toString() {
		return "Color [red=" + red + ", green=" + green + ", blue=" + blue + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(blue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(green);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(red);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(java.lang.Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Color other = (Color) obj;
		if (Double.doubleToLongBits(blue) != Double.doubleToLongBits(other.blue))
			return false;
		if (Double.doubleToLongBits(green) != Double.doubleToLongBits(other.green))
			return false;
		if (Double.doubleToLongBits(red) != Double.doubleToLongBits(other.red))
			return false;
		return true;
	}

}
