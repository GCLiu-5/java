package classend;

import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;

public final class MatrixToImageWriter {
	private static final int black = 0xFF000000;
	private static final int white = 0xFFFFFFFF;
	
	private MatrixToImageWriter() {}
	
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
	    int height = matrix.getHeight();
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    for (int x = 0; x < width; x++) {
	    	for (int y = 0; y < height; y++) {
	    		image.setRGB(x, y, matrix.get(x, y) ? black : white);
	    		}
	    	}
	    return image;
	    }
	
	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
			}
		}
}
