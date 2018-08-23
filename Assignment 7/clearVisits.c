#include "myheader.h"

VERTEX* clearVisits(VERTEX array[]) {

	int i=0;

	while(true) {

		array[i].isVisited = 0;

		if(array[i+1].c != NULL) {
			i++;
		}
		else {
			break;
		}
	}
	return array;
}
