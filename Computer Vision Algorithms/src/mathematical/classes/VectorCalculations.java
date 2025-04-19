package mathematical.classes;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import mathematical.interfaces.Algorithms;

import java.io.IOException;

public class VectorCalculations implements Algorithms {
    /**
     * This method takes an image and flattens it into a vector(1D array)
     *
     * @param image the image to be processed
     * @return return a vector of the image
     */
    @Override
    public int[] flattenImage(Image image) {
        // get the dimensions
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        // create the vector
        int[] imageVector = new int[width * height];
        // create pixel reader
        PixelReader reader = image.getPixelReader();
        // iterate and get all the pixels
        for(int x = 0; x < height; x++){
            for(int y = 0; y < width; y++){
                // get argb
                int argb = reader.getArgb(x, y);
                // extract channels
                int red = (argb >> 16) & 0xFF;
                int green = (argb >> 8) & 0xFF;
                int blue = argb & 0xFF;

                // compute gray value
                int grayValue = (int) (0.299 * red + 0.587 * green + 0.144 * blue);
                // flatten row wise
                imageVector[x * width + y] = grayValue;
            }
        }
        return imageVector;
    }

    /**
     * This method calculates the Euclidean distance between two vectors
     *
     * @param vectorOne the first vector
     * @param vectorTwo the second vector
     * @return returns the result of the operation
     */
    @Override
    public double EuclideanDistance(int[] vectorOne, int[] vectorTwo) {
        double sum = 0;
        // ensure vectors have the same length
        assert vectorOne.length == vectorTwo.length;
        for(int i= 0; i < vectorOne.length; i++){
            sum += Math.pow(vectorOne[i] - vectorTwo[i], 2);
        }
        // return square root of sum
        return Math.sqrt(sum);
    }

    /**
     * This method calculates the cosine similarity between two vectors
     *
     * @param vectorOne the first vector
     * @param vectorTwo the second vector
     * @return returns the result of the operation
     */
    @Override
    public double cosineSimilarity(int[] vectorOne, int[] vectorTwo) {
        double dotProduct = 0, NormOfVectorOne = 0, NormOfVectorTwo = 0;
        // ensure lengths are the same
        assert vectorOne.length == vectorTwo.length;
        // calculate the dot product and norms of the two vectors
        for(int i = 0; i < vectorOne.length; i++){
            dotProduct += vectorOne[i] * vectorTwo[i];
            NormOfVectorOne += Math.pow(vectorOne[i], 2);
            NormOfVectorTwo += Math.pow(vectorTwo[i], 2);
        }
        // return the cosine angle of the two vectors
        return dotProduct / (Math.sqrt(NormOfVectorOne) * Math.sqrt(NormOfVectorTwo));
    }

    /**
     * This method resizes the given image to the desired dimension
     *
     * @param image         the image to be resized
     * @param desiredWidth  desired width
     * @param desiredHeight desired height
     * @return returns the new resized image
     */
    @Override
    public Image resizeImage(Image image, int desiredWidth, int desiredHeight) {
        // creating a writable image object with the desired dimensions
        WritableImage resizedImage = new WritableImage(desiredWidth, desiredHeight);
        // get pixel reader and writer
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = resizedImage.getPixelWriter();
        // creating scale factors
        double xScaleFactor = image.getHeight() / desiredHeight;
        double yScaleFactor = image.getWidth() / desiredWidth;
        // get each pixel from the current image and write it on the new image
        for(int x = 0; x < desiredHeight; x++){
            for(int y = 0; y < desiredWidth; y++){
                int xLocation = (int) (x * xScaleFactor);
                int yLocation = (int) (y * yScaleFactor);
                pixelWriter.setArgb(x,  y, pixelReader.getArgb(x, y));
            }
        }
        // return the scaled image
        return resizedImage;
    }
}
