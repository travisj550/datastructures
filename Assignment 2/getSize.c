#include "my.h"

int getSize(FILE file) {
	//getSize reads the first int in a file and returns it
	int size;
	fscanf(&file, "%d", &size);
	return size;
}