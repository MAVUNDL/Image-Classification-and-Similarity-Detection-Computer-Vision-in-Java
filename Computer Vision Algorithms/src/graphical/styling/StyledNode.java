package graphical.styling;

import graphical.classes.NodeLabel;

/**
 * This interface will be used to attach a styled Label to a Node
 */
public interface StyledNode {
    /**
     * This method will bind a label to node
     * @param label the styled label
     */
    void attachStyledLabelToNode(NodeLabel label);

    /**
     * @return returns the label attached to a node
     */
    NodeLabel getAttachedLabelToNode();

}
