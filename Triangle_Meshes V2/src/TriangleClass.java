import java.util.Arrays;

public class TriangleClass implements Triangle {

    private Point a;
    private Point b;
    private Point c;

    public TriangleClass(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
	public Point getPointA() {
    	return a;
    }

    @Override
	public Point getPointB() {
    	return b;
    }
    
    @Override
	public Point getPointC() {
    	return c;
    }

    @Override
	public boolean contains(Point point) {
        double pab = point.sub(a).cross(b.sub(a));
        double pbc = point.sub(b).cross(c.sub(b));

        if (!hasSameSign(pab, pbc)) {
            return false;
        }

        double pca = point.sub(c).cross(a.sub(c));

        if (!hasSameSign(pab, pca)) {
            return false;
        }

        return true;
    }

    @Override
	public boolean isPointInCircumcircle(Point point) {
        double a11 = a.getX() - point.getX();
        double a21 = b.getX() - point.getX();
        double a31 = c.getX() - point.getX();

        double a12 = a.getY() - point.getY();
        double a22 = b.getY() - point.getY();
        double a32 = c.getY() - point.getY();

        double a13 = (a.getX() - point.getX()) * (a.getX() - point.getX()) + (a.getY() - point.getY()) * (a.getY() - point.getY());
        double a23 = (b.getX() - point.getX()) * (b.getX() - point.getX()) + (b.getY() - point.getY()) * (b.getY() - point.getY());
        double a33 = (c.getX() - point.getX()) * (c.getX() - point.getX()) + (c.getY() - point.getY()) * (c.getY() - point.getY());

        double det = a11 * a22 * a33 + a12 * a23 * a31 + a13 * a21 * a32 - a13 * a22 * a31 - a12 * a21 * a33
                - a11 * a23 * a32;

        if (isOrientedCCW()) {
            return det > 0.0d;
        }

        return det < 0.0d;
    }

    @Override
	public boolean isOrientedCCW() {
        double a11 = a.getX() - c.getX();
        double a21 = b.getX() - c.getX();

        double a12 = a.getY() - c.getY();
        double a22 = b.getY() - c.getY();

        double det = a11 * a22 - a12 * a21;

        return det > 0.0d;
    }

    @Override
	public boolean isNeighbour(Segment edge) {
        return (a == edge.getA() || b == edge.getA() || c == edge.getA()) && (a == edge.getB() || b == edge.getB() || c == edge.getB());
    }

    @Override
	public Point getNoneEdgeVertex(Segment edge) {
        if (a != edge.getA() && a != edge.getB()) {
            return a;
        } else if (b != edge.getA() && b != edge.getB()) {
            return b;
        } else if (c != edge.getA() && c != edge.getB()) {
            return c;
        }

        return null;
    }

    @Override
	public boolean hasVertex(Point vertex) {
        if (a == vertex || b == vertex || c == vertex) {
            return true;
        }

        return false;
    }

    @Override
	public SegmentDistance findNearestEdge(Point point) {
        SegmentDistance[] edges = new SegmentDistance[3];

        edges[0] = new SegmentDistance(new Segment(a, b),
                computeClosestPoint(new Segment(a, b), point).sub(point).mag());
        edges[1] = new SegmentDistance(new Segment(b, c),
                computeClosestPoint(new Segment(b, c), point).sub(point).mag());
        edges[2] = new SegmentDistance(new Segment(c, a),
                computeClosestPoint(new Segment(c, a), point).sub(point).mag());

        Arrays.sort(edges);
        return edges[0];
    }

    private Point computeClosestPoint(Segment edge, Point point) {
        Point ab = edge.getB().sub(edge.getA());
        double t = point.sub(edge.getA()).dot(ab) / ab.dot(ab);

        if (t < 0.0d) {
            t = 0.0d;
        } else if (t > 1.0d) {
            t = 1.0d;
        }

        return edge.getA().add(ab.mult(t));
    }

    private boolean hasSameSign(double a, double b) {
        return Math.signum(a) == Math.signum(b);
    }

    @Override
    public String toString() {
        return "Triangle[" + a + " / " + b + " / " + c + "]";
    }

}