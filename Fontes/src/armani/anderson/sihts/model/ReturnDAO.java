package armani.anderson.sihts.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import armani.anderson.sihts.factory.ConnectionFactory;

public class ReturnDAO {
	final static String COL_ID = "id";
	final static String COL_NAME = "name";
	final static String COL_DESC = "description";
	final static String COL_TIMEOUT = "timeout";
	final static String COL_TEXT = "text";
	
	
	public boolean insert(ReturnVO returnVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
	    String sql = "INSERT INTO return (" + COL_NAME + ", " + COL_DESC + "," + COL_TIMEOUT + "," + COL_TEXT +")"
	    		   + " VALUES(?,?,?,?)";
		
		try {
		   connection = new ConnectionFactory().getConnection();
			
		   stmt = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
		   stmt.setString(1, returnVO.getName());
		   stmt.setString(2, returnVO.getDescription());
		   stmt.setInt(3, returnVO.getTimeout());
		   stmt.setString(4, returnVO.getText());
		   
		   ret = stmt.executeUpdate();

		   ResultSet keys = stmt.getGeneratedKeys();
		   if(keys.next()) {
			   returnVO.setId(keys.getInt(1));   
		   }
		   
		   keys.close();
		   stmt.close();
		} catch (SQLException e) {
		    throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (ret > 0);
	}
	
	public List<ReturnVO> select(ReturnVO returnVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		List<ReturnVO> lstReturnVO = null;
		
		String sql = "SELECT * FROM return";
		
		if(returnVO != null) {
			String where = " WHERE ";
			
			if(returnVO.getId() >= 0) {
				where += COL_ID + " = " + returnVO.getId();
			}
			
			if(returnVO.getName() != null  && returnVO.getName().length() >= 0) {
				where += COL_NAME + " = " + returnVO.getName();
			}
			
			if(returnVO.getDescription() != null && returnVO.getDescription().length() >= 0) {
				where += COL_DESC + " = " + returnVO.getDescription();
			}
			
			/*if(returnVO.getTimeout() >= 0) {
				where += COL_TIMEOUT + " = " + returnVO.getTimeout();
			}*/
			
			if(returnVO.getText() != null && returnVO.getText().length() >= 0) {
				where += COL_TEXT + " = " + returnVO.getText();
			}
			
			sql += where;
			System.out.println("SQL = " + sql);
		}
		
		sql += " ORDER BY " + COL_ID + "," + COL_NAME;
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			if(stmt.execute() == true) {
				resSet = stmt.getResultSet();
				if(resSet != null) {
					lstReturnVO = new LinkedList<ReturnVO>();

					while(resSet.next()) {
						ReturnVO retLstVO = new ReturnVO();
			
						retLstVO.setId(Integer.valueOf(resSet.getString(COL_ID)));
						retLstVO.setName(resSet.getString(COL_NAME));
						retLstVO.setDescription(resSet.getString(COL_DESC));
						retLstVO.setTimeout(Integer.valueOf(resSet.getString(COL_TIMEOUT)));
						retLstVO.setText(resSet.getString(COL_TEXT));
						
						lstReturnVO.add(retLstVO);
					}
				}
			}
			
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lstReturnVO;
	}
	
	public int update(ReturnVO returnVO) {
		
		return 0;
	}
	
	public int delete(ReturnVO returnVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM return WHERE id = ?";
		
		try {
			connection = new ConnectionFactory().getConnection();
			
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, returnVO.getId());
			ret = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}

}
