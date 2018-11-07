import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<PointClass> cloud = new ArrayList<PointClass>();
		Collections.addAll(cloud, new PointClass(2,3), new PointClass(1,2), new PointClass(3,2), new PointClass(2,1));
		ShapeSolver solver = new ShapeSolver(cloud);
		solver.solution();

	}

}
