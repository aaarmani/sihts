package armani.anderson.sihts.model;

import java.util.List;

/**
 * Classe que descreve a entidade Action
 * @author armani
 * V00.01
 */
public class ActionBO {

	public boolean insert(ActionVO actionVO) {
		
		ActionDAO actDAO = new ActionDAO();
		
		return actDAO.insert(actionVO); 
	}
	
	public List<ActionVO> select(ActionVO actionVO) {
		ActionDAO actDAO = new ActionDAO();
		
		return actDAO.select(actionVO);
	}
	
	public int update(ActionVO actionVO) {
		
		return 0;
	}
	
	public int delete(ActionVO actionVO) {
		ActionDAO actDAO = new ActionDAO();
		return actDAO.delete(actionVO);
	}
}
