package common_functionalities;

import javafx.scene.image.PixelReader;

public interface CommonFunctionalities {
    int convolutionApplicationPerPixel(PixelReader reader, int xcoord, int ycoord, int[][] convolutionMatrix);
}
