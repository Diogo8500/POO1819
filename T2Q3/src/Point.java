
public class Point {
	
	//Coordenadas x e y;
	private double x, y;
	
	//Construtor Point
	public Point (double _x, double _y) {
		this.x = _x;
		this.y = _y;
	}
	
	//Retorna a coordenada X
	public double getX() {
		return this.x;
	}
	
	//Retorna a coordenada Y
	public double getY() {
		return this.y;
	}
	
	//Verifica se as coordenadas X e Y do ponto sao iguais as de outro ponto
	public boolean equal(Point _a) {
		boolean xEqual = this.x == _a.getX();
		boolean yEqual = this.y == _a.getY();
		
		return xEqual && yEqual;
	}
	
	//Retorna a distancia entre o ponto e outro
	public double distPoint(Point _a) {
		double distX = _a.getX() - this.x;
		double distY = _a.getY() - this.y;
		double toSqrt = distX*distX + distY*distY;
		
		return Math.sqrt(toSqrt);
	}
}
