import java.util.Scanner;
import java.util.LinkedList;

class KnapSack {
	
	private LinkedList solution;
	private LinkedList remaining;
	private LinkedList temp;
	private LinkedList temp2;
	private int target;
	int numTop;
	
	public KnapSack() {
		
		solution = new LinkedList();
		remaining = new LinkedList();
		temp = new LinkedList();
		temp2 = new LinkedList();
	}
	//Start will call kp with private LinkedLists in this KnapSack object
	public void start() {
		
		//Copy remaining over to temp2
		for(int i = remaining.size()-1; i >= 0 ; i--) {
			
			temp2.push((int) remaining.get(i));
		}
		
		kp(target, solution, temp2);
	}

	//createArrays  will take input from the console and push to LinkLists
	//LinkedLists are used here as stacks and also "arrays" by using the
	//indexOf method in the LinkedList class
	public void createLists() {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Please enter the target weight followed by the "
				+ "weight of each item, separated by spacing:\n");
		String data = in.nextLine();
		
		in = new Scanner(data);

		target = 0;
		
		while(true) {
			if(target == 0)
				target=in.nextInt();
			else if(in.hasNextInt()) {
				int temp = in.nextInt();
				remaining.push(temp);
			}
			else
				break;
		}		
	}
	//Recursive method for finding solutions
	public void kp(int targ, LinkedList solut, LinkedList remain) {
		
		//A solution leaf node has been found
		//Solutions are printed as they are found
		if(targ == 0) {
			for(int i=0; i<solut.size(); i++) {
				System.out.print(solut.get(i) + " ");
			}
			System.out.print("\n");
		}
		//A non-solution leaf node has been found
		if(targ != 0 && remain.size() == 0) {

		}
		//If this node leads to a dead end
		if(targ < 0) {
			//Remove it from solut and call kp again
			solut.remove();
			//Call kp with remain since that node has already
			//been popped from remain
			kp(targ, solut, remain);
		}
		//A non-leaf node has been found
		//Continue...
		if(targ > 0 && remain.size() > 0) {
			
			//If this node leads to a dead end...
			if( ( targ - (int) remain.peek() ) < 0) {
				//Call kp without adding that item to solut
				//or substracting from targ
				remain.pop();
				kp(targ, solut, remain);

			}
			//Otherwise, add it to solut
			else {
				//...may be part of a solution
				
				//Backup target
				int targB = targ;
				//Backup "index 0" or the top of the "stack", which is also the first
				//item in this LinkedList
				int top = (int) remain.peek();
				//"Index" relative to end of list
				int indexOfTop = remain.size()-1-remain.indexOf(top);
				//Adjust target
				targ = targ - (int) remain.peek();

				//Insert that node into solution
				solut.push(remain.pop());
				
				//Copy ints in remain over to temp
				int size = remain.size();
				//Call kp recursively with new solut and targ
				kp(targ, solut, remain);
				solut.pop();
				//This is to solve duplicate weights error...
				indexOfTop = remaining.size() - 1 - indexOfTop;
				
				for(int i = remaining.size()-1; i > indexOfTop; i--) {
						temp.push((int) remaining.get(i));
					}	
				//After reaching a dead end or a leaf, call kp again with temp
				kp(targB, solut, temp);
				
			}
		}
	}
}
public class n00436223 {

	public static void main(String[] args) {
		KnapSack ks = new KnapSack();
		ks.createLists();
		System.out.println("*******************\nSolutions (if any):\n");
		ks.start();
	}
}