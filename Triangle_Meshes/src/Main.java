import java.util.*;

public class Main {

	public static void main(String[] args) {
		List<Point> arrayP = new ArrayList<Point>();
		Point a = new SignedPoint(5, 6);
		Point b = new SignedPoint(3, 4);
		Point c = new SignedPoint(10, 6);
		Point d = new SignedPoint(8, 7);
		Point e = new SignedPoint(21, 1);
		Point f = new SignedPoint(15, 10);
		Collections.addAll(arrayP, a, b, c, d, e, f);
		Collections.sort(arrayP);
		System.exit(0);
	}

}
