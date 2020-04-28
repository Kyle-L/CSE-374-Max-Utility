import java.util.List;

public class main {	
	
	public static void main (String[] args) {
		// Variables for measuring execution time for finding the max utility.
		long start = 0;
		long end = 0;
		List<State> path = null;
		
		/*======================================= EXAMPLE 1 ===============================================*/
		
		System.out.println("===================================================================");
		System.out.println("                        EXAMPLE 1 : Table 2");
		System.out.println("===================================================================");
		System.out.println("Here is our max utility path algorithm running on the \nexample provided "
						 + " in table 2 of our paper.");
		System.out.println("===================================================================");
		System.out.println();
		
		// Builds the example tree.
		State root = buildExampleTree();
		
		// Finds the max utility path and the time.
		start = System.nanoTime();
		path = MaxUtilityPath.findMaxUtilityPath(root);
		end = System.nanoTime();

		// Displays the path.
		System.out.println("This actions are taken by visiting:");
		for (State state : path) {
			state.getAction().Go();
		}

		//Displays the max utility found as well as the time taken to compute that.
		System.out.println("\nThe resulting utility is " + path.get(path.size() - 1).getExpectedUtility() + ".");
		System.out.format("This was computed in %d nanoseconds.\n\n", end-start);
		
		/*======================================= EXAMPLE 2 ===============================================*/
		
		System.out.println("===================================================================");
		System.out.println("                        EXAMPLE 2 : Rideshare");
		System.out.println("===================================================================");
		System.out.println("Here is our max utility path algorithm running a randomized\n"
						 + "rideshare example in which a driver has to visit 3 locations.\n"
						 + "A ride-share driver must reach three locations.");
		System.out.println("===================================================================");
		System.out.println();
		
		// Builds the rideshare tree.
		RideShare rideShareTree = buildRideShareTree(2, 2, 0, 10, 1); 
		
		// Finds the max utility path and the time.
		start = System.nanoTime();
		path = MaxUtilityPath.findMaxUtilityPath(rideShareTree.getInitialState());
		end = System.nanoTime();

		
		// Displays the path actions.
		System.out.println("Actions taken by the first driver to maximize utility:");	
		for (State state : path) {
			state.getAction().Go();
		}
		
		//Displays the max utility found as well as the time taken to compute that.
		System.out.println("\nThe resulting utility is " + path.get(path.size() - 1).getExpectedUtility() + ".");
		System.out.format("This was computed on average in %d nanoseconds.\n\n", end-start);
		
/*======================================= EXAMPLE 2 ===============================================*/
		
		System.out.println("===================================================================");
		System.out.println("                        EXAMPLE 3 : Rideshare 2");
		System.out.println("===================================================================");
		System.out.println("Here is our max utility path algorithm running a randomized\n"
						 + "rideshare example in which a driver has to visit 5 locations.\n"
						 + "A ride-share driver must reach five locations.");
		System.out.println("===================================================================");
		System.out.println();
		
		// Builds the rideshare tree.
		rideShareTree = buildRideShareTree(4, 2, -5, 5, 1); 
		
		// Finds the max utility path and the time.
		start = System.nanoTime();
		path = MaxUtilityPath.findMaxUtilityPath(rideShareTree.getInitialState());
		end = System.nanoTime();

		
		// Displays the path actions.
		System.out.println("Actions taken by the first driver to maximize utility:");	
		for (State state : path) {
			state.getAction().Go();
		}
		
		//Displays the max utility found as well as the time taken to compute that.
		System.out.println("\nThe resulting utility is " + path.get(path.size() - 1).getExpectedUtility() + ".");
		System.out.format("This was computed on average in %d nanoseconds.\n\n", end-start);
	}
	
	public static RideShare buildRideShareTree (int locationNum, int numUtil, int lower, int upper, int seed) {
		// Creates a RideShare object that has 3 possible locations, with 3 utility assigned to each location.
		RideShare rideShare = new RideShare(locationNum, numUtil, lower, upper, seed);
		
		System.out.println("Possible paths for the driver:\n" + rideShare);
		
		return rideShare;
	}
	
	public static State buildExampleTree () {
		State parentState = new State("Start State", new StringAction("Start at empty state."), new int[] {0, 0}); 

		State childState1 = new State("State 1", new StringAction("Visit State 1"), new int[] {7, -5});
		State childState2 = new State("State 2", new StringAction("Visit State 2"), new int[] {-2, 10});
		State childState3 = new State("State 3", new StringAction("Visit State 3"), new int[] {5, -6});

		parentState.addChild(childState1);
		parentState.addChild(childState2);
		parentState.addChild(childState3);		

		State childState4 = new State("State 4", new StringAction("Visit State 4"), new int[] {-10, 5});
		State childState5 = new State("State 5", new StringAction("Visit State 5"), new int[] {10, -5});
		State childState6 = new State("State 6", new StringAction("Visit State 6"), new int[] {4, 3}); 

		childState1.addChild(childState4);
		childState1.addChild(childState5);
		childState1.addChild(childState6);

		childState2.addChild(childState4);
		childState2.addChild(childState5);
		childState2.addChild(childState6);

		childState3.addChild(childState4);
		childState3.addChild(childState5);
		childState3.addChild(childState6);

		State childState7 = new State("State 7", new StringAction("Visit State 7"), new int[] {1, -5});
		State childState8 = new State("State 8", new StringAction("Visit State 8"), new int[] {5, 5});
		State childState9 = new State("State 9", new StringAction("Visit State 9"), new int[] {-3, 7}); 
		
		childState4.addChild(childState7);
		childState4.addChild(childState8);
		childState4.addChild(childState9);

		childState5.addChild(childState7);
		childState5.addChild(childState8);
		childState5.addChild(childState9);

		childState6.addChild(childState7);
		childState6.addChild(childState8);
		childState6.addChild(childState9);
		
		return parentState;
	}
	
}
