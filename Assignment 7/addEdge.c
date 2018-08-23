#include "myheader.h"

void addEdge(VERTEX* a, VERTEX* b) {
	
    EDGE* c = (EDGE*)malloc(sizeof(EDGE));
	EDGE* temp = (EDGE*)malloc(sizeof(EDGE));
	
	if(a->p != NULL) {

		//If a's first edge is already to b...
		if(a->p->v->c == b->c) {
			return;
		}
		//If not, step forward to the next edgetag
		temp = a->p->q;
		//iterate through a's edgetags until null
		while(temp != NULL) {
			//If any of a's other edges is already to b...
			if(temp->v->c == b->c) {
				return;
			}
			//If not, step forward to another edgetag
			temp = temp->q;
		}
	}

	temp = a->p; //reference to a's previous edge in temp
	c->v = b;
	a->p = c;
	c->q = temp; //set c to point to a's previous edge
}
