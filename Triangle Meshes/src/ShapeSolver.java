import java.util.List;

public class ShapeSolver {
	private List<Point> hull;
	
	public ShapeSolver(List<Point> _hull) {
		if(_hull.size() < 3) throw new IllegalStateException("Hull contains less than 3 points.");
		hull = _hull;
	}
	
	public Polygon getShape() {
		cleanHull();
		Polygon solution;
		if(hull.size() == 3) {
			Point a = hull.get(0);
			Point b = hull.get(1);
			Point c = hull.get(2);
			
			try {
				solution = new EquilateralTriangle(a, b, c);
			}catch(IllegalArgumentException _e) {
				solution = new Triangle(a, b, c);
			}
		}else {
			Point a = hull.get(0);
			Point b = hull.get(1);
			Point c = hull.get(2);
			Point d = hull.get(3);
			
			try {
				solution = new Square(a, b, c, d);
			}catch(IllegalArgumentException _e) {
				try {
					solution = new Rectangle(a, b, c, d);
				}catch(IllegalArgumentException _f) {
					try {
						solution = new Parallelogram(a, b, c, d);
					}catch(IllegalArgumentException _g) {
						solution = new Quadrilateral(a, b, c, d);
					}
				}
			}
		}
		return solution;
	}
	
	private void cleanHull() {
		for(int i=2; i<=hull.size(); i++) {
			if(hull.get(i%hull.size()).isCollinear(hull.get(i - 1), hull.get(i - 2))) {
				hull.remove(i - 1);
				i--;
			}
		}
	}
}
