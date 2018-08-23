#include "my.h"

void readFile(int array[], FILE file, int size) {
	
	int i;
	int j;
	//Move to the beginning of the file
	//j is used to store the first int so it can be skipped in the for loop
	//Writing all the ints in the file into array, ending at size-1
	for(i=0; i<size; i++) {
		fscanf(&file, "%d", &array[i]);
	}
}