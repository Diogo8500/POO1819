import java.util.List;

/**
 * Client class that receives the input of the problem
 * and prints out the solution.
 *
 */
public class Client {
	
	private ShapeSolver solver;;
	
	public Client(List<Point> _cloud) {
		solver = new ShapeSolver(_cloud);
	}
	
	public void solution(){
		Polygon shape = solver.getShape();
		System.out.println(shape.toString() + " " + (int)shape.getPerimetre());
	}
	
}
