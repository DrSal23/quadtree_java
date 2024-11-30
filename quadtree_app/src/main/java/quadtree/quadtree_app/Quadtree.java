package quadtree.quadtree_app;

import java.util.ArrayList;

public class Quadtree {
	private node root;
	
	public Quadtree() {
		this.root = new leafnode(-50.0, -50.0, 50.0, 50.0,1);
	}
	
	public void dump() {
		this.root.dump();
	}
	

    /**
     * Inserts a rectangle into the node. This method will be different for LeafNode and InternalNode.
     *
     * @param rectangle The rectangle to insert.
     */
    public boolean insert(rectangle rectangle) {
    	boolean flag = root.insert(rectangle);
    	
    	if(!flag) {
    		if(this.root instanceof leafnode && ((leafnode) this.root).getRectangles().size() == 5) {
        		// ensures this only get called when the node is at the limit 5
    			// else a double insert happened
    			ArrayList<rectangle> rectangles = ((leafnode)this.root).getRectangles();
        		this.root = new internalnode(-50.0, -50.0, 50.0, 50.0,1);
        		this.root.insert(rectangle);
        		
        		for(rectangle rect : rectangles) {
        			this.root.insert(rect);
        		}	
    		}
    		else {
    			return false;
    		}
    	}
    	
    	return true;
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
    public boolean delete(double x, double y) {
    	return this.root.delete(x,y);
    }

    /**
     * Prints out the contents of the node, including its children nodes and rectangles.
     * This method will be different for LeafNode and InternalNode.
     */
    
    public boolean update(rectangle rect) {
    	return this.root.update(rect);
    }
}
