package armani.anderson.sihts.model;

import java.util.List;
import java.util.Vector;

public class TestxActionBO {
	public boolean insert(TestxActionVO testxactionVO) {
		TestxActionDAO tstxactDAO = new TestxActionDAO();
		
		return tstxactDAO.insert(testxactionVO);
	}
	
	public List<TestxActionVO> select(TestxActionVO testxactionVO) {
		TestxActionDAO tstxactDAO = new TestxActionDAO();

		return tstxactDAO.select(testxactionVO);
	}
	
	public int update(TestxActionVO testxactionVO) {
		TestxActionDAO tstxactDAO = new TestxActionDAO();
		
		return tstxactDAO.update(testxactionVO);
	}
	
	public int delete(TestxActionVO testxactionVO) {
		TestxActionDAO tstxactDAO = new TestxActionDAO();

		return tstxactDAO.delete(testxactionVO);
	}

	public Vector<String> selectPositions(TestxActionVO actionLstVO) {
		TestxActionDAO tstxactDAO = new TestxActionDAO();
		return tstxactDAO.selectActions(actionLstVO);
	}
}
