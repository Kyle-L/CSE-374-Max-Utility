import java.util.*; 
import java.lang.*; 
import java.io.*; 

public class MaxUtilityPath<T>{

	public void Start (State<T> root) {
		root.setExpectedUtility(0);
		PriorityQueue<State<T>> queue = new PriorityQueue<>(root.toSet());
		Set<State<T>> set = new HashSet<State<T>>();
		while (!queue.isEmpty()) {
			State<T> m = queue.poll();
			set.add(m);
			for (State<T> child : m.getChildren()) {
				relax(m, child);
			}
		}
		MaxPath(set);
	}
	
	private void relax (State<T> m, State<T> s) {
		if (s.getExpectedUtility() < m.getExpectedUtility() + s.getUtility()) {
			s.setExpectedUtility(m.getExpectedUtility() + s.getUtility());
			s.setParent(m);
		}
	}
	
	private void MaxPath (Set<State<T>> nodes) {
		State<T> max = new State<T>(null, Integer.MIN_VALUE);
		for (State<T> node : nodes) {
			if (node.isLeaf()) {
				max = (node.getExpectedUtility() > max.getExpectedUtility()) ? node : max;
			}
		}
		while (max != null) {
			System.out.println(max.getValue());
			max = max.getParent();
		}
	}
	
}
