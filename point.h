/*
 * point.h
 *
 *  Created on: 18 Sep 2018
 *      Author: Diogo
 */

#ifndef POINT_H_
#define POINT_H_

typedef void* point;

point newPoint(float x, float y);

void setVals(point a, float x, float y);

float distPoints(point a, point b);

float getX(point a);

float getY(point a);

#endif /* POINT_H_ */
