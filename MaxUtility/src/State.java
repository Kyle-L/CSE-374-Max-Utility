import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class State implements Comparable<State>{
    
	private String stateName;
	private State parent;
    private List<State> children;
    
    private int[] utilities;
    private int maxExpectedUtil;
    private Action action = null;

    /**
     * Creates an instance of state.
     * @param action what action results by moving into this state.
     * @param utilities the utilities gained from an action.
     */
    public State(String stateName, Action action, int[] utilities) {
    	this.setStateName(stateName);
        this.action = action;
        this.utilities = utilities;
        maxExpectedUtil = Integer.MIN_VALUE;
        children = new ArrayList<>();
    }
    
    /**
     * Copies another instance of state onto the current instance (only copies the name and action). 
     * @param copy - The state to be copied from.
     */
    public State(State copy, int[] utilities) {
    	this.setStateName(copy.getStateName());
    	this.setAction(copy.getAction());
    	this.utilities = utilities; 
    	maxExpectedUtil = Integer.MIN_VALUE;
    	children = new ArrayList<>();
    }
    
	/**
	 * Gets the state's name.
	 * @return
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * Sets the state's name.
	 * @param stateName
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

    /**
     * Returns all states that are children of this state.
     * @return
     */
    public List<State> getChildren() {
        return children;
    }

    /**
     * Adds a child to the current state.
     * @param child
     */
    public void addChild(State child) {
        this.children.add(child);
    }
    
    /**
     * Returns the parent of this state.
     * @return
     */
    public State getParent() {
    	return parent;
    }
    
    /**
     * Sets the parent of this state.
     * @param parent
     */
    public void setParent(State parent) {
    	this.parent = parent; 
    }
    
    /**
     * Returns the  utilities for this state.
     * @return
     */
    public int[] getUtilities () {
    	return utilities;
    }
    
    /**
     * Returns the summation of all utilities.
     * @return
     */
    public int getUtilitySum () {
    	return IntStream.of(utilities).sum();
    }
    
    /**
     * Sets the utilities for this state.
     * @param utility
     */
    public void setUtility (int[] utils) {
    	this.utilities = utils;
    }
    
    /**
     * Returns the maximum expected utility for this state.
     * @return
     */
    public int getExpectedUtility () {
    	return maxExpectedUtil;
    }
    
    /**
     * Sets the maximum expected utility for this state.
     * @param utility
     */
    public void setExpectedUtility (int utility) {
    	maxExpectedUtil = utility;
    }

    /**
     * Returns the action of this state.
     * @return
     */
    public Action getAction() {
        return this.action;
    }

    /**
     * Sets the action of this state.
     * @param value
     */
    public void setAction(Action value) {
        this.action = value;
    }

    /**
     * Returns whether this state is leaf or not.
     * @return True if it is a leaf and false otherwise.
     */
    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    /**
     * Converts the state and all children into an unordered set.
     * @return
     */
	public Set<State> toSet () {
		Set<State> set = new HashSet<>();
		toSet(set);
		return set;
	}
	
	/**
     * Converts the state and all children into an unordered set.
	 * @param set
	 */
	private void toSet (Set<State> set) {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).toSet(set);
		}
		set.add(this);
	}

	@Override
	public int compareTo(State o) {
		if (o.maxExpectedUtil > this.maxExpectedUtil) return 1;
		if (o.maxExpectedUtil < this.maxExpectedUtil) return -1;
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		
		if (o == this)
			return true;
		
		if(!(o instanceof State))
			return false;
		
		State s = (State) o;
		
		return s.getStateName().equals(this.getStateName());
	}
	
}