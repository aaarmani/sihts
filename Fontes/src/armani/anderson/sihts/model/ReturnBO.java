package armani.anderson.sihts.model;

import java.util.List;

public class ReturnBO {
	public boolean insert(ReturnVO returnVO) {
		
		ReturnDAO retDAO = new ReturnDAO();
		
		return retDAO.insert(returnVO); 
	}
	
	public List<ReturnVO> select(ReturnVO returnVO) {
		ReturnDAO retDAO = new ReturnDAO();
		
		return retDAO.select(returnVO);
	}
	
	public int update(ReturnVO returnVO) {
		
		return 0;
	}
	
	public int delete(ReturnVO returnVO) {
		ReturnDAO retDAO = new ReturnDAO();
		return retDAO.delete(returnVO);
	}

}
