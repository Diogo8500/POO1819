/*
 * point.c
 *
 *  Created on: 18 Sep 2018
 *      Author: Diogo
 */

#include <stdlib.h>
#include <math.h>
#include "point.h"

typedef struct _point{
	float x, y;
}infoPoint, *apPoint;

point newPoint(float x, float y){
	apPoint nPoint = (apPoint)malloc(sizeof(infoPoint));
	nPoint->x = x;
	nPoint->y = y;
	return (point)nPoint;
}

void setVals(point a, float x, float y){

	((apPoint)a)->x = x;
	((apPoint)a)->y = y;

}

float distPoints(point a, point b){

	float xDif = ((apPoint)a)->x - ((apPoint)b)->x;
	float yDif = ((apPoint)a)->y - ((apPoint)b)->y;

	return sqrt(xDif*xDif + yDif*yDif);
}

float getX(point a){
	return ((apPoint)a)->x;
}

float getY(point a){
	return ((apPoint)a)->y;
}
