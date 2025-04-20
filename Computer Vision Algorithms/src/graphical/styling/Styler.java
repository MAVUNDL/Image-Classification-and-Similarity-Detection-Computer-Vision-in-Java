package graphical.styling;

public interface Styler {
    /**
     * This method sets the css style to the underlying node
     * @param css the ccs style
     */
    void setInlineStyle(String css);

    /**
     * This method replaces the css class for the underlying node
     * @param css_class the css class
     */
    void setStyleClass(String css_class);

    /**
     * This method adds the css class for the underlying node
     * @param css_class the css class
     */
    void addStyleClass(String css_class);

}
