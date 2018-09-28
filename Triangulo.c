/*
 ============================================================================
 Name        : Triangulo.c
 Author      : LOlada
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct _point{
	float x, y;
}Point;

float distPoints(Point a, Point b){

	float xDif = a.x - b.x;
	float yDif = a.y - b.y;

	return sqrt(xDif*xDif + yDif*yDif);
}

float getX(Point a){
	return a.x;
}

float getY(Point a){
	return a.y;
}

int nextComb(int comb[], int k, int n) {
    int i = k - 1;
    ++comb[i];
    while ((i >= 0) && (comb[i] >= n - k + 1 + i)) {
        --i;
        ++comb[i];
    }

    if (comb[0] > n - k)
        return 0;

    for (i = i + 1; i < k; ++i)
        comb[i] = comb[i - 1] + 1;

    return 1;
}

int main(void) {
	float x, y;
		int nPoints;
		Point coordO[3];
		int combArray[3] = {0,1,2};
		float dist12, dist23, dist31;
		float _dist12, _dist23, _dist31;
		int i;

		for(i=0; i<3; i++){
			scanf("%f %f", &x, &y);
			if(x<0 || y<0)
				return 1;
			coordO[i].x = x;
			coordO[i].y = y;
		}

		dist12 = distPoints(coordO[0], coordO[1]);
		dist23 = distPoints(coordO[1], coordO[2]);
		dist31 = distPoints(coordO[2], coordO[0]);

		scanf("%d", &nPoints);

		Point coordS[nPoints];

		for(i=0; i<nPoints; i++){
			scanf("%f %f", &x, &y);
			if(x<0 || y<0)
				return 1;
			coordS[i].x = x;
			coordS[i].y = y;
		}

		do{
			_dist12 = distPoints(coordS[combArray[0]], coordS[combArray[1]]);
			_dist23 = distPoints(coordS[combArray[1]], coordS[combArray[2]]);
			_dist31 = distPoints(coordS[combArray[2]], coordS[combArray[0]]);

			if((dist12 == _dist12 || dist12 == _dist23 || dist12 == _dist31) && (dist23 == _dist12 || dist23 == _dist23 || dist23 == _dist31) && (dist31 == _dist12 || dist31 == _dist23 || dist31 == _dist31)){
				Point aux;
				Point n1 = coordS[combArray[0]];
				Point n2 = coordS[combArray[1]];
				Point n3 = coordS[combArray[2]];

				if (n1.x > n2.x)
				    {
				      /* troca o conteudo das variaveis n1 e n2 */
				      aux = n1;
				      n1  = n2;
				      n2  = aux;
				    }

				  /* Neste ponto do programa n1 <= n2 */
				  if (n2.x <= n3.x)
				    { /* n1 <= n2 e n2 <= n3 */
					  printf("%.0f %.0f\n", getX(n1), getY(n1));
					  printf("%.0f %.0f\n", getX(n2), getY(n2));
					  printf("%.0f %.0f\n", getX(n3), getY(n3));
				    }
				  else
				    { /* n1 <= n2 e n3 < n2 */
				      if (n1.x <= n3.x)
					{ /* n1 <= n3 e n3 < n2 */
				    	  printf("%.0f %.0f\n", getX(n1), getY(n1));
				    	  printf("%.0f %.0f\n", getX(n3), getY(n3));
				    	  printf("%.0f %.0f\n", getX(n2), getY(n2));
					}
				      else
					{ /* n3 < n1 e n1 <= n2 */
				    	  printf("%.0f %.0f\n", getX(n3), getY(n3));
				    	  printf("%.0f %.0f\n", getX(n2), getY(n2));
				    	  printf("%.0f %.0f\n", getX(n1), getY(n1));
					}
				    }
				  return 0;
			}

		}while(nextComb(combArray, 3, nPoints));

		return EXIT_SUCCESS;
}
