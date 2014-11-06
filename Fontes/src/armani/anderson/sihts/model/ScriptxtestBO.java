package armani.anderson.sihts.model;

import java.util.List;
import java.util.Vector;

public class ScriptxtestBO {
	public boolean insert(ScriptxtestVO scriptxtestVO) {
		ScriptxTestDAO scriptxtestDAO = new ScriptxTestDAO();
		
		return scriptxtestDAO.insert(scriptxtestVO);
	}
	
	public List<TestVO> select(ScriptVO scptVO) {
		ScriptxTestDAO scriptxtestDAO = new ScriptxTestDAO();

		return scriptxtestDAO.select(scptVO);
	}
	
	public int update(ScriptxtestVO scriptxtestVO) {
		ScriptxTestDAO scriptxtestDAO = new ScriptxTestDAO();
		
		return scriptxtestDAO.update(scriptxtestVO);
	}
	
	public int delete(ScriptVO scptVO) {
		ScriptxTestDAO scriptxtestDAO = new ScriptxTestDAO();

		return scriptxtestDAO.delete(scptVO);
	}

	public Vector<String> selectTests(ScriptVO scptVO) {
		ScriptxTestDAO scriptxtestDAO = new ScriptxTestDAO();
		return scriptxtestDAO.selectTests(scptVO);
	}
}
