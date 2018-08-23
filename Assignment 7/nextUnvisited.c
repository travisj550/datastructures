#include "myheader.h"

//Return the next unvisited neighbor of v
VERTEX* nextUnvisited(VERTEX* v) {

	EDGE* currentEdge = (EDGE*)malloc(sizeof(EDGE));

	if(v->p != NULL) {
		
		if(v->p->v->isVisited != 1) {
			return v->p->v;
		}
		currentEdge = v->p;

		do {
			if(currentEdge->v->isVisited != 1) {
				return currentEdge->v;
			}
			else if(currentEdge->q != NULL) {
				currentEdge = currentEdge->q;
			}
			else {
				break;
			}
		} while (currentEdge != NULL);
	}
	return NULL;
}
