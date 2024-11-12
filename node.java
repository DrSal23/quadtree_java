package quadtree.quadtree_app;

/**
 * The Node class is an abstract class that represents a region in the quadtree.
 * Each node has an area defined by minimum and maximum x and y coordinates.
 * The node can be either a LeafNode or an InternalNode.
 */
public abstract class node {
    
    // The coordinates that define the boundaries of this node.
    protected double xMin;  // Minimum x-coordinate
    protected double yMin;  // Minimum y-coordinate
    protected double xMax;  // Maximum x-coordinate
    protected double yMax;  // Maximum y-coordinate
    
    protected int level;  // The depth level of the node in the quadtree (root is level 0)

    /**
     * Constructor for the Node class that sets the boundaries of the node.
     *
     * @param xMin  The minimum x-coordinate of the node's area.
     * @param yMin  The minimum y-coordinate of the node's area.
     * @param xMax  The maximum x-coordinate of the node's area.
     * @param yMax  The maximum y-coordinate of the node's area.
     * @param level The depth level of the node in the quadtree.
     */
    public node(double xMin, double yMin, double xMax, double yMax, int level) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
        this.level = level;
    }

    /**
     * Inserts a rectangle into the node. This method will be different for LeafNode and InternalNode.
     *
     * @param rectangle The rectangle to insert.
     */
    public abstract void insert(rectangle rectangle);

    /**
     * Finds a rectangle in the node by its x and y coordinates.
     *
     * @param x The x-coordinate to search for.
     * @param y The y-coordinate to search for.
     * @return The found rectangle, or null if not found.
     */
    public abstract rectangle find(double x, double y);

    /**
     * Deletes a rectangle from the node by its x and y coordinates.
     *
     * @param x The x-coordinate of the rectangle to delete.
     * @param y The y-coordinate of the rectangle to delete.
     */
    public abstract void delete(double x, double y);

    /**
     * Prints out the contents of the node, including its children nodes and rectangles.
     * This method will be different for LeafNode and InternalNode.
     */
    public abstract void dump();
}
