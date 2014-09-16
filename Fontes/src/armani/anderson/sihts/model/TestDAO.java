package armani.anderson.sihts.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import armani.anderson.sihts.factory.ConnectionFactory;

public class TestDAO {
	final static String COL_ID = "id";
	final static String COL_NAME = "name";
	final static String COL_DESCRIPTION = "description";
	final static String COL_RET = "return";
	
	public boolean insert(TestVO testVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
	    String sql = "INSERT INTO test(" + COL_NAME + ", " + COL_DESCRIPTION + ", " + COL_RET + ")"
	    		   + " VALUES(?,?,?)";
		
		try {
		   connection = new ConnectionFactory().getConnection();
			
		   stmt = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
		   stmt.setString(1, testVO.getName());
		   stmt.setString(2, testVO.getDescription());
		   stmt.setInt(3, testVO.getReturnId());
		   
		   ret = stmt.executeUpdate();

		   ResultSet keys = stmt.getGeneratedKeys();
		   if(keys.next()) {
			   testVO.setId(keys.getLong(1));   
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
	
	public List<TestVO> select(TestVO testVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		List<TestVO> lstTestVO = null;
		
		String sql = "SELECT * FROM test";
		
		if(testVO != null) {
			String where = " WHERE ";
			
			
			if(testVO.getName() != "") {
				where += COL_NAME + " = '" + testVO.getName() + "'";
			}
			
			sql += where;
			System.out.println("SQL = " + sql);
		}
		
		sql += " ORDER BY " + COL_NAME;
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			if(stmt.execute() == true) {
				resSet = stmt.getResultSet();
				if(resSet != null) {
					lstTestVO = new LinkedList<TestVO>();

					while(resSet.next()) {
						TestVO tstVO = new TestVO();
			
						tstVO.setId(Long.valueOf(resSet.getString(COL_ID)));
						tstVO.setName(resSet.getString(COL_NAME));
						tstVO.setDescription(resSet.getString(COL_DESCRIPTION));
						tstVO.setReturnId(resSet.getInt(COL_RET));
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
	
	public int update(TestVO testVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "UPDATE test SET "+ COL_NAME + "=?, " + COL_DESCRIPTION + "=?," + COL_RET + "=? WHERE id=?";
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, testVO.getName());
			stmt.setString(2, testVO.getDescription());
			stmt.setInt(3, testVO.getReturnId());
		    stmt.setLong(4, testVO.getId());
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
	
	public int delete(TestVO testVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM test WHERE id = ?";
		
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

}
