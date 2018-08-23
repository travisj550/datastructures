//Travis Jones N00436223
//Assignment 7 COP3530

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>

struct EDGETAG;
struct LINKTAG;

typedef struct
{
    char c;
    bool isVisited;
    struct EDGETAG* p;
} VERTEX;

typedef struct EDGETAG
{
    VERTEX* v;
    struct EDGETAG* q;
} EDGE;

typedef struct 
{
	int size;
	VERTEX* array;
	int front;
	int rear;
} QUEUE;

typedef struct LINKTAG
{
	VERTEX* v;
	struct LINKTAG* next;
} LINK;

typedef struct
{
	LINK* first;

} LINKLIST;

//Get number of vertices from file
VERTEX* fillArray(VERTEX array[], FILE **file);
//Add edges to vertex array
VERTEX* addEdges(VERTEX array[], FILE **file);
//Count the number of unique vertices in file
void printAdjacent(VERTEX v);
//Print array of vertices and also return the total number of vertices
int printTable(VERTEX* v, int size);
//Create an edge from a to b
void addEdge(VERTEX* a, VERTEX* b);
//Depth first search starting from v
void dfs(VERTEX* v);
//Breadth first search starting from v
void bfs(VERTEX array[], VERTEX* v, int size);
//Topological sort starting at v
LINKLIST* topo(VERTEX* v, LINKLIST* list);
//Set all vertices to isVisited=0
VERTEX* clearVisits(VERTEX array[]);
//Print vertices from a linked list
void printList(LINKLIST* list);
//Insert vertex at end of queue
void qInsert(QUEUE* q, VERTEX* v);
//Remove vertex from front of queue
VERTEX* qRemove(QUEUE* q);
//Return true if queue is empty
bool qIsEmpty(QUEUE* q);
//Find the next unvisited vertex for v
VERTEX* nextUnvisited(VERTEX* v);
