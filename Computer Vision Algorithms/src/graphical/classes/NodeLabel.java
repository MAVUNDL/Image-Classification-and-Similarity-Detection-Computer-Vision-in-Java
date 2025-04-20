package graphical.classes;

import graphical.interfaces.LabelGUI;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.text.Text;

public class NodeLabel extends Text implements LabelGUI {
    /*
        class variables
     */
    private final DoubleProperty width; // mutable to track the width
    private final DoubleProperty height; // mutable to track the height
    private final NodeShape labelShape;

    /**
     * This constructor creates a new label with the given text
     * @param text text for the label
     */
    public NodeLabel(String text){
        this(0,0, text); // just create a new label with text and let javafx update dimensions dynamically
    }

    /**
     * This constructor creates a Label with the specified dimensions as tracks its changes and updates it
     * @param width the width of the label
     * @param height the height of the label
     * @param text the text of the label
     */
    public NodeLabel(double width, double height, String text){
        super(width, height, text);
        this.labelShape = new NodeShape(this);
        this.width = new SimpleDoubleProperty();
        this.height = new SimpleDoubleProperty();

        // event listener to recalculate the layout bounds of the label
        layoutBoundsProperty().addListener(((_, _, newValue) ->{
            if(newValue != null){
                // check if the current width and height are equal to the new dimensions, if not update them
                if(Double.compare(this.width.doubleValue(), newValue.getWidth()) != 0){
                    this.width.set(newValue.getWidth());
                }
                if(Double.compare(this.height.doubleValue(), newValue.getHeight()) != 0){
                    this.height.set(newValue.getHeight());
                }
            }
        } ));
    }

    /**
     * @return This method returns a read only property for the width of the label
     */
    @Override
    public ReadOnlyDoubleProperty widthProperty() {
        return width;
    }

    /**
     * @return This method returns a read only property for the height of the label
     */
    @Override
    public ReadOnlyDoubleProperty heightProperty() {
        return height;
    }

    /**
     * This method sets the text to be displayed on the label
     *
     * @param text the text for the label
     */
    @Override
    public void setTextForLabel(String text) {
        // check if the current text is different to the new one, if yes update it
        if(getText().compareTo(text) != 0){
            setText(text);
        }
    }

    /**
     * This method sets the css style to the underlying node
     *
     * @param css the ccs style
     */
    @Override
    public void setInlineStyle(String css) {
        labelShape.setInlineStyle(css);
    }

    /**
     * This method sets the css class for the underlying node
     *
     * @param css_class the css class
     */
    @Override
    public void setStyleClass(String css_class) {
        this.labelShape.setStyleClass(css_class);
    }

    /**
     * This method adds the css class for the underlying node
     *
     * @param css_class the css class
     */
    @Override
    public void addStyleClass(String css_class) {
        this.labelShape.addStyleClass(css_class);
    }
}
