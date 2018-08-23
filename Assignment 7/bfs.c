#include "myheader.h"

//bfs is based on bfs.java from Lafore
void bfs(VERTEX array[], VERTEX* v, int size) {

	//Create a queue and its struct fields
	QUEUE* q = (QUEUE*)malloc(sizeof(QUEUE));
	VERTEX* qArray = (VERTEX*)calloc(size, sizeof(VERTEX));
	VERTEX* one = (VERTEX*)malloc(sizeof(VERTEX));
	VERTEX* two = (VERTEX*)malloc(sizeof(VERTEX));

	q->array = qArray;
	q->size = size;
	q->rear = -1;
	q->front = 0;
	//Visit first vertex and print it
	v->isVisited = 1;
	printf("%c ", v->c);
	//Insert first vertex into queue
	qInsert(q, v);

	while(!qIsEmpty(q)) {

		one = qRemove(q);
		two = nextUnvisited(one);
		if(two == NULL) {
			break;
		}
		two->isVisited = 1;

		//While two equals the next unvisited neighbor of one
		while(two != NULL) {
			
			printf("%c ", two->c);
			qInsert(q, two);
			two = nextUnvisited(one);
			if(two != NULL)
				two->isVisited = 1;
		}
	}
	//Really need to return an array here?
}
