#include "myheader.h"

VERTEX* fillArray(VERTEX array[], FILE **file) {
	
	int i=0;
	int empty=0;
	char str[2];
	bool exists0;
	bool exists1;
	
	rewind(*file);
	//Check each char in file until EOF
	while(true) {

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
			exists0=false;
			exists1=false;
		
			 for(i=0; i<256; i++) {
				
				if(array[i].c == str[0]) {		
					exists0=true;
				}
				if(array[i].c == str[1]) {
					exists1=true;
				}
				if(array[i].c == NULL && array[empty].c != NULL) {
					empty = i;
				}
			}
			if(exists0 != true) {
				
				array[empty].c = str[0];
				array[empty].isVisited = 0;

				empty++;
			}
			if(exists1 != true && str[0] != str[1]) {
				
				array[empty].c = str[1];
				array[empty].isVisited = 0;
			}
	}
	return array;
}
