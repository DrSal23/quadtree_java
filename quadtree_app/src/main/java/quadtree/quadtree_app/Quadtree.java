package quadtree.quadtree_app;

public class Quadtree {
	private node root;
	
	public Quadtree() {
		this.root = new leafnode(10.0, 10.0, 100.0, 100.0,1);
	}
	
	public void dump() {
		this.root.dump();
	}
	
}
