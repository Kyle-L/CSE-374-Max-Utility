import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class State implements Comparable<State>{
    
	private State parent;
    private List<State> children;
    
    private int[] utils;
    private int maxExpectedUtil;
    private Action action = null;

    /**
     * Creates an instance of state.
     * @param action what action results by moving into this state.
     * @param utils the utility from the action.
     */
    public State(Action action, int[] utils) {
        this.action = action;
        this.utils = utils;
        maxExpectedUtil = Integer.MIN_VALUE;
        children = new ArrayList<>();
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
    	return utils;
    }
    
    /**
     * Returns the summation of all utilities.
     * @return
     */
    public int getUtilitySum () {
    	return IntStream.of(utils).sum();
    }
    
    /**
     * Sets the utilities for this state.
     * @param utility
     */
    public void setUtility (int[] utils) {
    	this.utils = utils;
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
}