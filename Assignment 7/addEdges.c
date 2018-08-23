#include "myheader.h"

VERTEX* addEdges(VERTEX array[], FILE **file) {
	
	int j=0;
	int k=0;
	char char1=NULL;
	char char2=NULL;
	char str[2];
	
	rewind(*file);

	//This loop should go through the whole file
	while(true) {
		//Get the second char from each line, then add that char as an edge to that vertex
		while(true) {
			str[0] = fgetc(*file);

			if(str[0] == EOF) {
				break;
			}
			if(isalpha(str[0])) {
				while(str[1] != EOF) {
					str[1] = fgetc(*file);

					if(isalpha(str[1])) {
						break;
					}
				}
				break;
			}	
		}
		if(str[0] == EOF) {
			break;
			}
		for(j=0; j<256; j++) {
			if(array[j].c == str[0]) {

				for(k=0; k<256; k++) {
					if(array[k].c == str[1]) {
						addEdge(&array[j], &array[k]);
						}
					}
				}
			}		
		}			
	return array;
	}
