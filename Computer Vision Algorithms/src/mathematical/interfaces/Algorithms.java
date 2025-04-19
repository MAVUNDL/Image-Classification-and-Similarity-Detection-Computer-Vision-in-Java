package mathematical.interfaces;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public interface Algorithms {
    /**
     * This method takes an image and flattens it into a vector(1D array)
     * @param image the image to be processed
     * @return return a vector of the image
     */
    int[] flattenImage(Image image);

    /**
     * This method calculates the Euclidean distance between two vectors
     * @param vectorOne the first vector
     * @param vectorTwo the second vector
     * @return returns the result of the operation
     */
    double EuclideanDistance(int[] vectorOne, int[] vectorTwo);

    /**
     * This method calculates the cosine similarity between two vectors
     * @param vectorOne the first vector
     * @param vectorTwo the second vector
     * @return returns the result of the operation
     */
    double cosineSimilarity(int[] vectorOne, int[] vectorTwo);

    /**
     * This method resizes the given image to the desired dimension
     * @param image the image to be resized
     * @param desiredWidth desired width
     * @param desiredHeight desired height
     * @return returns the new resized image
     */
    Image resizeImage(Image image, int desiredWidth, int desiredHeight);
}
