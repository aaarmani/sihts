package armani.anderson.sihts.model;

import java.util.List;

public class TestBO {
	public boolean insert(TestVO testVO) {
		
		TestDAO tstDAO = new TestDAO();
		
		return tstDAO.insert(testVO); 
	}
	
	public List<TestVO> select(TestVO testVO) {
		TestDAO tstDAO = new TestDAO();
		
		return tstDAO.select(testVO);
	}
	
	public int update(TestVO testVO) {
		
		return 0;
	}
	
	public int delete(TestVO testVO) {
		TestDAO tstDAO = new TestDAO();
		return tstDAO.delete(testVO);
	}
}
