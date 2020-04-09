import java.util.PriorityQueue;

public class main {
	public static void main (String[] args) {

		State<Integer> parentNode = new State<Integer>(0, 0); 
		
		State<Integer> childNode1 = new State<Integer>(1, 2);
		State<Integer> childNode2 = new State<Integer>(2, 4);
		State<Integer> childNode3 = new State<Integer>(3, 3);
		
		parentNode.addChild(childNode1);
		parentNode.addChild(childNode2);
		parentNode.addChild(childNode3);		
		
		State<Integer> childNode4 = new State<Integer>(4, 4);
		State<Integer> childNode5 = new State<Integer>(5, 3);
		State<Integer> childNode6 = new State<Integer>(6, 2); 

		childNode1.addChild(childNode4);
		childNode1.addChild(childNode5);
		childNode1.addChild(childNode6);
		
		childNode2.addChild(childNode4);
		childNode2.addChild(childNode5);
		childNode2.addChild(childNode6);
		
		childNode3.addChild(childNode4);
		childNode3.addChild(childNode5);
		childNode3.addChild(childNode6);
				
		MaxUtilityPath<Integer> test = new MaxUtilityPath<Integer>();
		test.Start(parentNode);
	}
	
	

}
