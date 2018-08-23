import java.io.*;
import java.util.*;

//Modified priorityQ.java from LaFore
class PriorityQ
{
// array in sorted order, from max at 0 to min at size-1
private int maxSize;
private Tree[] queArray;
protected int nItems;
//-------------------------------------------------------------
public PriorityQ(int s)          // constructor
   {
   maxSize = s;
   queArray = new Tree[maxSize];
   nItems = 0;
   }
//-------------------------------------------------------------
public void insert(Tree item)    // insert item
   {
   int j;

   if(nItems==0)                         // if no items,
      queArray[nItems++] = item;         // insert at 0
   else                                // if items,
      {
      for(j=nItems-1; j>=0; j--)         // start at end,
         {
         if( item.root.iData > queArray[j].root.iData )      // if new item larger,
            queArray[j+1] = queArray[j]; // shift upward
         else                          // if smaller,
            break;                     // done shifting
         }  // end for
      queArray[j+1] = item;            // insert it
      nItems++;
      }  // end else (nItems > 0)
   }  // end insert()
//-------------------------------------------------------------
public Tree remove()             // remove minimum item
   { return queArray[--nItems]; }
//-------------------------------------------------------------
public Tree peekMin()            // peek at minimum item
   { return queArray[nItems-1]; }
//-------------------------------------------------------------
}  // end class PriorityQ

//Modified tree.java from LaFore
class Node
   {
   public int iData;              // frequency for this char
   public char cData;           // char for this node
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child
   public Node parent; 			//parent of this node

   public void displayNode()      // display ourself
      {
      System.out.print('{');
      System.out.print(cData);
      System.out.print(", ");
      System.out.print(iData);
      System.out.print("} ");
      }
   }  // end class Node
