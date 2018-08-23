#include "myheader.h"

int main(int argc, const char *argv[])
{
	VERTEX *array;
	int size;
	//Use calloc for arrays
	array = (VERTEX*)calloc(256, sizeof(VERTEX));
	FILE *file;
	
	file = fopen(argv[1], "r");
	
	//Fill array with vertices of unique A-Z chars from file
	array = fillArray(array, &file);

	//Add each unique edge to the associated vertices
	array = addEdges(array, &file);

	//Print the adjacency table and return the number of vertices
	printf("****************************************\n");
	size = printTable(array, size);

	printf("\nDepth first search:\n");
	//Call dfs with the vertex from the beginning of the file
	dfs(&array[0]);
	printf("\n");

	//Set all vertices to isVisited=0
	array = clearVisits(array);

	printf("\nBreadth first search:\n");
	//Call bfd with first vertex
	bfs(array, &array[0], size);
	printf("\n");

	array = clearVisits(array);

	printf("\nTopological sort:\n");
	//Create a new linklist that can point to the first link in a list
	LINKLIST* list = (LINKLIST*)malloc(sizeof(LINKLIST));

	//Call topo with the first vertex from the file and list
	list = topo(&array[0], list);

	//Print the linked list from topo
	printList(list);
	printf("\n****************************************\n");

	fclose(file);
	
	return 0;
}
