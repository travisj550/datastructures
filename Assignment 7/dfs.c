#include "myheader.h"

void dfs(VERTEX* v) {

	VERTEX* temp = (VERTEX*)malloc(sizeof(VERTEX));
	//Vertices are printed if they haven't been visited
	if(!v->isVisited) {
		printf("%c ", v->c);
	}
	v->isVisited = 1;
	if(v->p != NULL) {
		temp = v->p->v;
	}
	else {
		return;
	}
	if(temp != NULL && !temp->isVisited) {
		dfs(temp);
	}
	if(v->p->q != NULL && !v->p->q->v->isVisited) {
		temp = v->p->q->v;
		dfs(temp);
	}
	return;
}
