import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		List<Point> cloud = new ArrayList<Point>();
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		for (int i=0; i<num; i++) {
			cloud.add(new Point(input.nextInt(), input.nextInt()));
		}
		input.close();
		Client solver = new Client(cloud);
		solver.solution();
	}

}
