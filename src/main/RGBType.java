package main;

public class RGBType {
	public double r, g, b;

	RGBType(double r, double g, double b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public int toInteger() {
		return (int) (r * 255) << 16 | (int) (g * 255) << 8 | (int) (b * 255);
	}
}
