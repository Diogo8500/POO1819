import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Client {
	
	private List<Point> cloud;
	private Stack<Point> hull;
	
	public Client(List<Point> _cloud) {
		cloud = new ArrayList<Point>();
		hull = new Stack<Point>();
		cloud = _cloud;
	}
	
	public void solution(){
		cleanCloud();
		calculateHull();
		ShapeSolver solver = new ShapeSolver(hull);
		Polygon shape = solver.getShape();
		
		System.out.println(shape.toString() + " " + (int)shape.getPerimetre());
	}
	
	private void calculateHull(){
		
		Collections.sort(cloud, (_a, _b) -> _a.getY() < _b.getY() ? -1 : _a.getY() == _b.getY() ? 0 : 1);
		Collections.sort(cloud, (_a, _b) -> cloud.get(0).getCoTan(_a) < cloud.get(0).getCoTan(_b) ? -1 : cloud.get(0).getCoTan(_a) == cloud.get(0).getCoTan(_b) ? 0 : 1);

		hull.push(cloud.get(0));
		hull.push(cloud.get(1));
		hull.push(cloud.get(2));
		
		for (int i=3; i<cloud.size(); i++) {
			while(ccw(hull.get(hull.size() - 2), hull.get(hull.size() - 1), cloud.get(i)) < 0) {
				hull.pop();
			}
			hull.push(cloud.get(i));
		}
	}
	
	private void cleanCloud() {
		Set<Point> newCloud = new TreeSet<Point>();
		newCloud.addAll(cloud);
		cloud.clear();
		cloud.addAll(newCloud);
	}
	
	private static double ccw(Point _a, Point _b, Point _c) {
		return (_b.getX() - _a.getX())*(_c.getY() - _a.getY()) - (_b.getY() - _a.getY())*(_c.getX() - _a.getX());
	}
	
}
