/*
 ============================================================================
 Name        : Triangle.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "point.h"

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

int main(int argc, char *argv[]) {

	float x, y;
	int nPoints;
	point coordO[3];
	int combArray[3] = {0,1,2};
	float dist12, dist23, dist31;
	float _dist12, _dist23, _dist31;

	for(int i=0; i<3; i++){
		scanf("%f %f", &x, &y);
		coordO[i] = newPoint(x, y);
	}

	dist12 = distPoints(coordO[0], coordO[1]);
	dist23 = distPoints(coordO[1], coordO[2]);
	dist31 = distPoints(coordO[2], coordO[0]);

	scanf("%d", &nPoints);

	point coordS[nPoints];

	for(int i=0; i<nPoints; i++){
		scanf("%f %f", &x, &y);
		coordS[i] = newPoint(x, y);
	}

	do{
		_dist12 = distPoints(coordS[combArray[0]], coordS[combArray[1]]);
		_dist23 = distPoints(coordS[combArray[1]], coordS[combArray[2]]);
		_dist31 = distPoints(coordS[combArray[2]], coordS[combArray[0]]);

		if((dist12 == _dist12 || dist12 == _dist23 || dist12 == _dist31) && (dist23 == _dist12 || dist23 == _dist23 || dist23 == _dist31) && (dist31 == _dist12 || dist31 == _dist23 || dist31 == _dist31)){
			printf("EUREKA!!\n");
			printf("%.0f %.0f\n", getX(coordS[combArray[0]]), getY(coordS[combArray[0]]));
			printf("%.0f %.0f\n", getX(coordS[combArray[1]]), getY(coordS[combArray[1]]));
			printf("%.0f %.0f\n", getX(coordS[combArray[2]]), getY(coordS[combArray[2]]));
			return 0;
		}

	}while(nextComb(combArray, 3, nPoints));

	return EXIT_SUCCESS;
}

//Falta a condicao para que os pontos sejam apresentados por ordem crescente das abcissas e ordenadas;
