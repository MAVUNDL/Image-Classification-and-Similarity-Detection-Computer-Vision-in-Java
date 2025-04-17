package preprocessing;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ImagePreprocessing implements PreprocessingAlgorithms{
    /**
     * This method will convert a given image into a grayscale image
     *
     * @param image input image
     * @return writable image of the grayscaled image
     */
    @Override
    public WritableImage ImageGrayScaling(Image image) {
        // getting dimension from the given image
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // creating a writable image instance
        WritableImage grayScaledImage = new WritableImage(width, height);

        // getting a pixel reader from the Image and creating a pixel writer object
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = grayScaledImage.getPixelWriter();

        // loop through all the pixels in the image
        for(int x = 0; x < height; x++){
            for(int y = 0; y < width; y++){
                // get the pixel at the current location
                int pixel = reader.getArgb(x, y);
                // extract the ARGB channels from the pixel
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;
                // converting the RGB values to a grayscale value
                int grayValue = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
                // constructing a gray pixel
                int grayPixel = (alpha << 24) | (grayValue << 16) | (grayValue << 8) | grayValue;
                // writing the gray pixel to the new image
                writer.setArgb(x, y, grayPixel);
            }
        }
        // return the grayscaled image
        return grayScaledImage;
    }

    /**
     * This method perform a gaussian blurring algorithm on the given image to reduce noise
     *
     * @param image input image
     * @return writable image of the blurred image
     */
    @Override
    public WritableImage GaussianBlurAlgorithm(Image image) {
        return null;
    }
}
