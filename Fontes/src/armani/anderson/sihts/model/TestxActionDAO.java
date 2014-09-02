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
	final static String COL_ACTION_TYPE = "action_type";
	final static String COL_TEST_ID = "test_id";
	
	public boolean insert(TestxActionVO testxactionVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
	    String sql = "INSERT INTO testxaction(" + COL_INDEX + ", " + COL_TEST_ID + "," + ", " + COL_ACTION_ID + "," + COL_ACTION_TYPE + ")"
	    		   + " VALUES(?,?,?,?)";
		
		try {
		   connection = new ConnectionFactory().getConnection();
			
		   stmt = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
		   stmt.setInt(1, testxactionVO.getIndex());
		   stmt.setInt(2, testxactionVO.getTestId());
		   stmt.setInt(3, testxactionVO.getActionId());
		   stmt.setString(4, String.valueOf(testxactionVO.getType()));
		   
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
	
	public List<TestxActionVO> select(TestxActionVO testxactionVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		List<TestxActionVO> lstTstxActVO = null;
		
		String sql = "SELECT * FROM testxaction";
		
		if(testxactionVO != null) {
			String where = " WHERE ";
			
			if(testxactionVO.getId() >= 0) {
				where += COL_ID + " = " + testxactionVO.getId();
			}
			
			if(testxactionVO.getIndex() >= 0) {
				where += COL_INDEX + " = " + testxactionVO.getIndex();
			}
			
			if(testxactionVO.getActionId() >= 0) {
				where += COL_ACTION_ID + " = " + testxactionVO.getActionId();
			}
			
			if(testxactionVO.getType() == 'A' || testxactionVO.getType() == 'E') {
				where += COL_ACTION_TYPE + " = '" + testxactionVO.getType() + "";
			}
			
			sql += where;
			System.out.println("SQL = " + sql);
		}
		
		sql += " ORDER BY " + COL_ACTION_ID + "," + COL_INDEX;
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			if(stmt.execute() == true) {
				resSet = stmt.getResultSet();
				if(resSet != null) {
					lstTstxActVO = new LinkedList<TestxActionVO>();

					while(resSet.next()) {
						TestxActionVO tstxactVO = new TestxActionVO();
			
						tstxactVO.setId(Integer.valueOf(resSet.getString(COL_ID)));
						tstxactVO.setIndex(Integer.valueOf(resSet.getString(COL_INDEX)));
						tstxactVO.setActionId(Integer.valueOf(resSet.getString(COL_ACTION_ID)));
						tstxactVO.setType(resSet.getString(COL_ACTION_TYPE).charAt(0));
						tstxactVO.setTestId(Integer.valueOf(resSet.getString(COL_TEST_ID)));

						lstTstxActVO.add(tstxactVO);
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
		return lstTstxActVO;
	}
	
	public int update(TestxActionVO testxactionVO) {
		
		return 0;
	}
	
	public int delete(TestxActionVO testxactionVO) {
	
		return 0;
	}

	public Vector<String> selectActions(TestxActionVO testxactionVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		Vector<String> vctActionName = null;
		
		String sql = "SELECT action.name FROM testxaction, action WHERE testxaction.action_id = action.id AND test_id = ";
		sql += testxactionVO.getTestId();
		sql += " ORDER BY testxaction.index";

		System.out.println(sql);
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
