import java.util.Scanner;

public class Main {
	
	//Funcao auxiliar que modifica um array de k inteiros de modo a
	//obtermos todos os indexes possiveis sem repeticoes
	//C(n, k) - Combinacoes n, k a k
	private static boolean nextComb(int comb[], int k, int n) {
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
	
	public static void main(String[] args) {
		
		int index[] = {0, 1, 2};
        int universeSize;
        Point[] originalPoints = new Point[3];
        Point[] universePoints;
        Triangle originalTri;
        Scanner in = new Scanner(System.in);
        
        //Le os 3 pontos originais da consola e cria um triangulo com esses mesmos pontos
        for(int i=0; i<3; i++) {
        	originalPoints[i] = new Point(in.nextDouble(), in.nextDouble());
        }
        originalTri = new Triangle(originalPoints[0], originalPoints[1], originalPoints[2]);
        
        //le os pontos que constituem o mundo dos pontos (universePoints[])
        universeSize = in.nextInt();
        universePoints = new Point[universeSize];
        for(int i=0; i<universeSize; i++) {
        	universePoints[i] = new Point(in.nextDouble(), in.nextDouble());
        }
        in.close();
        
        //Comnpara o trinagulo original com o triangulo formado por 3 pontos do mundo dos pontos
        do {
        	Point _a = universePoints[index[0]];
        	Point _b = universePoints[index[1]];
        	Point _c = universePoints[index[2]];
    		if(Triangle.getAreaFromPoints(_a, _b, _c) != 0) {
    			Triangle _t = new Triangle(_a, _b, _c);
    			if(originalTri.equal(_t)) {
    				_t.printVertices();
    				return;
    			}
    		}
    	
        }while(nextComb(index, 3, universeSize));
        
	}

}
