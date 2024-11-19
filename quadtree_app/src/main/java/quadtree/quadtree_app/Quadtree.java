package quadtree.quadtree_app;

public class Quadtree {
	private node root;
	
	public Quadtree() {
		this.root = new leafnode(10.0, 10.0, 100.0, 100.0,1);
	}
	
	public void dump() {
		this.root.dump();
	}
	

    /**
     * Inserts a rectangle into the node. This method will be different for LeafNode and InternalNode.
     *
     * @param rectangle The rectangle to insert.
     */
    public void insert(rectangle rectangle) {
    	this.root.insert(rectangle);
    }

    /**
     * Finds a rectangle in the node by its x and y coordinates.
     *
     * @param x The x-coordinate to search for.
     * @param y The y-coordinate to search for.
     * @return The found rectangle, or null if not found.
     */
    public rectangle find(double x, double y) {
    	return this.root.find(x,y);
    }

    /**
     * Deletes a rectangle from the node by its x and y coordinates.
     *
     * @param x The x-coordinate of the rectangle to delete.
     * @param y The y-coordinate of the rectangle to delete.
     */
    public void delete(double x, double y) {
    	this.root.delete(x,y);
    }

    /**
     * Prints out the contents of the node, including its children nodes and rectangles.
     * This method will be different for LeafNode and InternalNode.
     */
    
    public boolean update(rectangle rect) {
    	return this.root.update(rect);
    }
}
