package graphical.interfaces;

import DataStructures.interfaces.Edge;
import graphical.styling.StyledNode;
import graphical.styling.Styler;

public interface EdgeGUI<V, E> extends Styler, StyledNode{
    /**
     * @return returns the underlying Node object
     */
    Edge<E, E> getUnderlyingEdge();

    /**
     * @return returns the Label node for stying
     */
    StyledNode getStylableNode();

}
