
public interface Point {

	double getX();

	double getY();

	Point sub(Point vector);

	Point add(Point vector);

	Point mult(double scalar);

	double mag();

	double dot(Point vector);

	double cross(Point vector);

}