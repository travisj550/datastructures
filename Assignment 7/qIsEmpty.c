#include "myheader.h"

//Return true if queue is empty
bool qIsEmpty(QUEUE* q) {

	if(q->rear+1 == q->front || q->front+q->size-1 == q->rear)
		return 1;
	else
		return 0;

}
