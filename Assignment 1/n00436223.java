/*Travis Jones n00436223
 *Assignment 1
 *COP3530.83194
 */
class HighArray
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
   //-----------------------------------------------------------
   public HighArray(int max)         // constructor
      {
      a = new long[max];                 // create the array
      nElems = 0;                        // no items yet
      }
   //-----------------------------------------------------------
   public boolean find(long searchKey)
      {                              // find specified value
      int j;
      for(j=0; j<nElems; j++)            // for each element,
         if(a[j] == searchKey)           // found item?
            break;                       // exit loop before end
      if(j == nElems)                    // gone to end?
         return false;                   // yes, can't find it
      else
         return true;                    // no, found it
      }  // end find()
   //-----------------------------------------------------------
   public void insert(long value)    // put element into array
      {
      a[nElems] = value;             // insert it
      nElems++;                      // increment size
      }
   //-----------------------------------------------------------
   public boolean delete(long value)
      {
      int j;
      for(j=0; j<nElems; j++)        // look for it
         if( value == a[j] )
            break;
      if(j==nElems)                  // can't find it
         return false;
      else                           // found it
         {
         for(int k=j; k<nElems; k++) // move higher ones down
            a[k] = a[k+1];
         nElems--;                   // decrement size
         return true;
         }
      }  // end delete()
   //-----------------------------------------------------------
   public void display()             // displays array contents
      {
      for(int j=0; j<nElems; j++)       // for each element,
         System.out.print(a[j] + " ");  // display it
      System.out.println("");
      }
   //-----------------------------------------------------------
   public long getMax() {
	   
	   if(nElems == 0)
		   //If the array is empty, return -1
		   return -1;
	   
	   long max = a[0];
	   //max uses a[0] as the initial maximum
	   for(int i = 1; i < a.length; i++) {
		   //The array is unordered so this for loop starts at a[1]
		   if(a[i] > max) 
			   //A new max is set if the value at this index is greater
			   max = a[i]; 
		   }
	   //After the for loop is finished, max is returned
	   return max;
	   }
   public long getMin() {
	   
	   if(nElems == 0)
		   //If the array is empty, return -1
		   return -1;
	   
	   long min = a[0];
	   //min uses a[0] as the initial maximum
	   for(int i = 1; i < a.length; i++) {
		   //The array is unordered so this for loop starts at a[1]
		   if(a[i] < min)
			   //A new min is set if the value at this index is smaller
			   min = a[i];
		   }
	   //After the for loop is finished, min is returned
	   return min;
	   }
   }  // end class HighArray
////////////////////////////////////////////////////////////////
class n00436223
   {
   public static void main(String[] args)
      {
      int maxSize = 100;            // array size
      HighArray arr;                // reference to array
      arr = new HighArray(maxSize); // create the array

      arr.insert(77);               // insert 10 items
      arr.insert(99);
      arr.insert(44);
      arr.insert(55);
      arr.insert(22);
      arr.insert(88);
      arr.insert(11);
      arr.insert(00);
      arr.insert(66);
      arr.insert(33);

      arr.display();                // display items

      int searchKey = 35;           // search for item
      if( arr.find(searchKey) )
         System.out.println("Found " + searchKey);
      else
         System.out.println("Can't find " + searchKey);

      arr.delete(00);               // delete 3 items
      arr.delete(55);
      arr.delete(99);

      arr.display();                // display items again
      
      //Create variables y and z
      long y;
      long z;
      /*Code from assignment instructions to print max and min values
      of the array hidden in the HighArray object arr*/
      y=arr.getMax();
      System.out.println(y);
      z=arr.getMin();
      System.out.println(z); 
      }  // end main()
   }  // end class n00436223
