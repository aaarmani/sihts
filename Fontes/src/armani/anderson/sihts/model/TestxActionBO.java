package armani.anderson.sihts.model;

import java.util.List;
import java.util.Vector;

public class TestxActionBO {
	public boolean insert(TestxActionVO testxactionVO) {
		TestxActionDAO tstxactDAO = new TestxActionDAO();
		
		return tstxactDAO.insert(testxactionVO);
	}
	
	public List<ActionVO> select(TestVO testVO) {
		TestxActionDAO tstxactDAO = new TestxActionDAO();

		return tstxactDAO.select(testVO);
	}
	
	public int update(TestxActionVO testxactionVO) {
		TestxActionDAO tstxactDAO = new TestxActionDAO();
		
		return tstxactDAO.update(testxactionVO);
	}
	
	public int delete(TestVO testVO) {
		TestxActionDAO tstxactDAO = new TestxActionDAO();

		return tstxactDAO.delete(testVO);
	}

	public Vector<String> selectActions(TestVO tstVO) {
		TestxActionDAO tstxactDAO = new TestxActionDAO();
		return tstxactDAO.selectActions(tstVO);
	}
}
