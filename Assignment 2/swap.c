#include "my.h"

void swap(int array[], int a, int b) {
	/*The swap function takes a pair of ints in an array and swaps 
	their indexes*/
	int temp=array[a];
	array[a]=array[b];
	array[b]=temp;
}