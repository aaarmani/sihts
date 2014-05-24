package armani.anderson.sihts.model;

import java.util.List;

public class ActionListBO {
	
	public boolean insert(ActionListVO actionListVO) {
		ActionListDAO actLstDAO = new ActionListDAO();
		
		return actLstDAO.insert(actionListVO);
	}
	
	public List<ActionListVO> select(ActionListVO actionListVO) {
		
		return null;
	}
	
	public int update(ActionListVO actionListVO) {
		
		return 0;
	}
	
	public int delete(ActionListVO actionListVO) {
	
		return 0;
	}
}
