#include "myheader.h"

//Remove vertex from front of queue
VERTEX* qRemove(QUEUE* q) {

	VERTEX* temp = (VERTEX*)malloc(sizeof(VERTEX));
	temp = &q->array[q->front];
	q->front++;


	if(q->front == q->size) {
		q->front = 0;
	}
	return temp;
}
