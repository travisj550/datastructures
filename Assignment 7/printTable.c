#include "myheader.h"

int printTable(VERTEX array[], int size) {
	
	int i=0;
	printf("  Vertex  Adjacent\n");
	//Use <256 to terminate loop for printArray
	for(i=0; i<256; i++) {
		if(array[i].c != NULL) {
			
			printf("%d %c\t  ", i+1, array[i].c);
			
			printAdjacent(array[i]);

			printf("\n");
			size++;
		}
	}
	return size;
}
