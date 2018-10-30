import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DelaunayTriangulator {

    private List<Point> pointSet;
    private Mesh triangleSoup;

    /**
     * Constructor of the SimpleDelaunayTriangulator class used to create a new
     * triangulator instance.
     * 
     * @param pointSet
     *            The point set to be triangulated
     * @throws NotEnoughPointsException
     *             Thrown when the point set contains less than three points
     */
    public DelaunayTriangulator(List<Point> pointSet) {
        this.pointSet = pointSet;
        this.triangleSoup = new Mesh();
    }

    /**
     * This method generates a Delaunay triangulation from the specified point
     * set.
     * 
     * @throws NotEnoughPointsException
     */
    public void triangulate() throws NotEnoughPointsException {
        triangleSoup = new Mesh();

        if (pointSet == null || pointSet.size() < 3) {
            throw new NotEnoughPointsException("Less than three points in point set.");
        }

        /**
         * In order for the in circumcircle test to not consider the vertices of
         * the super triangle we have to start out with a big triangle
         * containing the whole point set. We have to scale the super triangle
         * to be very large. Otherwise the triangulation is not convex.
         */
        double maxOfAnyCoordinate = 0.0d;

        for (Point vector : getPointSet()) {
            maxOfAnyCoordinate = Math.max(Math.max(vector.getX(), vector.getY()), maxOfAnyCoordinate);
        }

        maxOfAnyCoordinate *= 16.0d;

        Point p1 = new PointClass(0.0d, 3.0d * maxOfAnyCoordinate);
        Point p2 = new PointClass(3.0d * maxOfAnyCoordinate, 0.0d);
        Point p3 = new PointClass(-3.0d * maxOfAnyCoordinate, -3.0d * maxOfAnyCoordinate);

        Triangle superTriangle = new TriangleClass(p1, p2, p3);

        triangleSoup.add(superTriangle);

        for (int i = 0; i < pointSet.size(); i++) {
            Triangle triangle = triangleSoup.findContainingTriangle(pointSet.get(i));

            if (triangle == null) {
                /**
                 * If no containing triangle exists, then the vertex is not
                 * inside a triangle (this can also happen due to numerical
                 * errors) and lies on an edge. In order to find this edge we
                 * search all edges of the triangle soup and select the one
                 * which is nearest to the point we try to add. This edge is
                 * removed and four new edges are added.
                 */
                Segment edge = triangleSoup.findNearestEdge(pointSet.get(i));

                Triangle first = triangleSoup.findOneTriangleSharing(edge);
                Triangle second = triangleSoup.findNeighbour(first, edge);

                Point firstNoneEdgeVertex = first.getNoneEdgeVertex(edge);
                Point secondNoneEdgeVertex = second.getNoneEdgeVertex(edge);

                triangleSoup.remove(first);
                triangleSoup.remove(second);

                Triangle triangle1 = new TriangleClass(edge.getA(), firstNoneEdgeVertex, pointSet.get(i));
                Triangle triangle2 = new TriangleClass(edge.getB(), firstNoneEdgeVertex, pointSet.get(i));
                Triangle triangle3 = new TriangleClass(edge.getA(), secondNoneEdgeVertex, pointSet.get(i));
                Triangle triangle4 = new TriangleClass(edge.getB(), secondNoneEdgeVertex, pointSet.get(i));

                triangleSoup.add(triangle1);
                triangleSoup.add(triangle2);
                triangleSoup.add(triangle3);
                triangleSoup.add(triangle4);

                legalizeEdge(triangle1, new Segment(edge.getA(), firstNoneEdgeVertex), pointSet.get(i));
                legalizeEdge(triangle2, new Segment(edge.getB(), firstNoneEdgeVertex), pointSet.get(i));
                legalizeEdge(triangle3, new Segment(edge.getA(), secondNoneEdgeVertex), pointSet.get(i));
                legalizeEdge(triangle4, new Segment(edge.getB(), secondNoneEdgeVertex), pointSet.get(i));
            } else {
                /**
                 * The vertex is inside a triangle.
                 */
                Point a = triangle.getPointA();
                Point b = triangle.getPointB();
                Point c = triangle.getPointC();

                triangleSoup.remove(triangle);

                Triangle first = new TriangleClass(a, b, pointSet.get(i));
                Triangle second = new TriangleClass(b, c, pointSet.get(i));
                Triangle third = new TriangleClass(c, a, pointSet.get(i));

                triangleSoup.add(first);
                triangleSoup.add(second);
                triangleSoup.add(third);

                legalizeEdge(first, new Segment(a, b), pointSet.get(i));
                legalizeEdge(second, new Segment(b, c), pointSet.get(i));
                legalizeEdge(third, new Segment(c, a), pointSet.get(i));
            }
        }

        /**
         * Remove all triangles that contain vertices of the super triangle.
         */
        triangleSoup.removeTrianglesUsing(superTriangle.getPointA());
        triangleSoup.removeTrianglesUsing(superTriangle.getPointB());
        triangleSoup.removeTrianglesUsing(superTriangle.getPointC());
    }

    /**
     * This method legalizes edges by recursively flipping all illegal edges.
     * 
     * @param triangle
     *            The triangle
     * @param edge
     *            The edge to be legalized
     * @param newVertex
     *            The new vertex
     */
    private void legalizeEdge(Triangle triangle, Segment edge, Point newVertex) {
        Triangle neighbourTriangle = triangleSoup.findNeighbour(triangle, edge);

        /**
         * If the triangle has a neighbor, then legalize the edge
         */
        if (neighbourTriangle != null) {
            if (neighbourTriangle.isPointInCircumcircle(newVertex)) {
                triangleSoup.remove(triangle);
                triangleSoup.remove(neighbourTriangle);

                Point noneEdgeVertex = neighbourTriangle.getNoneEdgeVertex(edge);

                Triangle firstTriangle = new TriangleClass(noneEdgeVertex, edge.getA(), newVertex);
                Triangle secondTriangle = new TriangleClass(noneEdgeVertex, edge.getB(), newVertex);

                triangleSoup.add(firstTriangle);
                triangleSoup.add(secondTriangle);

                legalizeEdge(firstTriangle, new Segment(noneEdgeVertex, edge.getA()), newVertex);
                legalizeEdge(secondTriangle, new Segment(noneEdgeVertex, edge.getB()), newVertex);
            }
        }
    }

    /**
     * Creates a random permutation of the specified point set. Based on the
     * implementation of the Delaunay algorithm this can speed up the
     * computation.
     */
    public void shuffle() {
        Collections.shuffle(pointSet);
    }

    /**
     * Shuffles the point set using a custom permutation sequence.
     * 
     * @param permutation
     *            The permutation used to shuffle the point set
     */
    public void shuffle(int[] permutation) {
        List<Point> temp = new ArrayList<Point>();
        for (int i = 0; i < permutation.length; i++) {
            temp.add(pointSet.get(permutation[i]));
        }
        pointSet = temp;
    }

    /**
     * Returns the point set in form of a vector of 2D vectors.
     * 
     * @return Returns the points set.
     */
    public List<Point> getPointSet() {
        return pointSet;
    }

    /**
     * Returns the trianges of the triangulation in form of a vector of 2D
     * triangles.
     * 
     * @return Returns the triangles of the triangulation.
     */
    public List<Triangle> getTriangles() {
        return triangleSoup.getTriangles();
    }

}