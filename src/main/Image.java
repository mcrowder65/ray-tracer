package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	public BufferedImage buffer;
	public File image;

	public Image(String filename, int width, int height) {

		image = new File(filename);
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	public void write(String filetype) {
		try {
			ImageIO.write(buffer, filetype, image);
		} catch (IOException e) {
			System.out.println("Could not write image.");
			e.printStackTrace();
		}
	}
}
