#include "myheader.h"

void printAdjacent(VERTEX v) {
	
	EDGE* e = (EDGE*)malloc(sizeof(EDGE));
	//A recursive dfs might be similar to this code:
	if(v.p != NULL) {
		
		printf("%c", v.p->v->c);
		
		e = v.p->q;
		
		while(e != NULL) {
			
			printf("%c", e->v->c);
			e = e->q;
		}
	}
}
