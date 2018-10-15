public class Triangle {
	
	private Point a, b, c; // Vertices do triangulo. Sao pontos
	private double lenghtA, lenghtB, lenghtC; // Comprimento dos lados
	private double area, perimetre; //Area e perimetro do triangulo
	
	//Construtor do triangulo
	public Triangle(Point _a, Point _b, Point _c) {
		if(_a.equal(_b)) System.exit(1); //
		if(_a.equal(_c)) System.exit(1); // Se algum dos pontos for igual a outro sai do programa
		if(_b.equal(_c)) System.exit(1); //
		
		double _area = Triangle.getAreaFromPoints(_a, _b, _c); //Chama o metodo estatico desta classe (ver linha 31)
		if(_area == 0) //Se area = 0 -> Pontos colineares. Sai do programa
			System.exit(1);
		
		double _lenghtA = _a.distPoint(_b); //
		double _lenghtB = _b.distPoint(_c); // Calcula as distancias entre os 3 pontos
		double _lenghtC = _c.distPoint(_a); //
		
		this.lenghtA = _lenghtA;
		this.lenghtB = _lenghtB;
		this.lenghtC = _lenghtC;
		this.perimetre = _lenghtA + _lenghtB + _lenghtC;
		this.area = _area;
		this.a = _a;
		this.b = _b;
		this.c = _c;
	}
	
	//Metodo estatico que dado 3 pontos devolve a area do poligno formado por esses 3 vertices (aka triangulo) 
	//um metodo estatico e basicamente um metodo que pode ser usado sem estar associado a um objecto
	//Math.sqrt() e um bom exemplo de um metodo estatico
	//[ Ax * (By - Cy) + Bx * (Cy - Ay) + Cx * (Ay - By) ] / 2 = Area do triangulo
	public static double getAreaFromPoints(Point _a, Point _b, Point _c) {
		return (_a.getX() * (_b.getY() - _c.getY()) + _b.getX() * (_c.getY() - _a.getY())  + _c.getX() * (_a.getY() - _b.getY())) / 2;
	}
	
	//Devolve o vertice A (tipo Point) deste triangulo
	public Point getPointA() {
		return this.a;
	}
	
	//Devolve o vertice B (tipo Point) deste triangulo
	public Point getPointB() {
		return this.b;
	}
	
	//Devolve o vertice C (tipo Point) deste triangulo
	public Point getpointC() {
		return this.c;
	}
	
	//Devolve o comprimento do lado A deste triangulo
	public double getLenghtA() {
		return this.lenghtA;
	}
	
	//Devolve o comprimento do lado B deste triangulo
	public double getLenghtB() {
		return this.lenghtB;
	}
	
	//Devolve o comprimento do lado C deste triangulo
	public double getLenghtC() {
		return this.lenghtC;
	}
	
	//Devolve o perimetro deste triangulo
	public double getPerimeter() {
		return this.perimetre;
	}
	
	//Devolve a area deste triangulo
	public double getArea() {
		return this.area;
	}
	
	private void rotate() {          //
		double aux = this.lenghtA;   //
		this.lenghtA = this.lenghtB; //
		this.lenghtB = this.lenghtC; //
		this.lenghtC = aux;          //Metodos auxiliares usadas no metodo equal(Triangle) abaixo (linha 92)
	}                                //Rodam e espelham os comprimentos dos lados do triangulo
	                                 //NAO devem ser usadas em mais lado NEHUM
	private void flip() {            //
		double aux = this.lenghtA;   //
		this.lenghtA = this.lenghtB; //
		this.lenghtB = aux;          //
	}
	
	//Compara se este triangulo e outro sao iguais usando os comprimentos dos seus lados
	public boolean equal(Triangle _t) {
		boolean sameLenghtA = false;
		boolean sameLenghtB = false;
		boolean sameLenghtC = false;
		
		double originalA = this.lenghtA;
		double originalB = this.lenghtB;
		double originalC = this.lenghtC;
		
		for (int i=0; i<2; i++) {
			for(int j=0; j<3; j++) {
				sameLenghtA = this.lenghtA == _t.getLenghtA();
				sameLenghtB = this.lenghtB == _t.getLenghtB();
				sameLenghtC = this.lenghtC == _t.getLenghtC();
				if(sameLenghtA && sameLenghtB && sameLenghtC) {
					this.lenghtA = originalA;
					this.lenghtB = originalB;
					this.lenghtC = originalC;
					return true;
				}
				this.rotate();
			}
			this.flip();
		}
		
		return false;
	}
	
	//Funcao que imprime os 3 vertices do triangulo na forma "X Y" ordenados primeiro pelas abcissas (X's)
	//e segundo pelas ordenadas (Y's) caso 2 ou mais abcissas sejam iguais
	public void printVertices() {
		Point aux;

		if (a.getX() > b.getX()) {
			aux = a;
			a  = b;
			b  = aux;
	    }

		if (b.getX() > c.getX())  {
	    	if (a.getX() <= c.getX()) { 
	    		aux = b;
	    		b = c;
	    		c = aux;
	    	} else {
	    		aux = a;
	    		a = c;
	    		c = b;
	    		b = aux;
	    	}
		}
		
		if(a.getX() == b.getX()) {
			if(a.getY() > b.getY()) {
				aux = a;
				a = b;
				b = aux;
			}
		}

		if(b.getX() == c.getX()) {
			if(b.getY() > c.getY()) {
				aux = b;
				b = c;
				c = aux;
			}
		}

		System.out.println((int)a.getX() + " " + (int)a.getY());
		System.out.println((int)b.getX() + " " + (int)b.getY());
		System.out.println((int)c.getX() + " " + (int)c.getY());
	}
}
