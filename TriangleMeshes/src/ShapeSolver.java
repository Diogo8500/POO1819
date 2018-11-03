import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ShapeSolver {
	
	private List<Point> cloud;
	private Stack<Point> hull;
	
	public ShapeSolver(List<Point> _cloud) {
		cloud = new ArrayList<Point>();
		hull = new Stack<Point>();
		cloud = _cloud;
	}
	
	public void solution(){
		calculateHull();
		Poligon solution;
		if(hull.size() == 3) {
			Point a = hull.get(0);
			Point b = hull.get(1);
			Point c = hull.get(2);
			try {
				solution = new EquilateralTriangleClass(a, b, c);
			}catch(IllegalArgumentException _e) {
				solution = new TriangleClass(a, b, c);
			}
		}else {
			Point a = hull.get(0);
			Point b = hull.get(1);
			Point c = hull.get(2);
			Point d = hull.get(3);
			try {
				solution = new SquareClass(a, b, c, d);
			}catch(IllegalArgumentException _e) {
				try {
					solution = new RectangleClass(a, b, c, d);
				}catch(IllegalArgumentException _f) {
					try {
						solution = new ParallelogramClass(a, b, c, d);
					}catch(IllegalArgumentException _g) {
						solution = new QuadrilateralClass(a, b, c, d);
					}
				}
			}
		}
		System.out.println(solution.toString() + " " + (int)solution.getPerimetre());
		//System.out.println(solution.toString() + " " + solution.getArea());
	}
	
	private void calculateHull(){
		
		Point aux= cloud.get(cloud.size() - 1);
		cloud.set(cloud.size() - 1, cloud.get(0));
		cloud.set(0, aux);
		Collections.sort(cloud, (_a, _b) -> cloud.get(0).getCoTan(_a) < cloud.get(0).getCoTan(_b) ? -1 : cloud.get(0).getCoTan(_a) == cloud.get(0).getCoTan(_b) ? 0 : 1);
		
		hull.push(cloud.get(0));
		hull.push(cloud.get(1));
		hull.push(cloud.get(2));
		
		for (int i=3; i<cloud.size(); i++) {
			while(ccw(hull.get(hull.size() - 2), hull.get(hull.size() - 1), cloud.get(i)) <= 0) {
				hull.pop();
			}
			hull.push(cloud.get(i));
		}
	}
	
	private static double ccw(Point _a, Point _b, Point _c) {
		return (_b.getX() - _a.getX())*(_c.getY() - _a.getY()) - (_b.getY() - _a.getY())*(_c.getX() - _a.getX());
	}
	
}
