package armani.anderson.sihts.model;

import java.util.List;

public class ScriptBO {
	public boolean insert(ScriptVO scptVO) {
		
		ScriptDAO scptDAO = new ScriptDAO();
		
		return scptDAO.insert(scptVO); 
	}
	
	public List<ScriptVO> select(ScriptVO scptVO) {
		ScriptDAO scptDAO = new ScriptDAO();
		
		return scptDAO.select(scptVO);
	}
	
	public int update(ScriptVO scptVO) {
		
		return 0;
	}
	
	public int delete(ScriptVO scptVO) {
		ScriptDAO scptDAO = new ScriptDAO();
		return scptDAO.delete(scptVO);
	}
}
