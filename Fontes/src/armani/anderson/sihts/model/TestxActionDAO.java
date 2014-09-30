package armani.anderson.sihts.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import armani.anderson.sihts.factory.ConnectionFactory;

public class TestxActionDAO {
	final static String COL_ID = "id";
	final static String COL_INDEX = "index";
	final static String COL_ACTION_ID = "action_id";
	final static String COL_TEST_ID = "test_id";
	
	public boolean insert(TestxActionVO testxactionVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
	    String sql = "INSERT INTO testxaction(" + COL_INDEX + ", " + COL_TEST_ID + "," + COL_ACTION_ID + ")"
	    		   + " VALUES(?,?,?)";
		
		try {
		   connection = new ConnectionFactory().getConnection();
			
		   stmt = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
		   stmt.setInt(1, testxactionVO.getIndex());
		   stmt.setInt(2, testxactionVO.getTestId());
		   stmt.setInt(3, testxactionVO.getActionId());
		   System.out.println("SQL =  " + sql);
		   ret = stmt.executeUpdate();

		   ResultSet keys = stmt.getGeneratedKeys();
		   if(keys.next()) {
			   testxactionVO.setId(keys.getInt(1));   
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
	
	public List<ActionVO> select(TestVO testVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		List<ActionVO> lstActVO = null;
		
		String sql = "SELECT action.id, action.name, action.description FROM testxaction, action WHERE testxaction.action_id = action.id AND test_id = ";
		sql += testVO.getId();
		sql += " ORDER BY testxaction.index";
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			
			if(stmt.execute() == true) {
				resSet = stmt.getResultSet();
				if(resSet != null) {
					lstActVO = new LinkedList<ActionVO>();
					while(resSet.next()) {
						ActionVO actVO = new ActionVO();
						actVO.setName(resSet.getString("name"));
						actVO.setId(resSet.getLong("id"));
						actVO.setDescription(resSet.getString("description"));
						
						lstActVO.add(actVO);
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
		return lstActVO;
	}
	
	public int update(TestxActionVO testxactionVO) {
		
		return 0;
	}
	
	public int delete(TestVO testVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM testxaction WHERE testxaction.test_id = ?";

		try {
			connection = new ConnectionFactory().getConnection();
			
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, testVO.getId());
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

	public Vector<String> selectActions(TestVO testVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		Vector<String> vctActionName = null;
		
		String sql = "SELECT action.name FROM testxaction, action WHERE testxaction.action_id = action.id AND test_id = ";
		sql += testVO.getId();
		sql += " ORDER BY testxaction.index";

		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			
			if(stmt.execute() == true) {
				resSet = stmt.getResultSet();
				if(resSet != null) {

					vctActionName = new Vector<>();
					while(resSet.next()) {
						vctActionName.add(resSet.getString("name"));
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
		return vctActionName;

	}

}
