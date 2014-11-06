package armani.anderson.sihts.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import armani.anderson.sihts.factory.ConnectionFactory;

public class ScriptxTestDAO {
	final static String COL_ID = "id";
	final static String COL_INDEX = "index";
	final static String COL_SCRIPT_ID = "script_id";
	final static String COL_TEST_ID = "test_id";
	
	public boolean insert(ScriptxtestVO testxActionVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
	    String sql = "INSERT INTO testxaction(" + COL_INDEX + ", " + COL_TEST_ID + "," + COL_SCRIPT_ID + ")"
	    		   + " VALUES(?,?,?)";
		
		try {
		   connection = new ConnectionFactory().getConnection();
			
		   stmt = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
		   stmt.setInt(1, testxActionVO.getIndex());
		   stmt.setInt(2, testxActionVO.getTestId());
		   stmt.setInt(3, testxActionVO.getScriptId());
		   System.out.println("SQL =  " + sql);
		   ret = stmt.executeUpdate();

		   ResultSet keys = stmt.getGeneratedKeys();
		   if(keys.next()) {
			   testxActionVO.setId(keys.getInt(1));   
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
	
	public List<TestVO> select(ScriptVO scriptVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		List<TestVO> lstTestVO = null;
		
		String sql = "SELECT test.id, test.name, test.description FROM Scriptxtest, test WHERE scriptxtest.test_id = test.id AND script_id = ";
		sql += scriptVO.getId();
		sql += " ORDER BY Scriptxtest.index";
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			
			if(stmt.execute() == true) {
				resSet = stmt.getResultSet();
				if(resSet != null) {
					lstTestVO = new LinkedList<TestVO>();
					while(resSet.next()) {
						TestVO tstVO = new TestVO();
						tstVO.setName(resSet.getString("name"));
						tstVO.setId(resSet.getLong("id"));
						tstVO.setDescription(resSet.getString("description"));
						
						lstTestVO.add(tstVO);
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
		return lstTestVO;
	}
	
	public int update(ScriptxtestVO testxactionVO) {
		
		return 0;
	}
	
	public int delete(ScriptVO scriptVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM Scriptxtest WHERE Scriptxtest.test_id = ?";

		try {
			connection = new ConnectionFactory().getConnection();
			
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, scriptVO.getId());
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

	public Vector<String> selectTests(ScriptVO scriptVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		Vector<String> vctTestName = null;
		
		String sql = "SELECT test.name FROM Scriptxtest, test WHERE Scriptxtest.test_id = test.id AND script_id = ";
		sql += scriptVO.getId();
		sql += " ORDER BY Scriptxtest.index";

		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			
			if(stmt.execute() == true) {
				resSet = stmt.getResultSet();
				if(resSet != null) {

					vctTestName = new Vector<>();
					while(resSet.next()) {
						vctTestName.add(resSet.getString("name"));
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
		return vctTestName;
	}
}
