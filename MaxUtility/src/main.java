import java.util.List;

public class main {
	public static void main (String[] args) {
		State root = buildTree();
				
		List<State> path = MaxUtilityPath.Start(root);
		
		for (State state : path) {
			state.getAction().Go();
		}
	}
	
	public static State buildTree () {
		State parentState = new State(new StringAction("0"), new int[] {0}); 
		
		State childState1 = new State(new StringAction("1"), new int[] {2});
		State chilState2 = new State(new StringAction("2"), new int[] {4});
		State childState3 = new State(new StringAction("3"), new int[] {3});
		
		parentState.addChild(childState1);
		parentState.addChild(chilState2);
		parentState.addChild(childState3);		
		
		State childState4 = new State(new StringAction("4"), new int[] {4});
		State childState5 = new State(new StringAction("5"), new int[] {3});
		State childState6 = new State(new StringAction("6"), new int[] {2}); 

		childState1.addChild(childState4);
		childState1.addChild(childState5);
		childState1.addChild(childState6);
		
		chilState2.addChild(childState4);
		chilState2.addChild(childState5);
		chilState2.addChild(childState6);
		
		childState3.addChild(childState4);
		childState3.addChild(childState5);
		childState3.addChild(childState6);
		
		return parentState;
	}

}