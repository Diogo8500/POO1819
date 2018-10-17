package triangle;

public class Triangle {
	
	private Point a, b, c; // Vertices do triangulo. Sao pontos
	//private double lenghtA, lenghtB, lenghtC; // Comprimento dos lados
	//private double area, perimetre; //Area e perimetro do triangulo
	
	//Construtor do triangulo
	public Triangle(Point _a, Point _b, Point _c) {
		if(_a.equal(_b)) System.exit(1); //
		if(_a.equal(_c)) System.exit(1); // Se algum dos pontos for igual a outro sai do programa
		if(_b.equal(_c)) System.exit(1); //
		
		if(_a.getX() * (_b.getY() - _c.getY()) + _b.getX() * (_c.getY() - _a.getY())  + _c.getX() * (_a.getY() - _b.getY()) == 0) //Se = 0 -> Pontos colineares. Sai do programa
			System.exit(1);
		
		a = _a;
		b = _b;
		c = _c;
	}
	
	//Devolve o vertice A (tipo Point) deste triangulo
	public Point getPointA() {
		return a;
	}
	
	//Devolve o vertice B (tipo Point) deste triangulo
	public Point getPointB() {
		return b;
	}
	
	//Devolve o vertice C (tipo Point) deste triangulo
	public Point getpointC() {
		return c;
	}
	
	//Devolve o comprimento do lado A deste triangulo
	public double getLenghtA() {
		return a.distPoint(b);
	}
	
	//Devolve o comprimento do lado B deste triangulo
	public double getLenghtB() {
		return b.distPoint(c);
	}
	
	//Devolve o comprimento do lado C deste triangulo
	public double getLenghtC() {
		return c.distPoint(a);
	}
	
	public double getLongestSide() {
		double aux = getLenghtA();
		if (aux < getLenghtB()) aux = getLenghtB();
		if(aux < getLenghtC()) aux = getLenghtC();
		return aux;
	}
	
	protected void rotate() {          
		Point aux = a;   
		a = b; 
		b = c; 
		c = aux;                //Metodos auxiliares usadas no metodo equal(Triangle) abaixo (linha 92)
	}                           //Rodam e espelham os comprimentos dos lados do triangulo
	                            //NAO devem ser usadas em mais lado NENHUM
	protected void flip() {            
		Point aux = a;   
		a = b; 
		b = aux;          
	}
	
	//Compara se este triangulo e outro sao iguais usando os comprimentos dos seus lados
	public boolean equal(Triangle _t) {
		double oriLenghtA = getLenghtA();
		double oriLenghtB = getLenghtB();
		double oriLenghtC = getLenghtC();
		
		for (int i=0; i<2; i++) {
			for(int j=0; j<3; j++) {
				if(_t.getLenghtA() == oriLenghtA && _t.getLenghtB() == oriLenghtB && _t.getLenghtC() == oriLenghtC) 
					return true;
				_t.rotate();
			}
			_t.flip();
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
		System.out.println(a.toString());
		System.out.println(b.toString());
		System.out.println(c.toString());
	}
}
