package org.eugene.image;


import java.io.InputStream;

public class ImageMain {
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
		System.out.println("\nthank you Marco Schmidt!");

	}


	private static void getImageInfo(InputStream stream) {
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setInput(stream);
		
		imageInfo.setDetermineImageNumber(true);
		if (!imageInfo.check()) {
			System.err.println("Not a supported image file format.");
			return;
		}

		int width = imageInfo.getWidth();
		int height = imageInfo.getHeight();
		int heightByWidth = width * height;
		String exceedsThumborMax = heightByWidth > THUMBOR_MAX_PIXELS ?  " (exceeds Thumbor max)" : "";
		String isAnimatedGif = imageInfo.getNumberOfImages() > 1 ? "(animated gif)," : "";
		String isProgressive = imageInfo.isProgressive() ? "(progressive image), " : "";
		
		System.out.println(
				imageInfo.getFormatName() + ", " 
				+ imageInfo.getMimeType() +	", " 
				+ width + " x " + height 
				+ " = " + heightByWidth + " pixels" 
				+ exceedsThumborMax +	", " 
				+ isAnimatedGif 
				+ isProgressive +
				+ imageInfo.getBitsPerPixel() + " bits per pixel.");
	}

}
