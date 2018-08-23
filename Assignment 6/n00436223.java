//n00436223 Travis Jones
//COP3530 Assignment 6

import java.io.*;
import java.util.*;

//Modified from hash.java from LaFore
class DataItem
{
	public String s = "-1";
	public int pl = -1;  //pl stores probe lengths for different operations
	public boolean success; //success stores whether an operation succeeded or not
	//--------------------------------------------------------------
	public DataItem(String key)          // constructor
	   { s = key; }
	//--------------------------------------------------------------
	public String getKey()
	   { return s; }
	//--------------------------------------------------------------
}  // end class DataItem

//Modified from hash.java from LaFore
class HashTable
{
	private DataItem[] hashArray;    // array holds hash table
	private int arraySize;
	public char title;
	//-------------------------------------------------------------
	public HashTable(int size, char ab)       // constructor
	   {
	   arraySize = size;
	   hashArray = new DataItem[arraySize];
	   title = ab;
	   }
	//-------------------------------------------------------------
	public void displayInsertTable()
	   {
		System.out.println("Insertion table for " + title + ": ");
		  System.out.printf("%-7s %-40s %s \n", "Index", "String", "Probe length");
	   for(int j=0; j<arraySize; j++)
	      {
	      if(hashArray[j] != null && hashArray[j].s != "-1") {
	    	  
			  System.out.printf("%-7d %-40s %d \n", j, hashArray[j].getKey(), hashArray[j].pl);
		         
	      }
	      }
       System.out.printf("Average probe length:\t\t\t\t %.2f\n\n", avgPL());
	   }
	//-------------------------------------------------------------
	public int hashFunc(String key) {
			
		int hashVal = 0;
		for (int j=0; j<key.length(); j++) {
			int letter = key.charAt(j);
			hashVal = (hashVal * 26 + letter) % arraySize;
		}
		
		return hashVal;
	}
	//-------------------------------------------------------------
	public void lpInsert(String s) // insert with linear probing
	// (assumes table not full)
	{
		//System.out.println("string: " + s);
		DataItem item = new DataItem(s);
		
	   String key = item.getKey();      // extract key
	   int hashVal = hashFunc(key);  // hash the key
	                                 // until empty cell or -1,
	   int probeLength=1; 	//Create a variable for lpInsert probe length
	   while(hashArray[hashVal] != null &&
	                   hashArray[hashVal].getKey() != "-1") //probe until null or -1 (where an item was deleted)
	      {
	      ++hashVal;                 // go to next cell
	      hashVal %= arraySize;      // wraparound if necessary
	      probeLength++;				//Increment probeLength for each loop
	      }
	   item.pl=probeLength;	//Store probe length in pl
	   hashArray[hashVal] = item;    // insert item 
	
	   }  // end lpInsert()
	//-------------------------------------------------------------
	public void qpInsert(String s) // insert with quadratic probing
	// (assumes table not full)
	   {
		DataItem item = new DataItem(s);
	   String key = item.getKey();      // extract key
	   int hashVal = hashFunc(key);  // hash the key
	                                 // until empty cell or -1,
	   int i=1;
	   int probeLength=1; 	//Create a variable for lpInsert probe length
	   
	   while(hashArray[hashVal] != null &&
	                   hashArray[hashVal].getKey() != "-1") //probe until null or -1 (where an item was deleted)
	      {
	      hashVal = hashVal + i*i;                 // go to next cell
	      i++; //Increment i for quadratic probing
	      hashVal %= arraySize;      // wraparound if necessary
	      probeLength++;				//Increment probeLength for each loop
	      }
	   item.pl=probeLength;	//Store probe length in pl
	   hashArray[hashVal] = item;    // insert item 
	   }  // end qpInsert()
	//-------------------------------------------------------------
	public DataItem lpDelete(String key)  // delete a DataItem with linear probing
	   {
		int hashVal = hashFunc(key);  // hash the key
		   DataItem holder = new DataItem(key);
		   holder.pl = 1;

		   while(hashArray[hashVal] != null) 
		   {   
			   
			   if(hashArray[hashVal].getKey().equals(key)) {
				   //Deleted items have a key of "-1"
				   hashArray[hashVal].s = "-1"; //Set this DataItem's string to -1 to indicate it was deleted.
				   holder.success = true; // If deleted, set to true
				   return holder;   
			   }
		      ++hashVal;                 // go to next cell
		      hashVal %= arraySize;      // wraparound if necessary
		      holder.pl++; //Increment probeLength for each loop
		      }
		   holder.success = false; // If not deleted, set to false
		   return holder;                  
	   }  // end lpDelete()
	//-------------------------------------------------------------
	public DataItem qpDelete(String key)  // delete a DataItem with quadratic probing
	   {
		int hashVal = hashFunc(key);  // hash the key
		   DataItem holder = new DataItem(key);
		   holder.pl = 1;
		   int i = 1;

		   while(hashArray[hashVal] != null) 
		   {   
			   
			   if(hashArray[hashVal].getKey().equals(key)) {
				   //Deleted items have a key of "-1"
				   hashArray[hashVal].s = "-1"; //Set this DataItem's string to -1 to indicate it was deleted.
				   holder.success = true; // If deleted, set to true
				   return holder;   
			   }
			  hashVal = hashVal + i*i;                 // go to next cell
			  i++; //Increment i for quadratic probing
		      hashVal %= arraySize;      // wraparound if necessary
		      holder.pl++; //Increment probeLength for each loop
		      }
		   holder.success = false; // If not deleted, set to false
		   return holder;                  
	   }  // end qpDelete()
	//-------------------------------------------------------------
	public DataItem lpFind(String key)    // find item with key with linear probing
	   {
	   int hashVal = hashFunc(key);  // hash the key
	   DataItem holder = new DataItem(key);
	   holder.pl = 1;

	   while(hashArray[hashVal] != null) //search until null, skipping deleted (-1) items
	   {   
		   
		   if(hashArray[hashVal].getKey().equals(key)) {
			   holder.success = true; // If found, set to true
			   return holder;   
		   }
	      ++hashVal;                 // go to next cell
	      hashVal %= arraySize;      // wraparound if necessary
	      holder.pl++; //Increment probeLength for each loop
	      }
	   holder.success = false; // If not found, set to false
	   return holder;                  
	   }
	//------------------------------ -------------------------------
	public DataItem qpFind(String key)    // find item with key with quadratic probing
	   {
	   int hashVal = hashFunc(key);  // hash the key
	   DataItem holder = new DataItem(key);
	   holder.pl = 1;
	   int i = 1;

	   while(hashArray[hashVal] != null) //search until null, skipping deleted (-1) items
	   {   
		   
		   if(hashArray[hashVal].getKey().equals(key)) {
			   holder.success = true; // If found, set to true
			   return holder;   
		   }
		   hashVal = hashVal + i*i;                 // go to next cell
		   i++; //Increment i for quadratic probing
		   hashVal %= arraySize;      // wraparound if necessary
		   holder.pl++; //Increment probeLength for each loop
	      }
	   holder.success = false; // If not found, set to false
	   return holder;                  
	   }
	//------------------------------ -------------------------------
	public float avgPL() {
		
		float sum=0;
		float items=0;
		
		for(int i=0; i<hashArray.length; i++) {
			if(hashArray[i] != null && hashArray[i].getKey() != "-1") {
				sum+=hashArray[i].pl;
				items++;
			}
		}
		return sum/=items;
	}
}  // end class HashTable

