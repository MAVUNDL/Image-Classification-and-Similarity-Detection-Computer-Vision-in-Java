package graphical.classes;

import graphical.styling.Styler;
import javafx.scene.shape.Shape;

public class NodeShape implements Styler {
    /*
        class variables
     */
    private final Shape shape;

    public NodeShape(Shape shape){
        this.shape = shape;
    }
    /**
     * This method sets the css style to the underlying node
     *
     * @param css the ccs style
     */
    @Override
    public void setInlineStyle(String css) {
        this.shape.setStyle(css);
    }

    /**
     * This method replaces the css class for the underlying node
     *
     * @param css_class the css class
     */
    @Override
    public void setStyleClass(String css_class) {
        // clear current style and style class for this shape
        this.shape.setStyle(null);
        this.shape.getStyleClass().clear();
        // add the new style
        this.shape.getStyleClass().add(css_class);
    }

    /**
     * This method adds the css class for the underlying node
     *
     * @param css_class the css class
     */
    @Override
    public void addStyleClass(String css_class) {
        this.shape.getStyleClass().add(css_class);
    }
}
