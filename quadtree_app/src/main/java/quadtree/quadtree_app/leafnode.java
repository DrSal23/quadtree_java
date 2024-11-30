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
        this.rectangles = new ArrayList<rectangle>(); // Initialize the list of rectangles
    }
    

    /**
     * Inserts a rectangle into the node.
     * If there are already 5 rectangles, this node will need to split.
     *
     * @param rectangle The rectangle to insert.
     */
    public boolean insert(rectangle rectangle) {
        for (rectangle rect : rectangles) {
            if (rect.getX() == rectangle.getX() && rect.getY() == rectangle.getY()) {
                System.out.println("You can not double insert at a position.");
                return false; // Prevent duplicate insertion
            }
        }
        if (rectangles.size() < 5) {
            rectangles.add(rectangle); // Add the rectangle if there's space
            return true;
        } else {
            return false; // Node needs to split
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
        System.out.println(String.format("Nothing is at (%.2f, %.2f).", x, y)); // Print message if not found
        return null; // Return null if no rectangle was found
    }

    /**
     * Deletes a rectangle at the given x and y coordinates if it exists.
     *
     * @param x The x-coordinate of the rectangle to delete.
     * @param y The y-coordinate of the rectangle to delete.
     */
    public boolean delete(double x, double y) {   
        boolean found = false;
        for (int i = 0; i < rectangles.size(); i++) {
            rectangle rect = rectangles.get(i);
            if (rect.getX() == x && rect.getY() == y) {
                rectangles.remove(i);
                return true;
            }
        }
        if (!found) {
            System.out.println(String.format("Nothing to delete at (%.2f, %.2f).", x, y));
            return false;
        }
        return true;
    }


    /**
     * Prints out all rectangles in this node.
     * Each level in the tree is indented to show hierarchy.
     */
    public void dump() {
    	StringBuilder LeafNodeTabs = new StringBuilder();
    	for(int i = 0; i<this.level - 1; i++) {
    		LeafNodeTabs.append("\t");
    	}
    	System.out.println(LeafNodeTabs.toString() + String.format("Leaf Node - Rectangle at (%.2f, %.2f): %.2fx%.2f", xMin, yMin, xMax - xMin, yMax - yMin) );
        LeafNodeTabs.append("\t");
    	for (rectangle rect : this.rectangles) {
            System.out.println(LeafNodeTabs.toString() + rect.toString());
        }
    }


	@Override
	public boolean update(rectangle rect) {
	    for (int i = 0; i < rectangles.size(); i++) {
	        rectangle tmp = rectangles.get(i);
	        if (tmp.getX() == rect.getX() && tmp.getY() == rect.getY()) {
	            rectangles.set(i, rect); // Replace the rectangle in the list
	            return true;
	        }
	    }
	    return false; // Return false if no matching rectangle was found
	}


	public  ArrayList<rectangle> getRectangles() {
		// TODO Auto-generated method stub
		return this.rectangles;
	}
}
