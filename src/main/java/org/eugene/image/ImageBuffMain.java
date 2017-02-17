package org.eugene.image;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageBuffMain {
	final static int THUMBOR_MAX_PIXELS = 75000000 ;

	public static void main(String[] args) {
		
		//images that fail from QA
		getImageInfo(Thread.currentThread().getContextClassLoader().getResourceAsStream("1.60MB.jpg"));
		getImageInfo(Thread.currentThread().getContextClassLoader().getResourceAsStream("2.90MB.jpg"));
		getImageInfo(Thread.currentThread().getContextClassLoader().getResourceAsStream("3.79MB.jpg"));
		getImageInfo(Thread.currentThread().getContextClassLoader().getResourceAsStream("5.56MB.jpg"));
		getImageInfo(Thread.currentThread().getContextClassLoader().getResourceAsStream("5.78MB.jpg"));
		
		//animated gif
		getImageInfo(Thread.currentThread().getContextClassLoader().getResourceAsStream("party-parrot.gif"));
		
		//good image
		getImageInfo(Thread.currentThread().getContextClassLoader().getResourceAsStream("zalman.jpg"));

	}


	public static void getImageInfo(InputStream mediaStream)  {
		BufferedImage bufferedImage = null;

		try{
			bufferedImage = ImageIO.read(mediaStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int pixelWidth = bufferedImage.getWidth();
		int pixelHeight = bufferedImage.getHeight();
		int totalPixels = bufferedImage.getWidth() * bufferedImage.getHeight();
		
		System.out.print(pixelWidth + "," + pixelHeight + "," + totalPixels);
		if (totalPixels > THUMBOR_MAX_PIXELS) {
			System.out.println(" (total pixels exceed Thumbor max)"); 
		}


	}

}
