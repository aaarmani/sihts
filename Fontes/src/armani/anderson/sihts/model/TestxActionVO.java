package armani.anderson.sihts.model;

/**
 * 
 * @author armani
 *
 */
public class TestxActionVO {
	private int id;
	private int index;
	private int testId;
	private int actionId;
	private char type; //A Action or E Especial

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
}
