package graphical.interfaces;

import graphical.styling.Styler;
import javafx.beans.property.ReadOnlyDoubleProperty;

public interface LabelGUI  extends Styler {
    /**
     * @return This method returns a read only property for the width of the label
     */
    ReadOnlyDoubleProperty widthProperty();

    /**
     * @return This method returns a read only property for the height of the label
     */
    ReadOnlyDoubleProperty heightProperty();

    /**
     * This method sets the text to be displayed on the label
     * @param text the text for the label
     */
    void setTextForLabel(String text);
}
