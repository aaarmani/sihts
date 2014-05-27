package armani.anderson.sihts.model;

import java.util.List;

public class ActionListBO {
	
	public boolean insert(ActionListVO actionListVO) {
		ActionListDAO actLstDAO = new ActionListDAO();
		
		return actLstDAO.insert(actionListVO);
	}
	
	public List<ActionListVO> select(ActionListVO actionListVO) {
		ActionListDAO actLstDAO = new ActionListDAO();

		return actLstDAO.select(actionListVO);
	}
	
	public int update(ActionListVO actionListVO) {
		ActionListDAO actLstDAO = new ActionListDAO();
		
		return actLstDAO.update(actionListVO);
	}
	
	public int delete(ActionListVO actionListVO) {
		ActionListDAO actLstDAO = new ActionListDAO();

		return actLstDAO.delete(actionListVO);
	}
}
