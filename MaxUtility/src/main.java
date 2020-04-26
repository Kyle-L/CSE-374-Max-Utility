import java.util.List;

public class main {
	public static void main (String[] args) {
		/*
		State root = buildTree();

		List<State> path = MaxUtilityPath.findMaxUtilityPath(root);

		System.out.println("With the max utility determined. " +  	
						   "The actions that should be taken are:");	

		for (State state : path) {
			state.getAction().Go();
		}

		System.out.println("\nThis actions are taken by visiting:");

		for (State state : path) {
			System.out.println(state.getStateName());
		}

		System.out.println("\nThe resulting utility is " + path.get(path.size() - 1).getExpectedUtility() + ".");
		 */

		// =================================================================================================================  Testing Ride Share Class
		RideShare driverOne = new RideShare(2, 1, -10, -1);
		

		System.out.println("Path 1");
		System.out.println((driverOne.getInitialState().getChildren().get(0).getStateName()));
		int [] u = (driverOne.getInitialState().getChildren().get(0).getUtilities());
		System.out.println(u[0]);
		
		System.out.println((driverOne.getInitialState().getChildren().get(0).getChildren().get(0).getStateName()));
		u = (driverOne.getInitialState().getChildren().get(0).getChildren().get(0).getUtilities());
		System.out.println(u[0]);

		
		System.out.println("\nPath 2");
		System.out.println((driverOne.getInitialState().getChildren().get(1).getStateName()));
		u = (driverOne.getInitialState().getChildren().get(1).getUtilities());
		System.out.println(u[0]);
		
		System.out.println((driverOne.getInitialState().getChildren().get(1).getChildren().get(0).getStateName()));
		u = (driverOne.getInitialState().getChildren().get(1).getChildren().get(0).getUtilities());
		System.out.println(u[0]);
		System.out.println();
		
		List<State> path = MaxUtilityPath.findMaxUtilityPath(driverOne.getInitialState());

		System.out.println("The actions that should be taken are:");	

		for (State state : path) {
			state.getAction().Go();
		}
		
		
	}

	public static State buildTree () {
		State parentState = new State("Root State", new StringAction("0"), new int[] {0}); 

		State childState1 = new State("State 1", new StringAction("1"), new int[] {2});
		State chilState2 = new State("State 2", new StringAction("2"), new int[] {4});
		State childState3 = new State("State 3", new StringAction("3"), new int[] {3});

		parentState.addChild(childState1);
		parentState.addChild(chilState2);
		parentState.addChild(childState3);		

		State childState4 = new State("State 4", new StringAction("4"), new int[] {4});
		State childState5 = new State("State 5", new StringAction("5"), new int[] {3});
		State childState6 = new State("State 6", new StringAction("6"), new int[] {2}); 

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
