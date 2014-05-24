package armani.anderson.sihts.model;

public class ActionListVO {
	final static String COL_ID = "id";
	final static String COL_INDEX = "index";
	final static String COL_ACTION_ID = "action_id";
	final static String COL_POSITION_ID = "position_id";
	
	private int id;
	private int index;
	private int actionId;
	private int positionId;
	
	public ActionListVO() {
		this.id = -1;
		this.index = -1;
		this.actionId = -1;
		this.positionId = -1;
	}
	
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
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
}
