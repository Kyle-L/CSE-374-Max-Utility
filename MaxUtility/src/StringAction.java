/**
 * Represents an action in which a string is printed.
 * @author Kyle Lierer
 *
 */
public class StringAction implements Action{

	private String actionStr;

	/**
	 * Creates an instance of StringAction.
	 * @param actionString - The string printed when the action is called.
	 */
	public StringAction(String actionString) {
		actionStr = actionString;
	}
	
	/**
	 * Returns the actionString.
	 * @return
	 */
	public String getActionStr() {
		return actionStr;
	}

	/**
	 * Sets the actionString.
	 * @param actionStr
	 */
	public void setActionStr(String actionStr) {
		this.actionStr = actionStr;
	}

	@Override
	public void Go() {
		System.out.println(actionStr);
	}
	
}
