package preprocessing;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public interface PreprocessingAlgorithms {
    /**
     * This method will convert a given image into a grayscale image
     * @param image input image
     * @return writable image of the grayscaled image
     */
    WritableImage ImageGrayScaling(Image image);

    /**
     * This method perform a gaussian blurring algorithm on the given image to reduce noise
     * @param image input image
     * @return writable image of the blurred image
     */
    WritableImage GaussianBlurAlgorithm(Image image);
}
