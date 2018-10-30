public class PointClass implements Point {

    private double x;
    private double y;

    public PointClass(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /* (non-Javadoc)
	 * @see IVector2D#getX()
	 */
    @Override
	public double getX() {
    	return x;
    }
    
    /* (non-Javadoc)
	 * @see IVector2D#getY()
	 */
    @Override
	public double getY() {
    	return y;
    }

    /* (non-Javadoc)
	 * @see IVector2D#sub(Vector2D)
	 */
    @Override
	public Point sub(Point vector) {
        return new PointClass(this.x - vector.getX(), this.y - vector.getY());
    }

    /* (non-Javadoc)
	 * @see IVector2D#add(Vector2D)
	 */
    @Override
	public Point add(Point vector) {
        return new PointClass(this.x + vector.getX(), this.y + vector.getY());
    }

    /* (non-Javadoc)
	 * @see IVector2D#mult(double)
	 */
    @Override
	public Point mult(double scalar) {
        return new PointClass(this.x * scalar, this.y * scalar);
    }

    /* (non-Javadoc)
	 * @see IVector2D#mag()
	 */
    @Override
	public double mag() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /* (non-Javadoc)
	 * @see IVector2D#dot(Vector2D)
	 */
    @Override
	public double dot(Point vector) {
        return this.x * vector.getX() + this.y * vector.getY();
    }

    /* (non-Javadoc)
	 * @see IVector2D#cross(Vector2D)
	 */
    @Override
	public double cross(Point vector) {
        return this.y * vector.getX() - this.x * vector.getY();
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

}