package armani.anderson.sihts.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import armani.anderson.sihts.factory.ConnectionFactory;

public class ActionListDAO {
	final static String COL_ID = "id";
	final static String COL_INDEX = "index";
	final static String COL_ACTION_ID = "action_id";
	final static String COL_POSITION_ID = "position_id";
	
	public boolean insert(ActionListVO actionListVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
	    String sql = "INSERT INTO action_list(" + COL_INDEX + ", " + COL_ACTION_ID + "," + COL_POSITION_ID + ")"
	    		   + " VALUES(?,?,?)";
		
		try {
		   connection = new ConnectionFactory().getConnection();
			
		   stmt = connection.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
		   stmt.setInt(1, actionListVO.getIndex());
		   stmt.setInt(2, actionListVO.getActionId());
		   stmt.setInt(3, actionListVO.getPositionId());
		   
		   ret = stmt.executeUpdate();

		   ResultSet keys = stmt.getGeneratedKeys();
		   if(keys.next()) {
			   actionListVO.setId(keys.getInt(1));   
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
	
	public List<ActionListVO> select(ActionListVO actionListVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		List<ActionListVO> lstActListVO = null;
		
		String sql = "SELECT * FROM action";
		
		if(actionListVO != null) {
			String where = " WHERE ";
			
			if(actionListVO.getId() >= 0) {
				where += COL_ID + " = " + actionListVO.getId();
			}
			
			if(actionListVO.getIndex() >= 0) {
				where += COL_INDEX + " = " + actionListVO.getIndex();
			}
			
			if(actionListVO.getActionId() >= 0) {
				where += COL_ACTION_ID + " = " + actionListVO.getActionId();
			}
			
			if(actionListVO.getPositionId() >= 0) {
				where += COL_POSITION_ID + " = " + actionListVO.getPositionId();
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
					lstActListVO = new LinkedList<ActionListVO>();

					while(resSet.next()) {
						ActionListVO actLstVO = new ActionListVO();
			
						actLstVO.setId(Integer.valueOf(resSet.getString(COL_ID)));
						actLstVO.setIndex(Integer.valueOf(resSet.getString(COL_INDEX)));
						actLstVO.setActionId(Integer.valueOf(resSet.getString(COL_ACTION_ID)));
						actLstVO.setPositionId(Integer.valueOf(resSet.getString(COL_POSITION_ID)));
						
						lstActListVO.add(actLstVO);
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
		return lstActListVO;
	}
	
	public int update(ActionListVO actionListVO) {
		
		return 0;
	}
	
	public int delete(ActionListVO actionListVO) {
	
		return 0;
	}
}
