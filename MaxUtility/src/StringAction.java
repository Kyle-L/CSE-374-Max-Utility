
public class StringAction implements Action{

	private String actionStr;

	public StringAction(String str) {
		actionStr = str;
	}
	
	public String getActionStr() {
		return actionStr;
	}

	public void setActionStr(String actionStr) {
		this.actionStr = actionStr;
	}

	@Override
	public void Go() {
		System.out.println(actionStr);
	}
	
}
