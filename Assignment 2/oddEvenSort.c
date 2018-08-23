#include "my.h"

void oddEvenSort(int array[], int size) {
	//The number of swaps starts at 1 to begin the while loop
	int even_swaps=1;
	int odd_swaps=1;
	int i;
	
	while(even_swaps!=0 || odd_swaps!=0) {
		/*Number of swaps is set to 0 at the beginning of each loop.
		New loops will not begin if the previous loops ended with
		no swaps*/
		even_swaps=0;
		odd_swaps=0;
		//Even pair sorting starts at 0 (0,1 2,3 4,5 etc)
		for(i=0; i<size-1; i+=2) {
			if(array[i+1] < array[i]) {
				swap(array, i+1, i);
				even_swaps++;
			}
		}
		//Odd pair sorting starts at 1 (1,2 3,4 5,6 etc)
		for(i=1; i<size-1; i+=2) {
			if(array[i+1] < array[i]) {
				swap(array, i+1, i);
				odd_swaps++;
			}
		}
	}
}