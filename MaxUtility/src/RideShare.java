import java.util.*;

import javax.tools.DocumentationTool.Location;

/**
 * 
 * @author Matthew Coe
 *
 */
public class RideShare {
	
	// Counts the number of states;
	int stateCounter;
	
	// Generate's the random numbers used in the utility arrays.
	private Random randomGenerator;
	
	// The first state/location of the driver.
	private State initialState;

	// Number of locations that need to be reached (this number of locations will be generated for testing).
	private int locationNumber;
	
	// ArrayList of all location states.
	private ArrayList<State> locations = new ArrayList<State>();

	// Number of utilities for each state. 
	private int utilNumber;

	// Values for upper/lower bound for the utility value.
	private int utilLowerBound;
	private int utilUpperBound;
	
	// String representation of the paths in the Ride share utility tree.
	String tree = "";

	/**
	 * Constructs a new RideShare object.
	 * @param locationNum - Number of locations (states) generated for the tree.
	 * @param numUtil - Number of utilities for a given state. 
	 * @param lower - Lower bound for random utility values.
	 * @param upper - Upper bound for random utility values. 
	 */
	public RideShare(int locationNum, int numUtil, int lower, int upper) {
		this(locationNum, numUtil, lower, upper, 1);
	}
	
	/**
	 * Constructs a new RideShare object.
	 * @param locationNum - Number of locations (states) generated for the tree.
	 * @param numUtil - Number of utilities for a given state. 
	 * @param lower - Lower bound for random utility values.
	 * @param upper - Upper bound for random utility values.
	 * @param seed - The seed used for the randomly generate utility values.
	 */
	public RideShare(int locationNum, int numUtil, int lower, int upper, int seed) {
		randomGenerator = new Random();
		randomGenerator.setSeed(seed);
		
		this.stateCounter = 1;
		this.locationNumber = locationNum;
		this.utilNumber = numUtil;
		
		// Checks that the utility value range is valid.
		if(upper < lower) {
			throw new IllegalArgumentException("The lower bound cannot be greater than the upper bound");
		}
		
		this.utilLowerBound = lower;
		this.utilUpperBound = upper;	
		initialState = new State("Initial State", new StringAction("Initial Location"), new int [] {0});  
		generateRideShareUtilityTree();
	}


	/**
	 * Generates the locations for the tree. 
	 */
	public void generateRideShareUtilityTree() {

		// Generates the ride share locations.
		generateRideShareLocations();

		for(int i = 0; i < locations.size(); i++) {
			int [] values = generateUtilArray();

			// Setting child and parent states.
			State childState = new State(locations.get(i), values);
			childState.setParent(initialState);
			initialState.addChild(childState);
			
			// Recursively sets child and parent states.
			generateRideShareUtilityTree(locations, childState);
			
		}


	}


	/**
	 * Recursive helper method to generateRideShareUtilityTree(). 
	 * @param remaining - List of remaining locations that need to be visited. 
	 * @param parentState - State that will be used as the parent state. 
	 */
	public void generateRideShareUtilityTree(ArrayList<State> remaining, State parentState){

		// Base Case (when leaf is reached)
		if(remaining.size() == 1) {
			return;
		}

		// Makes a copy of remaining (excluding the previous state) 
		ArrayList<State> updatedRemaining = new ArrayList<State>();
		for(State s : remaining) {
			if(s.getAction().equals(parentState.getAction()))
				continue;
			updatedRemaining.add(s);
		}

		for(int i = 0; i < updatedRemaining.size(); i++) {
			int [] values = generateUtilArray();

			// Setting child and parent states
			State childState = new State(updatedRemaining.get(i), values);
			childState.setParent(parentState);
			parentState.addChild(childState);
			childState.setStateName("State " + stateCounter);
			stateCounter++;
			
			// Recursively sets child and parent states
			generateRideShareUtilityTree(updatedRemaining, childState);
		}

	}


	/**
	 * Creates each locations based on given parameters.
	 */
	public void generateRideShareLocations() {
		State randomState = null; 

		for(int i = 1; i <= locationNumber; i++) {
			randomState = new State("State " + stateCounter, new StringAction("Travel to location "+ i), generateUtilArray()); 
			stateCounter++;
			locations.add(randomState);
		}

	}


	/**
	 * Generates a random array of utilities based on given parameters. 
	 * @return A random array of utilities. 
	 */
	public int [] generateUtilArray() {

		int [] util = null;

		util = new int[utilNumber];

		// Adds random utilities to the list (from utilLowerBound to utilUpperBound)
		for(int i = 0; i < util.length; i++)
			util[i] = randomGenerator.nextInt(utilUpperBound - utilLowerBound) + utilLowerBound;

		return util;
	}

	
	/** 
	 * Returns a string representation of all paths in the Ride Share Tree
	 */
	@Override
	public String toString() {
		stringBuilder(initialState, "");
		return tree;
	}
	
	public void stringBuilder(State current, String path) {
		
		if(current.isLeaf()) {
			tree += path+"\n";	
			return;
		}
		
		State child = null;
		
		for(int i = 0; i < current.getChildren().size(); i++) {
			child = current.getChildren().get(i);
			String info = "[" + child.getStateName() + " | Action: " + child.getAction().toString() + " | Utility Sum: "+ child.getUtilitySum()+"]";
			
			if(!child.isLeaf())
				info += "-->";
			
			stringBuilder(child, new String(path+info));	
		}
		
		
	}

	public State getInitialState() {
		return initialState;
	}


}
