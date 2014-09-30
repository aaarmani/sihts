package armani.anderson.sihts.model;

import java.util.List;
import java.util.Vector;

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
	
	public int delete(ActionVO actionVO) {
		ActionListDAO actLstDAO = new ActionListDAO();

		return actLstDAO.delete(actionVO);
	}

	public Vector<String> selectPositions(ActionListVO actionLstVO) {
		ActionListDAO actLstDAO = new ActionListDAO();
		return actLstDAO.selectPositions(actionLstVO);
	}
	
	public List<PositionVO> selectPositionsVO(ActionVO actionVO) {
		ActionListDAO actLstDAO = new ActionListDAO();
		return actLstDAO.selectPositionsBO(actionVO);
	}
}
