#include "my.h"

int main(int argc, char* argv[]) {
	/*The file name is pulled from the command line argument
	and the file is opened for reading*/
	FILE* file = fopen(argv[1], "r");
	//size is set equal to the value returned from getSize
	int size = getSize(file);
	//Declaring array pointer
	int* array;
	//Allocating memory for array based on the number of ints
	array = (int*) calloc(size, sizeof(int));
	//Pulling data from file into array, ending at size
	readFile(array, *file, size);
	//oddEvenSort does passes through the array until there are no swaps
	oddEvenSort(array, size);
	//ui prints output to the user
	for(int i=0; i<size; i++) {
		printf("%d %d\n", i, array[i]);
	}
	
	ui(array, size);
	//file is closed before the program ends
	fclose(file);
}