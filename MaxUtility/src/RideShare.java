import java.util.ArrayList;
import java.util.Random;

import javax.tools.DocumentationTool.Location;

public class RideShare {

	// The first state/location of the driver
	private State initialState;

	// Number of locations that need to be reached (this number of locations will be generated for testing)
	private int locationNumber;

	// ArrayList of all location states
	private ArrayList<State> locations = new ArrayList<State>();

	// Number of utilities for each state. 
	private int utilNumber;

	// Values for upper/lower bound for the utility value
	private int utilLowerBound;
	private int utilUpperBound;


	/**
	 * Constructs a new RideShare object.
	 * @param locationNum - Number of locations (states) generated for the tree.
	 * @param numUtil - Number of utilities for a given state. 
	 * @param lower - Lower bound for random utility values.
	 * @param upper - Upper bound for random utility values. 
	 */
	public RideShare(int locationNum, int numUtil, int lower, int upper) {
		locationNumber = locationNum;
		utilNumber = numUtil;

		// Checks that the utility value range is valid
		if(upper < lower)
			throw new IllegalArgumentException("The lower bound cannot be greater than the upper bound");

		utilLowerBound = lower;
		utilUpperBound = upper;	
		initialState = new State("Initial State", new StringAction("Initial Location"), new int [] {0});  
		generateRideShareUtilityTree();
	}


	/**
	 * Generates the locations for the tree. 
	 */
	public void generateRideShareUtilityTree() {

		// Generates the ride share locations
		generateRideShareLocations();
		
		for(int i = 0; i < locations.size(); i++) {
			State childState = new State(locations.get(i), generateUtilArray());
			initialState.addChild(childState);
			generateRideShareUtilityTree(locations, childState);
		}
			

	}
	
	public void generateRideShareUtilityTree(ArrayList<State> remaining, State parentState){
		
		// Base Case (when leaf is reached)
		if(remaining.size() == 1) {
			// System.out.println(parentState.getStateName()+"\n");
			return;
		}
			
		// Makes a copy of remaining (excluding the previous state) 
		ArrayList<State> updatedRemaining = new ArrayList<State>();
		for(State s : remaining) {
			if(s.getStateName().equals(parentState.getStateName()))
				continue;
			updatedRemaining.add(s);
		}
		
		for(int i = 0; i < updatedRemaining.size(); i++) {
			State childState = new State(updatedRemaining.get(i), generateUtilArray());
			parentState.addChild(childState);
			generateRideShareUtilityTree(updatedRemaining, childState);
			// System.out.println(parentState.getStateName()+"\nChildNum: "+childState.getChildren().size()+"\n");
		}
			
	}


	/**
	 * Creates each locations based on given parameters.
	 */
	public void generateRideShareLocations() {
		State randomState = null; 

		for(int i = 1; i <= locationNumber; i++) {
			randomState = new State("Location "+i, new StringAction("Travel to location "+i), generateUtilArray()); 
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
			util[i] = (int)(Math.random() * (utilUpperBound - utilLowerBound) + 1) + utilLowerBound;

		return util;
	}


	public State getInitialState() {
		return initialState;
	}

}
