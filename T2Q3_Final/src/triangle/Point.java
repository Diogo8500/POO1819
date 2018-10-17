package triangle;

public class Point {
	
	//Coordenadas x e y;
	private double x, y;
	
	//Construtor Point
	public Point (double _x, double _y) {
		x = _x;
		y = _y;
	}
	
	//Retorna a coordenada X
	public double getX() {
		return x;
	}
	
	//Retorna a coordenada Y
	public double getY() {
		return y;
	}
	
	//Verifica se as coordenadas X e Y do ponto sao iguais as de outro ponto
	public boolean equal(Point _a) {
		return (x == _a.getX() && y == _a.getY());
	}
	
	//Retorna a distancia entre o ponto e outro
	public double distPoint(Point _a) {
		return Math.sqrt(Math.pow(_a.getX() - x, 2) + Math.pow(_a.getY() - y, 2));
	}
	
	@Override
	public String toString() {
		return (int)x + " " + (int)y;
	}
}
