#include "myheader.h"

//Insert vertex at end of queue
void qInsert(QUEUE* q, VERTEX* v) {

	if(q->rear == q->size - 1) {
		q->rear = -1;
	}
	q->rear++;
	q->array[q->rear] = *v;

}
