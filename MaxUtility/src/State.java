import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class State<T> implements Comparable<State<T>>{
    private State<T> parent;
    private List<State<T>> children = new ArrayList<State<T>>();
    
    private int util;
    private int maxExpectedUtil;
    private T value = null;

    /**
     * Creates an instance of state with a starting value.
     * @param value is what the state's starting value is.
     */
    public State(T value, int util) {
        this.value = value;
        this.util = util;
        maxExpectedUtil = Integer.MIN_VALUE;
    }

    /**
     * Returns all states that are children of this state.
     * @return
     */
    public List<State<T>> getChildren() {
        return children;
    }

    /**
     * Adds a child to the current state.
     * @param child
     */
    public void addChild(State<T> child) {
        this.children.add(child);
    }
    
    /**
     * Returns the parent of this state.
     * @return
     */
    public State<T> getParent() {
    	return parent;
    }
    
    /**
     * Sets the parent of this state.
     * @param parent
     */
    public void setParent(State<T> parent) {
    	this.parent = parent; 
    }
    
    /**
     * Returns the  utility for this state.
     * @return
     */
    public int getUtility () {
    	return util;
    }
    
    /**
     * Sets the utility for this state.
     * @param utility
     */
    public void setUtility (int util) {
    	this.util = util;
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
     * Returns the value of this state.
     * @return
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Sets the value of this state.
     * @param value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Returns whether this state is leaf or not.
     * @return True if it is a leaf and false otherwise.
     */
    public boolean isLeaf() {
        return this.children.size() == 0;
    }

	public Set<State<T>> toSet () {
		Set<State<T>> set = new HashSet<State<T>>();
		toSet(set);
		return set;
	}
	
	private void toSet (Set<State<T>> set) {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).toSet(set);
		}
		set.add(this);
	}

	@Override
	public int compareTo(State<T> o) {
		if (o.maxExpectedUtil > this.maxExpectedUtil) return 1;
		if (o.maxExpectedUtil < this.maxExpectedUtil) return -1;
		return 0;
	}
}