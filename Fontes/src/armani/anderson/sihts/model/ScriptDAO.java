package armani.anderson.sihts.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import armani.anderson.sihts.factory.ConnectionFactory;

public class ScriptDAO {
	final static String COL_ID = "id";
	final static String COL_NAME = "name";
	final static String COL_DESCRIPTION = "description";
	
	public boolean insert(ScriptVO scptVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
	    String sql = "INSERT INTO test(" + COL_NAME + ", " + COL_DESCRIPTION + ")"
	    		   + " VALUES(?,?)";
		
		try {
		   connection = new ConnectionFactory().getConnection();
			
		   stmt = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
		   stmt.setString(1, scptVO.getName());
		   stmt.setString(2, scptVO.getDescription());
		   
		   ret = stmt.executeUpdate();

		   ResultSet keys = stmt.getGeneratedKeys();
		   if(keys.next()) {
			   scptVO.setId(keys.getLong(1));   
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
	
	public List<ScriptVO> select(ScriptVO scptVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		List<ScriptVO> lstScptVO = null;
		
		String sql = "SELECT * FROM test";
		
		if(scptVO != null) {
			String where = " WHERE ";
			
			if(scptVO.getName() != "") {
				where += COL_NAME + " = '" + scptVO.getName() + "'";
			}
			
			sql += where;
		}
		
		sql += " ORDER BY " + COL_NAME;
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			if(stmt.execute() == true) {
				resSet = stmt.getResultSet();
				if(resSet != null) {
					lstScptVO = new LinkedList<ScriptVO>();

					while(resSet.next()) {
						ScriptVO tstVO = new ScriptVO();
			
						tstVO.setId(Long.valueOf(resSet.getString(COL_ID)));
						tstVO.setName(resSet.getString(COL_NAME));
						tstVO.setDescription(resSet.getString(COL_DESCRIPTION));
						lstScptVO.add(tstVO);
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
		return lstScptVO;
	}
	
	public int update(ScriptVO scptVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "UPDATE test SET "+ COL_NAME + "=?, " + COL_DESCRIPTION + "=? WHERE id=?";
		
		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, scptVO.getName());
			stmt.setString(2, scptVO.getDescription());
		    stmt.setLong(3, scptVO.getId());
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
	
	public int delete(ScriptVO scptVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM test WHERE id = ?";
		
		try {
			connection = new ConnectionFactory().getConnection();
			
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, scptVO.getId());
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
