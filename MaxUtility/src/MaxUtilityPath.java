import java.util.*; 

public class MaxUtilityPath{

	public static List<State> Start (State root) {
		Set<State> set = new HashSet<State>();
		PriorityQueue<State> queue = new PriorityQueue<>(root.toSet());

		// Initialization.
		initialize(root, set);
			
		// Iterate through queue until empty.
		while (!queue.isEmpty()) {
			State m = queue.poll();
			set.add(m);
			
			// Relaxation Step.
			for (State child : m.getChildren()) {
				relax(m, child);
			}
		}
		
		// Return max path.
		return getMaxPath(set);
	}
	
	private static void initialize(State root, Set<State> set) {
		root.setExpectedUtility(0);
		for (State state : set) {
			state.setExpectedUtility(Integer.MIN_VALUE);
			state.setParent(null);
		}
	}
	
	private static void relax (State m, State s) {
		if (s.getExpectedUtility() < m.getExpectedUtility() + s.getUtilitySum()) {
			s.setExpectedUtility(m.getExpectedUtility() + s.getUtilitySum());
			s.setParent(m);
		}
	}
	
	private static List<State> getMaxPath (Set<State> nodes) {
		List<State> orderedSet = new ArrayList<State>();
		int[] empty = { };
		State max = new State(null, empty);
		for (State node : nodes) {
			if (node.isLeaf()) {
				max = (node.getExpectedUtility() > max.getExpectedUtility()) ? node : max;
			}
		}
		while (max != null) {
			orderedSet.add(0, max);
			max = max.getParent();
		}

		return orderedSet;
	}
	
}
