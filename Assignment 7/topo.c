#include "myheader.h"

LINKLIST* topo(VERTEX* v, LINKLIST* list) {

	VERTEX* temp = (VERTEX*)malloc(sizeof(VERTEX));
	//A linked list where links to vertices are added at the beginning
	LINK* new = (LINK*)malloc(sizeof(LINK));
	EDGE* currentEdge = (EDGE*)malloc(sizeof(EDGE));
	
	if(v->isVisited) {
		return list;
	}
	currentEdge = v->p;
	//Iterate through the children of v
	while(currentEdge != NULL) {
		temp=currentEdge->v;
		topo(temp, list);		
		currentEdge = currentEdge->q;
	}
	//If no more edges, call topo with the first child of v
	if(v->p != NULL) {
		temp = v->p->v;
		topo(temp, list);
	}
	v->isVisited = 1;
	new->v = v;
	new->next = list->first;
	list->first = new;
	
	return list;
}
