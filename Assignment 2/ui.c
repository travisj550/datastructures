#include "my.h"

void ui(int array[], int size) {
	//index is set to 0 to start the while loop
	int index=0;
	while(1){
		//The while loop will continue until it reaches break
		printf("Which index key? ");
		scanf("%d", &index);
		if(index==-1)
			//If the user enters -1, the loop ends
			break;
		else if(index < size && index > -1) {
			/*If the index key is within the array bounds, it will be 
			less than size and greater than -1.*/
			printf("The value is %d \n", array[index]);
		}
		else if(index >= size || index < -1) {
			/*If the index key is greater than or equal to size, or less
			than -1, it's outside of the array bounds.*/
			printf("There are not this many numbers in the file. \n");
		}
	}
}