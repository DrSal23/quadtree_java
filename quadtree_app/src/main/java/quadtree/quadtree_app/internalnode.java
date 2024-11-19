package quadtree.quadtree_app;

/**
 * The InternalNode class represents a non-leaf node in the quadtree.
 * It divides its area into four quadrants (top-left, top-right, bottom-left, bottom-right),
 * and each quadrant is a node that stores rectangles.
 */
public class internalnode extends node {

    private node topLeft;
    private node topRight;
    private node bottomLeft;
    private node bottomRight;

    /**
     * Constructor for the InternalNode class.
     * It divides the node's area into four quadrants.
     *
     * @param xMin  The minimum x-coordinate of the node's area.
     * @param yMin  The minimum y-coordinate of the node's area.
     * @param xMax  The maximum x-coordinate of the node's area.
     * @param yMax  The maximum y-coordinate of the node's area.
     * @param level The depth level of the node.
     */
    public internalnode(double xMin, double yMin, double xMax, double yMax, int level) {
        super(xMin, yMin, xMax, yMax, level);  // Call the parent class constructor

        // Calculate the midpoint of the area
        double midX = (xMin + xMax) / 2;
        double midY = (yMin + yMax) / 2;

        // Initialize the four quadrants as LeafNodes
        topLeft = new leafnode(xMin, midY, midX, yMax, level + 1);
        topRight = new leafnode(midX, midY, xMax, yMax, level + 1);
        bottomLeft = new leafnode(xMin, yMin, midX, midY, level + 1);
        bottomRight = new leafnode(midX, yMin, xMax, midY, level + 1);
    }

    /**
     * Inserts a rectangle into the correct quadrant of the internal node.
     *
     * @param rectangle The rectangle to insert.
     */
    @Override
    public void insert(rectangle rectangle) {
        if (rectangle.getX() < (xMin + xMax) / 2) {
            if (rectangle.getY() < (yMin + yMax) / 2) {
                bottomLeft.insert(rectangle);
            } else {
                topLeft.insert(rectangle);
            }
        } else {
            if (rectangle.getY() < (yMin + yMax) / 2) {
                bottomRight.insert(rectangle);
            } else {
                topRight.insert(rectangle);
            }
        }
    }

    /**
     * Finds a rectangle at the given x and y coordinates.
     *
     * @param x The x-coordinate to search for.
     * @param y The y-coordinate to search for.
     * @return The rectangle found, or null if not found.
     */
    @Override
    public rectangle find(double x, double y) {
        if (x < (xMin + xMax) / 2) {
            if (y < (yMin + yMax) / 2) {
                return bottomLeft.find(x, y);
            } else {
                return topLeft.find(x, y);
            }
        } else {
            if (y < (yMin + yMax) / 2) {
                return bottomRight.find(x, y);
            } else {
                return topRight.find(x, y);
            }
        }
    }

    /**
     * Deletes a rectangle at the given x and y coordinates.
     *
     * @param x The x-coordinate of the rectangle to delete.
     * @param y The y-coordinate of the rectangle to delete.
     */
    @Override
    public void delete(double x, double y) {
        if (x < (xMin + xMax) / 2) {
            if (y < (yMin + yMax) / 2) {
                bottomLeft.delete(x, y);
            } else {
                topLeft.delete(x, y);
            }
        } else {
            if (y < (yMin + yMax) / 2) {
                bottomRight.delete(x, y);
            } else {
                topRight.delete(x, y);
            }
        }
    }

    /**
     * Dumps the contents of the internal node, including its child nodes.
     */
    @Override
    public void dump() {
        System.out.println("\t".repeat(level) + "Internal Node -");
        
        topLeft.dump();
        topRight.dump();
        bottomLeft.dump();
        bottomRight.dump();
    }

	@Override
	public boolean update(rectangle rect) {
		return topRight.update(rect) || topLeft.update(rect) || bottomRight.update(rect) || bottomLeft.update(rect);
	}
}
