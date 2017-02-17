package org.eugene.image;


import java.io.InputStream;

public class ImageMain {
	final static int THUMBOR_MAX_PIXELS = 75000000 ;

	public static void main(String[] args) {
		
		getImageInfo(Thread.currentThread().getContextClassLoader().getResourceAsStream("1.60MB.jpg"));
		getImageInfo(Thread.currentThread().getContextClassLoader().getResourceAsStream("5.78MB.jpg"));
		getImageInfo(Thread.currentThread().getContextClassLoader().getResourceAsStream("zalman.jpg"));
	}


	private static void getImageInfo(InputStream stream) {
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setInput(stream);
		if (!imageInfo.check()) {
			System.err.println("Not a supported image file format.");
			return;
		}
		int width = imageInfo.getWidth();
		int height = imageInfo.getHeight();
		int heightByWidth = width * height;
		String exceedsThumborMax = heightByWidth > THUMBOR_MAX_PIXELS ?  "(exceeds Thumbor max) " : "";
		
		System.out.println(
				imageInfo.getFormatName() + ", " 
				+ imageInfo.getMimeType() +	", " 
				+ width + " x " + height 
				+ "=" + heightByWidth + " pixels" 
				+ exceedsThumborMax +	", " 
				+ imageInfo.getBitsPerPixel() + " bits per pixel.");
	}

}
