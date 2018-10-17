package triangle;

import java.util.Scanner;

public class TriangleClient {
	private int index[] = {0, 1, 2};
    private int universeSize;
    private Point[] originalPoints = new Point[3];
    private Point[] universePoints;
    private Triangle originalTri;
    private double longestSide;
    //private Scanner in = new Scanner(System.in);
    
	//Funcao auxiliar que modifica um array de k inteiros de modo a
	//obtermos todos os indexes possiveis sem repeticoes
	//C(n, k) - Combinacoes n, k a k
	private static final boolean nextComb(int comb[], int k, int n) {
	    int i = k - 1;
	    ++comb[i];
	    while ((i >= 0) && (comb[i] >= n - k + 1 + i)) {
	    		--i;
	    		if(i>=0) ++comb[i];
	    		else return false;
	    }
	    if (comb[0] > n - k)
	        return false;

	    for (i = i + 1; i < k; ++i)
	        comb[i] = comb[i - 1] + 1;
	    return true;
	}
	
	//Metodo estatico que dado 3 pontos devolve a area do poligno formado por esses 3 vertices (aka triangulo) 
	//um metodo estatico e basicamente um metodo que pode ser usado sem estar associado a um objecto
	//Math.sqrt() e um bom exemplo de um metodo estatico
	//[ Ax * (By - Cy) + Bx * (Cy - Ay) + Cx * (Ay - By) ] / 2 = Area do triangulo
	private static final double getAreaFromPoints(Point _a, Point _b, Point _c) {
		return Math.abs(_a.getX() * (_b.getY() - _c.getY()) + _b.getX() * (_c.getY() - _a.getY()) + _c.getX() * (_a.getY() - _b.getY())) / 2;
	}
	
	public final void readInput() {
		Scanner in = new Scanner(System.in);
		
		//Le os 3 pontos originais da consola e cria um triangulo com esses mesmos pontos
	    for(int i=0; i<3; i++) {
	    	originalPoints[i] = new Point(in.nextDouble(), in.nextDouble());
	    }
	    originalTri = new Triangle(originalPoints[0], originalPoints[1], originalPoints[2]);
	    longestSide = originalTri.getLongestSide();
	    
	    //le os pontos que constituem o mundo dos pontos (universePoints[])
	    universeSize = in.nextInt();
	    universePoints = new Point[universeSize];
	    for(int i=0; i<universeSize; i++) {
	    	universePoints[i] = new Point(in.nextDouble(), in.nextDouble());
	    }
	    in.close();
	}
    
    public final void solver() {
    	//Comnpara o trinagulo original com o triangulo formado por 3 pontos do mundo dos pontos
    	do {
	    	Point _a = universePoints[index[0]];
	    	Point _b = universePoints[index[1]];
	    	Point _c = universePoints[index[2]];
			if(_a.distPoint(_b) <= longestSide && _b.distPoint(_c) <= longestSide && _c.distPoint(_a) <= longestSide && getAreaFromPoints(_a, _b, _c) != 0) {
				Triangle _t = new Triangle(_a, _b, _c);
				if(originalTri.equal(_t)) {
					_t.printVertices();
					return;
				}
			}
    	}while(nextComb(index, 3, universeSize));
    } 
}
