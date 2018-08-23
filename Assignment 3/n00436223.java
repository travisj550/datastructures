//Assignment 3
//Travis Jones N00436223
//Partially based on Lafore interIterator.java
import java.io.*;
import java.util.Scanner;
////////////////////////////////////////////////////////////////
class Link
{
public int dData;             // data item
public Link next;              // next link in list
//-------------------------------------------------------------
public Link(int dd)           // constructor
   { dData = dd; }
//-------------------------------------------------------------
public void displayLink()      // display ourself
   { System.out.print(dData + " "); }
}  // end class Link
////////////////////////////////////////////////////////////////
class ListIterator
{
private Link current;          // current link
//--------------------------------------------------------------
public ListIterator(Link initial) // constructor
   {
	initial.next = initial;
   current = initial;
   }
//--------------------------------------------------------------
public boolean atEnd()         // true if last link
   { return (current.next==null); }
//--------------------------------------------------------------
public void nextLink()         // go to next link
   {
   current = current.next;
   }
//--------------------------------------------------------------
public Link getCurrent()       // get current link
   { return current; }
//--------------------------------------------------------------
public void insert(int dd)     // insert after
   {                                 // current link
   Link newLink = new Link(dd);

      newLink.next = current.next;
      current.next = newLink;
      nextLink();              // point to new link

   }
//--------------------------------------------------------------
public void deleteNext()    // delete item at current.next
   {
      current.next = current.next.next;
   }
//--------------------------------------------------------------
}  // end class ListIterator
////////////////////////////////////////////////////////////////
class n00436223
{
public static void main(String[] args) throws IOException
   {
   int total=0;
   int start=0;
   int passing=0;
   
   Scanner in = new Scanner(System.in); 
   
   while(true) {
		System.out.println("Enter the number of prisoners, the starting"
				+ " prisoner's number, \nand the passing number (with spacing,"
				+ " respectively). \n"
				+ "Type \"stop\" to quit. ");

		if(in.hasNext("stop"))
			break;
		if(in.hasNextInt())
			total=in.nextInt();
		if(in.hasNextInt())
			start=in.nextInt();
		if(in.hasNextInt())
			passing=in.nextInt();	
		else {
			System.out.println("Invalid input.");
			in.next();
			continue;
		}

		Link initial = new Link(1);
		ListIterator iter = new ListIterator(initial);
      
		for(int i=2; i<=total; i++) {	
			iter.insert(i);
			}
		
		iter.getCurrent().next = initial;

		findStart(iter, start);

		killPrisoners(iter, passing);

		System.out.println("The last prisoner is number " + iter.getCurrent().dData + ".");
   }
   }
public static void findStart(ListIterator iter, int start) {

	while(iter.getCurrent().next.dData != start) {
		iter.nextLink();
	}
}
public static void killPrisoners(ListIterator iter, int passing) {
	
	while(iter.getCurrent().next != iter.getCurrent()) {
		
		for(int i=0; i<passing; i++) {
			iter.nextLink();
		}
		iter.deleteNext();
	}
}


}