public class n00436223 {

	public static void main(String[] args) throws FileNotFoundException {
		
		java.io.File file1 = new java.io.File(args[0]);
		java.io.File file2 = new java.io.File(args[1]);
		java.io.File file3 = new java.io.File(args[2]);
		
		Scanner in1 = new Scanner (file1);
		Scanner in2 = new Scanner (file2);
		Scanner in3 = new Scanner (file3);
		
		String temp1 = in1.useDelimiter("\\Z").next();
		String temp2 = in2.useDelimiter("\\Z").next();
		String temp3 = in3.useDelimiter("\\Z").next();
		
		int n=countStrings(temp1);
		int p=findPrime(n);
		
		HashTable a = new HashTable(p, 'A');
		HashTable b = new HashTable(p, 'B');
		
		Scanner inA = new Scanner (temp1);
		
		for(int i=0; i<p; i++) {
			if(inA.hasNext()) {
				a.lpInsert(inA.next()); //insert into A with linear probing
			}
		}
		inA.close();
		
		Scanner inB = new Scanner (temp1);
				
		for(int i=0; i<p; i++) {
			if(inB.hasNext())
				b.qpInsert(inB.next()); //insert into B with quadratic probing
		}
		inB.close();
		
		a.displayInsertTable(); //Linear probing insertion table (A)
		b.displayInsertTable(); //Quadratic probing insertion table (B)
		
		createFindTable(a, temp2, true); //search for items from 2nd file in A with linear probing and print table
		createFindTable(b, temp2, false); //in B with quadratic probing
		
		createDeleteTable(a, temp3, true); //delete items in 3rd file from A with linear probing
		createDeleteTable(b, temp3, false); //in B with quadratic probing
		
		
		in1.close();
		in2.close();
		in3.close();
		

		

		
		
	}
	public static void createFindTable(HashTable hashTable, String list, boolean probetype) {
		
		System.out.println("Search table for " + hashTable.title + ": ");
		System.out.printf("%-40s %s  %s  %s  %s\n", "String", "Success", "Failure", 
				"Probe length for success", "Probe length for failure");
		
		int n = countStrings(list);
		float ssum = 0;
		float fsum = 0;
		float nums = 0;
		float numf = 0;

		Scanner inTemp = new Scanner (list);
		String s;
		
	   for(int j=0; j<n; j++)
	      {
		   if(inTemp.hasNext()) {
			   s = inTemp.next();
			   DataItem item = new DataItem(null); //create DataItem as a temporary holder
			   
			   if(probetype) { //with linear probing
				   item = hashTable.lpFind(s);  
			   }
			   else { //with quadratic probing
				   item = hashTable.qpFind(s);  
			   }
			   
			   System.out.printf("%-40s ", s);
			   if(item.success) { //success stores whether s has been found
				   System.out.printf("%-7s  %-7s  ", "yes", " ");
				   System.out.printf("%d\n", item.pl); //probe length for success
				   ssum += item.pl; //add successful probe length to ssum
				   nums++; //increment nums to track the the # of successes
			   }
			   else {
				   System.out.printf("%-7s  %-7s  ", " ", "yes");
				   System.out.printf("%-24s  %d\n", " ", item.pl); //probe length for failure
				   fsum += item.pl; //add failed probe length to fsum
				   numf++; //increment numf to track the the # of failures
			   }
		   }
	      }
	   inTemp.close();
	   //Print average success and failure
	   System.out.printf("%-40s %-7s %-7s   %.2f %-19s  %.2f\n\n", "Average probe length: ", " ", " ", 
			   ssum/=nums, " ", fsum/=numf);
	   }
	public static void createDeleteTable(HashTable hashTable, String list, boolean probetype) {
		
		System.out.println("Deletion table for " + hashTable.title + ": ");
		System.out.printf("%-40s %s  %s  %s  %s\n", "String", "Success", "Failure", 
				"Probe length for success", "Probe length for failure");
		
		int n = countStrings(list);
		float ssum = 0;
		float fsum = 0;
		float nums = 0;
		float numf = 0;

		Scanner inTemp = new Scanner (list);
		String s;
		
	   for(int j=0; j<n; j++)
	      {
		   if(inTemp.hasNext()) {
			   s = inTemp.next();
			   DataItem item = new DataItem(null); //create DataItem as a temporary holder
			   
			   if(probetype) { //delete with linear probing
				   item = hashTable.lpDelete(s);  
			   }
			   else { //delete with quadratic probing
				   item = hashTable.qpDelete(s);  
			   }
			   
			   System.out.printf("%-40s ", s);
			   if(item.success) { //success stores whether s has been deleted
				   System.out.printf("%-7s  %-7s  ", "yes", " ");
				   System.out.printf("%d\n", item.pl); //probe length for success
				   ssum += item.pl; //add successful probe length to ssum
				   nums++; //increment nums to track the the # of successes
			   }
			   else {
				   System.out.printf("%-7s  %-7s  ", " ", "yes");
				   System.out.printf("%-24s  %d\n", " ", item.pl); //probe length for delete failure
				   fsum += item.pl; //add failed probe length to fsum
				   numf++; //increment numf to track the the # of failures
			   }
		   }
	      }
	   inTemp.close();
	   //Print average success and failure
	   System.out.printf("%-40s %-7s %-7s   %.2f %-19s  %.2f\n\n", "Average probe length: ", " ", " ", 
			   ssum/=nums, " ", fsum/=numf);
	   }
	public static int countStrings(String s) {  //counts strings separated by white space
		
		int n=0;
		Scanner in = new Scanner(s);
		
		while(in.hasNext()) {
			n++;
			in.next();
		}
		in.close();
		return n;
	}
	public static int findPrime(int n) {
		
		n*=2;
		n+=1;
		int m=0;

		for(m=2; m<n; m++) {
			
			if(n%m==0) {
				n++;
				m=2;
			}
			else if(m==n-1 && n%m != 0) {
				break;
			}
		}
		return n;
	}
}