////////////////////////////////////////////////////////////////
class Tree
   {
   protected Node root;             // first node of tree

// -------------------------------------------------------------
   public Tree()                  // constructor
      { root = null; }            // no nodes in tree yet
   
   //traverse takes a binary string "path" and traverses the tree, concatenating
   //leaf chars to x as they are found, then returning to root and starting over,
   //until the end of the path string is reached.
   public String traverse(String path) {
	   
	   String x = new String();
	   Node current = root;
	   
	   for(int i=0; i<path.length(); i++) {
			if(path.charAt(i)=='0')
				current = current.leftChild;
			if(path.charAt(i)=='1')
				current = current.rightChild;
			if(current.leftChild == null && current.rightChild == null) {
				x += current.cData;
				current = root;
			}
	   }
	   return x;
   }
// -------------------------------------------------------------
   public void insert(char cd, int id)
      {
      Node newNode = new Node();    // make new node
      newNode.iData = id;           // insert data
      newNode.cData = cd;
      if(root==null)                // no node in root
         root = newNode;
      else                          // root occupied
         {
         Node current = root;       // start at root
         Node parent;
         while(true)                // (exits internally)
            {
            parent = current;
            if(id < current.iData)  // go left?
               {
               current = current.leftChild;
               if(current == null)  // if end of the line,
                  {                 // insert on left
                  parent.leftChild = newNode;
                  return;
                  }
               }  // end if go left
            else                    // or go right?
               {
               current = current.rightChild;
               if(current == null)  // if end of the line
                  {                 // insert on right
                  parent.rightChild = newNode;
                  return;
                  }
               }  // end else go right
            }  // end while
         }  // end else not root
      }  // end insert()
// -------------------------------------------------------------
   //getLeafs puts leafs in the Paths static array nodes,
   //gets the strings of the paths to each leaf with getPath,
   //and lastly puts them in the Paths static array strings.
   public void getLeafs(Node localRoot) {
	  //If there are 2 child nodes, run getLeafs for both
	  if(localRoot.leftChild != null && localRoot.rightChild != null) {
		  getLeafs(localRoot.leftChild);
		  getLeafs(localRoot.rightChild);
	  }
	  //If there is only a leftChild, run getLeafs for that
	  else if (localRoot.leftChild != null) {
		  getLeafs(localRoot.leftChild);
	  }
	  //and so on.
	  else if (localRoot.rightChild != null) {
		  getLeafs(localRoot.rightChild);
	  }
	  //If there are no child nodes...
	  else if (localRoot.leftChild == null && localRoot.rightChild == null) {
		  for(int i=0; i<7; i++) {
			  //Find Nodes in NodePaths that haven't been changed yet 
			  if(NodePaths.nodes[i].cData == ' ') {
				  //Add leafs to the static nodes array as they are found
				  NodePaths.nodes[i] = localRoot;
				  //Add strings of the paths to these leafs to the static
				  //strings array
				  NodePaths.strings[i] = getPath(NodePaths.nodes[i]);
				  break;
			  }
		  }
	  }
   }
   //getPath returns a string of the path to a node
   public String getPath(Node localRoot) {
	   
	   String pathback = new String();
	   //Check to see if localRoot has a parent. If parent.cData is ' ', that
	   //means that node is the root of the Huffman tree.
	   while(localRoot.parent.cData != ' ') {
		   //If localRoot is the leftChild of its parent, a 0 is concatenated
		   //to pathback
		   if(localRoot.parent.leftChild==localRoot) {
			   pathback += "0";
			   localRoot = localRoot.parent;
		   }
		   //Likewise:
		   if(localRoot.parent.rightChild==localRoot) {
			   pathback += "1";
			   localRoot = localRoot.parent;
		   }
	   }
	   //pathback is backwards since the tree was traversed backwards to get
	   //it. Reverse pathback and put it in path.
	   String path = new String();
	   path = new StringBuffer(pathback).reverse().toString();
	   return path;
   }
   //setParents goes through a tree recursively and sets each
   //node's parent field to its parent node.
   public void setParents(Node localRoot) {
	   //If a node has 2 child nodes, set their parents to localRoot
	   if(localRoot.leftChild != null && localRoot.rightChild != null) {
			  localRoot.leftChild.parent=localRoot;
			  localRoot.rightChild.parent=localRoot;
			  setParents(localRoot.leftChild);
			  setParents(localRoot.rightChild);
			  }
	   //If a node only has a leftChild, set that node to localRoot
	   else if (localRoot.leftChild != null) {
			  localRoot.leftChild.parent=localRoot;
			  setParents(localRoot.leftChild);
			  }
	   //Likewise:
	   else if (localRoot.rightChild != null) {
			  localRoot.rightChild.parent=localRoot;
			  setParents(localRoot.rightChild);
			  }
   }
// -------------------------------------------------------------
   public void displayTree()
      {
      Stack globalStack = new Stack();
      globalStack.push(root);
      int nBlanks = 32;
      boolean isRowEmpty = false;
      System.out.println(
      "......................................................");
      while(isRowEmpty==false)
         {
         Stack localStack = new Stack();
         isRowEmpty = true;

         for(int j=0; j<nBlanks; j++)
            System.out.print(' ');

         while(globalStack.isEmpty()==false)
            {
            Node temp = (Node)globalStack.pop();
            if(temp != null)
               {
               System.out.print(temp.cData + "" + temp.iData);
               localStack.push(temp.leftChild);
               localStack.push(temp.rightChild);

               if(temp.leftChild != null ||
                                   temp.rightChild != null)
                  isRowEmpty = false;
               }
            else
               {
               System.out.print("--");
               localStack.push(null);
               localStack.push(null);
               }
            for(int j=0; j<nBlanks*2-2; j++)
               System.out.print(' ');
            }  // end while globalStack not empty
         System.out.println();
         nBlanks /= 2;
         while(localStack.isEmpty()==false)
            globalStack.push( localStack.pop() );
         }  // end while isRowEmpty is false
      System.out.println(
      "......................................................\n");
      }  // end displayTree()
// -------------------------------------------------------------
   }  // end class Tree
