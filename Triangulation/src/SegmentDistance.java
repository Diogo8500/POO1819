public class SegmentDistance implements Comparable<SegmentDistance> {

    public Segment edge;
    public double distance;

    public SegmentDistance(Segment edge, double distance) {
        this.edge = edge;
        this.distance = distance;
    }

    @Override
    public int compareTo(SegmentDistance o) {
        return Double.compare(this.distance, o.distance);
    }

}