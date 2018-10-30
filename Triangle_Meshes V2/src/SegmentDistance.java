public class SegmentDistance implements Comparable<SegmentDistance> {

    private Segment edge;
    private double distance;

    public SegmentDistance(Segment edge, double distance) {
        this.edge = edge;
        this.distance = distance;
    }
    
    public Segment getEdge() {
    	return edge;
    }

    @Override
    public int compareTo(SegmentDistance o) {
        return Double.compare(this.distance, o.distance);
    }

}