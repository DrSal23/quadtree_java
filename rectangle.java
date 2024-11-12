package quadtree.quadtree_app;

/**
 * The Rectangle class represents a rectangle with a given position and size.
 */
public class rectangle {

    private double x;  // x-coordinate of the rectangle's position
    private double y;  // y-coordinate of the rectangle's position
    private double width;  // Width of the rectangle
    private double length;  // Height of the rectangle

    /**
     * Constructor to initialize the rectangle.
     *
     * @param x The x-coordinate of the rectangle.
     * @param y The y-coordinate of the rectangle.
     * @param width The width of the rectangle.
     * @param length The height of the rectangle.
     */
    public rectangle(double x, double y, double width, double length) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.length = length;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    /**
     * Returns a string representation of the rectangle.
     *
     * @return A formatted string with the rectangle's details.
     */
    @Override
    public String toString() {
        return String.format("Rectangle at (%.2f, %.2f): %.2f×%.2f", x, y, width, length);
    }
}