class NodePaths {
	//NodePaths exists mainly to store static data for recursive methods.
	//strings is an array of strings that are set by getLeafs in the Tree class.
	//Nodes in nodes are set by getLeaf as well.
	static String[] strings= {" ", " ", " ", " ", " ", " ", " "};
	static Node a = new Node();
	static Node b = new Node();
	static Node c = new Node();
	static Node d = new Node();
	static Node e = new Node();
	static Node f = new Node();
	static Node g = new Node();
	static Node[] nodes = {a, b, c, d, e, f, g};

	NodePaths() {
		//Set node cData to ' ' as default to indicate when a node hasn't been set
		for(int i=0; i<7; i++) {
			nodes[i].cData = ' ';
		}
	}
	//Print all the nodes that have been set along with their corresponding strings
	public void printTable() {
		for(int i=0; i<7; i++) {
			if(nodes[i].cData != ' ') {
				System.out.println(nodes[i].cData + " " + strings[i]);
			}
		}
		System.out.println();
	}
	//getPath in NodePaths finds the Node used by a char and then returns the
	//string that represents the path to that node.
	public String getPath(char x) {
		
		String path = new String();
		
		for(int i=0; i<7; i++) {
			if(nodes[i].cData == x) {
				path = strings[i];
				}
			}		
		return path;
	}
	
}
public class n00436223 {

