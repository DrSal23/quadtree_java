package quadtree.quadtree_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * Unit test for the Quadtree implementation.
 */
public class AppTest {

    @Test
    public void testRectangleAttributes() {
        rectangle r = new rectangle(5, 5, 10, 10);
        assertEquals(5.00, r.getX());
        assertEquals(5.00, r.getY());
        assertEquals(10.00, r.getLength());
        assertEquals(10.00, r.getWidth());
    }

    @Test
    public void testRectangleToString() {
        rectangle r = new rectangle(5, 5, 10, 10);
        assertTrue("Rectangle at (5.00, 5.00): 10.00x10.00".equals(r.toString()));
    }

    @Test
    public void testLeafNodeBasicOperations() {
        leafnode leaf = new leafnode(0.0, 0.0, 10.0, 10.0, 1);
        rectangle r1 = new rectangle(5, 5, 2, 2);

        // Test insertion
        leaf.insert(r1);
        assertEquals(1, leaf.getRectangles().size());
        assertTrue(leaf.getRectangles().contains(r1));

        // Test finding
        assertEquals(r1, leaf.find(5, 5));
        assertNull(leaf.find(6, 6));  // Non-existing

        // Test deletion
        leaf.delete(5, 5);
        assertFalse(leaf.getRectangles().contains(r1));
        assertNull(leaf.find(5, 5));
    }

    @Test
    public void testLeafNodeSplitting() {
        leafnode leaf = new leafnode(0.0, 0.0, 10.0, 10.0, 1);

        // Insert 5 rectangles, no split yet
        for (int i = 0; i < 5; i++) {
            leaf.insert(new rectangle(i, i, 1, 1));
        }

        // Now trigger a split
        rectangle extraRect = new rectangle(6, 6, 1, 1);
        leaf.insert(extraRect);

        // Check node splitting behavior (requires complete split implementation)
        // This example assumes the split transitions to an `internalnode`.
        // Verify the reassignment logic post-split in your code.

        assertNotNull(leaf.find(6, 6));
    }

    @Test
    public void testInternalNodeInsertionAndFinding() {
        internalnode internal = new internalnode(0.0, 0.0, 10.0, 10.0, 0);
        rectangle rect1 = new rectangle(2, 2, 2, 2);
        rectangle rect2 = new rectangle(8, 8, 2, 2);

        // Insert rectangles into appropriate quadrants
        internal.insert(rect1);
        internal.insert(rect2);

        // Verify placement
        assertEquals(rect1, internal.find(2, 2));
        assertEquals(rect2, internal.find(8, 8));
    }

    @Test
    public void testInternalNodeDeletion() {
        internalnode internal = new internalnode(0.0, 0.0, 10.0, 10.0, 0);
        rectangle rect = new rectangle(2, 2, 2, 2);

        internal.insert(rect);
        internal.delete(2, 2);
        assertNull(internal.find(2, 2));
    }

    @Test
    public void testQuadtreeIntegration() {
        Quadtree quadtree = new Quadtree();
        rectangle rect1 = new rectangle(5, 5, 2, 2);
        rectangle rect2 = new rectangle(15, 15, 2, 2);
        rectangle rectOut = new rectangle(25, 25, 2, 2); // Out of bounds

        // Insert rectangles
        quadtree.insert(rect1);
        quadtree.insert(rect2);

        // Out of bounds insertion (expect exception or ignored behavior)
        try {
            quadtree.insert(rectOut);
        } catch (Exception e) {
            System.out.println("Out-of-bounds rectangle caught as expected.");
        }

        // Verify placement
        assertEquals(rect1, quadtree.find(5, 5));
        assertEquals(rect2, quadtree.find(15, 15));
        assertNull(quadtree.find(25, 25));

        // Delete a rectangle
        quadtree.delete(5, 5);
        assertNull(quadtree.find(5, 5));
    }

    @Test
    public void testDumpOutput() {
        leafnode leaf = new leafnode(0.0, 0.0, 10.0, 10.0, 1);
        rectangle r1 = new rectangle(2, 2, 2, 2);
        rectangle r2 = new rectangle(5, 5, 2, 2);

        leaf.insert(r1);
        leaf.insert(r2);

        // The dump output will be manually verified during execution
        System.out.println("Dumping Leaf Node:");
        leaf.dump();
    }
}
