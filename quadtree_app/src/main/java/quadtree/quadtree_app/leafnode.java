package quadtree.quadtree_app;

import java.util.ArrayList;

/**
 * LeafNode represents a leaf in the quadtree that stores rectangles.
 * A leaf node can hold up to 5 rectangles before it needs to split.
 * When a split is triggered, this node will convert into an InternalNode.
 */
public class leafnode extends node {
    // List to store rectangles in this node
    private ArrayList<rectangle> rectangles;

    /**
     * Constructor to create a new LeafNode with a defined area and level in the quadtree.
     *
     * @param xMin  The minimum x-coordinate of the node's area.
     * @param yMin  The minimum y-coordinate of the node's area.
     * @param xMax  The maximum x-coordinate of the node's area.
     * @param yMax  The maximum y-coordinate of the node's area.
     * @param level The depth level of this node in the quadtree.
     */
    public leafnode(double xMin, double yMin, double xMax, double yMax, int level) {
        super(xMin, yMin, xMax, yMax, level); // Call the superclass (Node) constructor
        this.rectangles = new ArrayList<>(); // Initialize the list of rectangles
    }

    /**
     * Inserts a rectangle into the node.
     * If there are already 5 rectangles, this node will need to split.
     *
     * @param rectangle The rectangle to insert.
     */
    public void insert(rectangle rectangle) {
        if (rectangles.size() < 5) {
            rectangles.add(rectangle); // Add the rectangle if there's space
        } else {
            // Node needs to split into an InternalNode (we'll define this later)
            System.out.println("Splitting node at level " + level);
        }
    }

    /**
     * Finds a rectangle located at the given x and y coordinates.
     *
     * @param x The x-coordinate to look for.
     * @param y The y-coordinate to look for.
     * @return The rectangle if found, or null if not found.
     */
    public rectangle find(double x, double y) {
        for (rectangle rect : rectangles) {
            if (rect.getX() == x && rect.getY() == y) { // Check if any rectangle matches
                return rect; // Return the found rectangle
            }
        }
        System.out.println("Nothing is at (" + x + ", " + y + ")."); // Print message if not found
        return null; // Return null if no rectangle was found
    }

    /**
     * Deletes a rectangle at the given x and y coordinates if it exists.
     *
     * @param x The x-coordinate of the rectangle to delete.
     * @param y The y-coordinate of the rectangle to delete.
     */
    public void delete(double x, double y) {
        rectangle toDelete = find(x, y); // Try to find the rectangle
        if (toDelete != null) {
            rectangles.remove(toDelete); // Remove if found
            System.out.println("Deleted rectangle at (" + x + ", " + y + ").");
        } else {
            System.out.println("Nothing to delete at (" + x + ", " + y + ")."); // Print if not found
        }
    }

    /**
     * Prints out all rectangles in this node.
     * Each level in the tree is indented to show hierarchy.
     */
    public void dump() {
    	StringBuilder LeafNodeTabs = new StringBuilder();
    	for(int i = 0; i<this.level; i++) {
    		LeafNodeTabs.append("\t");
    	}
    	System.out.println(LeafNodeTabs.toString() + "LeafNode at ...");
        for (rectangle rect : rectangles) {
            // Indent based on level to show structure in the tree
        	StringBuilder sb = new StringBuilder();
        	for(int i = 0; i<this.level; i++) {
        		sb.append("\t");
        	}
            System.out.println(sb.toString() + rect.toString());
        }
    }


	@Override
	public boolean update(rectangle rect) {
		for(int i=0; i<rectangles.size(); i++) {
			rectangle tmp = rectangles.get(i);
			if(tmp.getX() == rect.getX() && tmp.getY() == rect.getY()) {
				tmp = rect;
				return true;
			}
		}
		return false;
	}

	public  ArrayList<rectangle> getRectangles() {
		// TODO Auto-generated method stub
		return this.rectangles;
	}
}
