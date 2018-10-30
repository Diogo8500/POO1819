import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mesh {

    private List<Triangle> mesh;

    public Mesh() {
        this.mesh = new ArrayList<Triangle>();
    }

    /**
     * Adds a triangle to this mesh.
     * 
     * @param _triangle
     *            The triangle to be added to this mesh
     */
    public void add(Triangle _triangle) {
        this.mesh.add(_triangle);
    }

    /**
     * Removes a triangle from this mesh.
     * 
     * @param _triangle
     *            The triangle to be removed from this mesh
     */
    public void remove(Triangle _triangle) {
        this.mesh.remove(_triangle);
    }

    /**
     * Returns the triangles from this mesh.
     * 
     * @return The triangles from this mesh
     */
    public List<Triangle> getTriangles() {
        return this.mesh;
    }

    /**
     * Returns the triangle from this mesh that contains the specified
     * point or null if no triangle from the mesh contains the point.
     * 
     * @param _point
     *            The point
     * @return Returns the triangle from this mesh that contains the
     *         specified point or null
     */
    public Triangle findContainingTriangle(Point _point) {
        for (Triangle triangle : mesh) {
            if (triangle.contains(_point)) {
                return triangle;
            }
        }
        return null;
    }

    /**
     * Returns the neighbor triangle of the specified triangle sharing the same
     * edge as specified. If no neighbor sharing the same edge exists null is
     * returned.
     * 
     * @param _triangle
     *            The triangle
     * @param _edge
     *            The edge
     * @return The triangles neighbor triangle sharing the same edge or null if
     *         no triangle exists
     */
    public Triangle findNeighbour(Triangle _triangle, Segment _edge) {
        for (Triangle triangleFromSoup : mesh) {
            if (triangleFromSoup.isNeighbour(_edge) && triangleFromSoup != _triangle) {
                return triangleFromSoup;
            }
        }
        return null;
    }

    /**
     * Returns one of the possible triangles sharing the specified edge. Based
     * on the ordering of the triangles in this mesh the returned
     * triangle may differ. To find the other triangle that shares this edge use
     * the {@link findNeighbour(Triangle2D triangle, Edge2D edge)} method.
     * 
     * @param _edge
     *            The edge
     * @return Returns one triangle that shares the specified edge
     */
    public Triangle findOneTriangleSharing(Segment _edge) {
        for (Triangle triangle : mesh) {
            if (triangle.isNeighbour(_edge)) {
                return triangle;
            }
        }
        return null;
    }

    /**
     * Returns the edge from the mesh nearest to the specified point.
     * 
     * @param _point
     *            The point
     * @return The edge from the mesh nearest to the specified point
     */
    public Segment findNearestEdge(Point _point) {
        List<SegmentDistance> edgeList = new ArrayList<SegmentDistance>();

        for (Triangle triangle : mesh) {
            edgeList.add(triangle.findNearestEdge(_point));
        }

        SegmentDistance[] edgeDistancePacks = new SegmentDistance[edgeList.size()];
        edgeList.toArray(edgeDistancePacks);

        Arrays.sort(edgeDistancePacks);
        return edgeDistancePacks[0].getEdge();
    }

    /**
     * Removes all triangles from this mesh that contain the specified
     * vertex.
     * 
     * @param _vertex
     *            The vertex
     */
    public void removeTrianglesUsing(Point _vertex) {
        List<Triangle> trianglesToBeRemoved = new ArrayList<Triangle>();

        for (Triangle triangle : mesh) {
            if (triangle.hasVertex(_vertex)) {
                trianglesToBeRemoved.add(triangle);
            }
        }

        mesh.removeAll(trianglesToBeRemoved);
    }

}