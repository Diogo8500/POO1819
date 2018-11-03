import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class TestShapeSolver {

	@Test (timeout=300)
	public void test() {
		List<Point> cloud = new ArrayList<Point>();
		Point a = new PointClass(3,3);
		Point b = new PointClass(3,2);
		Point c = new PointClass(1,1);
		Point d = new PointClass(5,1);
		/*Point e = new PointClass(9,3);
		Point f = new PointClass(6,2);
		Point g = new PointClass(7,2);
		Point h = new PointClass(8,2);
		Point i = new PointClass(5,1);
		Point j = new PointClass(6,1);
		Point k = new PointClass(8,1);*/
		Collections.addAll(cloud, a,b,c,d/*,e,f,g,h,i,j,k*/);
		ShapeSolver s = new ShapeSolver(cloud);
		s.solution();
	}

}
