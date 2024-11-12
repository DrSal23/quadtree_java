package quadtree.quadtree_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * Unit test for the quadtree implementation.
 */
public class AppTest {

    /**
     * Simple Test to check true assertion
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    /**
     * Test to create a rectangle and check its attributes.
     */
    @Test
    public void createRect() {
        rectangle r = new rectangle(5, 5, 10, 10);
        assertEquals(5.0, r.getX(), 0.01);
        assertEquals(5.0, r.getY(), 0.01);
        assertEquals(10.0, r.getLength(), 0.01);
        assertEquals(10.0, r.getWidth(), 0.01);
    }

    /**
     * Test the string representation of a rectangle.
     */
    @Test
    public void print() {
        rectangle r = new rectangle(5, 5, 10, 10);
        assertEquals("Rectangle at (5.0, 5.0): 10.0 x 10.0", r.toString());
    }

    /**
     * Test the string representation of a node (abstract class).
     * This should not be directly used, but for understanding purposes we assume a concrete class for testing.
     */
    @Test
    public void checkNodeToString() {
        // You should be using leafnode or internalnode, not node (which is abstract)
        node n = new leafnode(1.0, 2.0, 3.0, 4.0, 1); // Using leafnode here
        String nodeString = "Node at (1.0, 2.0), (3.0, 4.0), level 1";
        assertEquals(nodeString, n.toString());
    }

    /**
     * Test the constructor of a leaf node.
     */
    @Test
    public void testLeafNodeConstructor() {
        leafnode leaf = new leafnode(0.0, 0.0, 10.0, 10.0, 1);
        
        assertEquals(0.0, leaf.getXMin(), 0.01);
        assertEquals(0.0, leaf.getYMin(), 0.01);
        assertEquals(10.0, leaf.getXMax(), 0.01);
        assertEquals(10.0, leaf.getYMax(), 0.01);
        assertEquals(1, leaf.getLevel());
        assertNotNull(leaf.getRectangles());
        assertTrue(leaf.getRectangles().isEmpty());
    }

    /**
     * Test inserting a rectangle into a leaf node.
     */
    @Test
    public void insertRectangleIntoLeafNode() {
        leafnode leaf = new leafnode(0.0, 0.0, 10.0, 10.0, 1);
        rectangle rect = new rectangle(5, 5, 2, 2);
        
        leaf.insert(rect);
        
        assertEquals(1, leaf.getRectangles().size());
        assertTrue(leaf.getRectangles().contains(rect));
    }

    /**
     * Test insert and split behavior when 5 rectangles are added to the leaf node.
     */
    @Test
    public void testInsertAndSplitLeafNode() {
        leafnode leaf = new leafnode(0.0, 0.0, 10.0, 10.0, 1);
        
        // Insert 5 rectangles (should not trigger split yet)
        for (int i = 0; i < 5; i++) {
            leaf.insert(new rectangle(i, i, 1, 1));
        }

        // Now insert the 6th rectangle (should trigger split)
        rectangle extraRect = new rectangle(5, 5, 2, 2);
        leaf.insert(extraRect);

        // As per your implementation, when splitting occurs, a print statement is called
        // So here we simply assert that the leaf's rectangle size is more than 5 after insertion
        assertEquals(6, ((Object) leaf.getRectangles()).size());
    }

    /**
     * Test deleting a rectangle from the leaf node.
     */
    @Test
    public void testDeleteRectangleFromLeafNode() {
        leafnode leaf = new leafnode(0.0, 0.0, 10.0, 10.0, 1);
        rectangle rect = new rectangle(5, 5, 2, 2);
        
        leaf.insert(rect);
        assertTrue(leaf.getRectangles().contains(rect));
        
        leaf.delete(5, 5);
        assertFalse(leaf.getRectangles().contains(rect));
    }

    /**
     * Test finding a rectangle in the leaf node.
     */
    @Test
    public void testFindRectangleInLeafNode() {
        leafnode leaf = new leafnode(0.0, 0.0, 10.0, 10.0, 1);
        rectangle rect = new rectangle(5, 5, 2, 2);
        
        leaf.insert(rect);
        
        assertEquals(rect, leaf.find(5, 5));
        assertNull(leaf.find(6, 6));  // Should return null as no rectangle exists at (6, 6)
    }

    /**
     * Test inserting a rectangle into an internal node.
     */
    @Test
    public void testInsertRectangleIntoInternalNode() {
        internalnode internal = new internalnode(0.0, 0.0, 10.0, 10.0, 0);
        rectangle rect = new rectangle(2, 2, 2, 2);

        internal.insert(rect);
        assertEquals(rect, internal.find(2, 2));
    }

    /**
     * Test deleting from an internal node.
     */
    @Test
    public void testDeleteFromInternalNode() {
        internalnode internal = new internalnode(0.0, 0.0, 10.0, 10.0, 0);
        rectangle rect = new rectangle(2, 2, 2, 2);

        internal.insert(rect);
        internal.delete(2, 2);
        assertNull(internal.find(2, 2));
    }

    /**
     * Test dumping the content of a leaf node.
     */
    @Test
    public void testDumpLeafNode() {
        leafnode leaf = new leafnode(0.0, 0.0, 10.0, 10.0, 1);
        rectangle rect1 = new rectangle(1, 1, 2, 2);
        rectangle rect2 = new rectangle(2, 2, 3, 3);
        
        leaf.insert(rect1);
        leaf.insert(rect2);

        // Check dump output
        leaf.dump();  // This should print out the rectangles in this node with indentation
    }

    /**
     * Test dumping the content of an internal node.
     */
    @Test
    public void testDumpInternalNode() {
        internalnode internal = new internalnode(0.0, 0.0, 10.0, 10.0, 0);
        rectangle rect1 = new rectangle(1, 1, 2, 2);
        rectangle rect2 = new rectangle(2, 2, 3, 3);
        
        internal.insert(rect1);
        internal.insert(rect2);

        internal.dump();  // This should print out the internal node and its children
    }
}
