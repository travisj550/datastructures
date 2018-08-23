#include "myheader.h"

void printList(LINKLIST* list) {

	LINK* link = (LINK*)malloc(sizeof(LINK));
	link = list->first;

	while(link != NULL) {

		printf("%c ", link->v->c);
		link = link->next;
	}
}