	public static void main(String[] args) throws FileNotFoundException {

		//Read from the file specified in the console
		java.io.File txt = new java.io.File(args[0]);
		Scanner in = new Scanner(txt);
		int a=0, b=0, c=0, d=0, e=0, f=0, g=0;

		//Use the \\Z delimiter to read in the entire text of the file
		String temp = in.useDelimiter("\\Z").next();
		in.close();
				
		//Create a string of only A-G characters
		String ag = agString(temp);
		//Get the frequency of each A-G character
		a=countChar(ag, 'A', a);
		b=countChar(ag, 'B', b);
		c=countChar(ag, 'C', c);
		d=countChar(ag, 'D', d);
		e=countChar(ag, 'E', e);
		f=countChar(ag, 'F', f);
		g=countChar(ag, 'G', g);
				
		//Create nodes for A-G
		Node A = new Node(); Node B = new Node(); Node C = new Node();
		Node D = new Node(); Node E = new Node(); Node F = new Node();
		Node G = new Node();
		//Set their frequency and char values
		A.iData=a; A.cData='A'; B.iData=b; B.cData='B'; C.iData=c; C.cData='C'; 
		D.iData=d; D.cData='D'; E.iData=e; E.cData='E'; F.iData=f; F.cData='F'; 
		G.iData=g; G.cData='G'; 
		//Create trees for each A-G char
		Tree one = new Tree(); Tree two = new Tree(); Tree three = new Tree(); 
		Tree four = new Tree(); Tree five = new Tree(); Tree six = new Tree(); 
		Tree seven = new Tree(); 
		//Set roots for trees
		one.root = A; two.root = B; three.root = C; four.root = D; 
		five.root = E; six.root = F; seven.root = G; 
		//Create a priority queue
		PriorityQ pq = new PriorityQ(7);
		
		//Use the checkTree method to put a tree into pq if the frequency of its
		//root is > 0
		pq=checkTree(pq, one); pq=checkTree(pq, two); pq=checkTree(pq, three);
		pq=checkTree(pq, four); pq=checkTree(pq, five); pq=checkTree(pq, six);
		pq=checkTree(pq, seven);
		
		//Call reNode method with pq in order to create a Huffman tree from
		//the other trees in pq
		pq = reNode(pq);

		//Set parent fields for each node in the Huffman tree
		pq.peekMin().setParents(pq.peekMin().root);
		//Make endnode parent of the root of the Huffman tree to indicate the
		//top of the tree. endnode is not part of the Huffman tree.
		Node endnode = new Node();
		endnode.cData = ' ';
		pq.peekMin().root.parent = endnode;
		
		//Create a NodePaths object to run NodePaths methods on static arrays in that class
		NodePaths paths = new NodePaths();
		
		//Find leafs, add them to the static array of nodes in Paths,
		//then add Strings of the path to each node to the static array of Strings
		//in Paths
		pq.peekMin().getLeafs(pq.peekMin().root);

		//Display the menu
		menu(ag, paths, pq.peekMin());
	}
	//menu displays a menu and prints outputs
	public static void menu(String s, NodePaths p, Tree t) {
		
		String x = new String();
		char y;
		int z=1;
		Scanner in = new Scanner(System.in);
		
		while(z==1) {
			System.out.println("Enter a letter for one of the following options:\n"
					+ "a. Display the Huffman tree\n"
					+ "b. Display the code table\n"
					+ "c. Display the binary encoding of the A-G portion of the file\n"
					+ "d. Display the A-G portion converted back from binary encoding,\n"
					+ "\tusing the Huffman tree to decode\n"
					+ "q. Quit");
			
			x = in.next();
			y = x.charAt(0);
			
			switch(y) {
			
			case 'a':
				t.displayTree();
				break;
			case 'b':
				p.printTable();
				break;
			case 'c':
				printBits(encodeString(s, p));
				break;
			case 'd':
				System.out.println(t.traverse(encodeString(s, p)) + "\n");
				break;
			case 'q':
				z=0;
				break;
			}
		}
		in.close();
	}
	//encodeString encodes an A-G string based on encoding strings from paths
	//and returns the encoded version of that string
	public static String encodeString(String s, NodePaths paths) {
		
		String encoded = new String();
		
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)=='A')
				encoded += paths.getPath('A');
			if(s.charAt(i)=='B')
				encoded += paths.getPath('B');
			if(s.charAt(i)=='C')
				encoded += paths.getPath('C');
			if(s.charAt(i)=='D')
				encoded += paths.getPath('D');
			if(s.charAt(i)=='E')
				encoded += paths.getPath('E');
			if(s.charAt(i)=='F')
				encoded += paths.getPath('F');
			if(s.charAt(i)=='G')
				encoded += paths.getPath('G');
		}
		return encoded;
	}
	//printString prints an encoded string in the correct format
	public static void printBits(String s) {
		int j=0;
		for(int i=0; i<s.length(); i++) {
						
			if(i%8==0 && i!=0) {
				System.out.print(" ");
				j++;
				if(j%3==0 && j!=0)
					System.out.println();
			}
			System.out.print(s.charAt(i));
		}
		System.out.println("\n");
	}
	//agString converts the temp string containing every character in the file
	//into a string containing only A-G.
	public static String agString(String s) {
		
		String ag = new String();
		
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)=='A')
				ag += "A";
			if(s.charAt(i)=='B')
				ag += "B";
			if(s.charAt(i)=='C')
				ag += "C";
			if(s.charAt(i)=='D')
				ag += "D";
			if(s.charAt(i)=='E')
				ag += "E";
			if(s.charAt(i)=='F')
				ag += "F";
			if(s.charAt(i)=='G')
				ag += "G";
		}		
		return ag;		
	}
	//Renode combines all trees in pq into a Huffman tree with all A-G nodes
	//as leafs.
	public static PriorityQ reNode(PriorityQ pq) {
		
		while(pq.nItems > 1) {
			Node x = new Node();
			Node y = new Node();
			y = pq.remove().root;
			int temp = y.iData;
			x.leftChild = y;
			y = pq.remove().root;
			temp += y.iData;
			x.rightChild = y;
			x.iData=temp;
			x.cData='-';
			Tree z = new Tree();
			z.root=x;
			pq.insert(z);
		}
		return pq;
	}
	//Check to make sure the char of the initial 7 trees is actually used
	//in the text of the file
	public static PriorityQ checkTree(PriorityQ pq, Tree tree) {
		
		if(tree.root.iData > 0) {
			pq.insert(tree);
			return pq;	
		}
		else
			return pq;
	}
	//Count the frequency of chars in a string
	public static int countChar(String s, char X, int x) {
		
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)==X)
				x++;
		}
		return x;
	}
}
