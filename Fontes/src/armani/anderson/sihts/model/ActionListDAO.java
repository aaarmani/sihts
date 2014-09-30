package armani.anderson.sihts.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

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
		
		String sql = "SELECT * FROM action_list";
		
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
	
	public int delete(ActionVO actionVO) {
		int ret = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM action_list WHERE action_id = ?";

		try {
			connection = new ConnectionFactory().getConnection();
			
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, actionVO.getId());
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

	public Vector<String> selectPositions(ActionListVO actionLstVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		Vector<String> vctPosName = null;
		
		String sql = "SELECT position.name FROM action_list, position WHERE action_list.position_id = position.id AND action_id = ";
		sql += actionLstVO.getActionId();
		sql += " ORDER BY action_list.index";

		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			
			if(stmt.execute() == true) {
				resSet = stmt.getResultSet();
				if(resSet != null) {

					vctPosName = new Vector<>();
					while(resSet.next()) {
						vctPosName.add(resSet.getString("name"));
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
		return vctPosName;

	}

	public List<PositionVO> selectPositionsBO(ActionVO actionVO) {
		ResultSet resSet = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		List<PositionVO> lstPosVO = null;
		
		String sql = "SELECT pos.id, pos.name, pos.pos_type, pos.positionartc1, pos.positionartc2," +
				     " pos.positionartc3, pos.positionartc4, pos.positionartc5"+
				     " FROM action_list act, position pos"+
				     " where act.position_id = pos.id and act.action_id = ";

		sql += actionVO.getId();
		sql += " ORDER BY act.index";

		try {
			connection = new ConnectionFactory().getConnection();
			stmt = connection.prepareStatement(sql);
			
			if(stmt.execute() == true) {
				resSet = stmt.getResultSet();
				if(resSet != null) {
					lstPosVO = new LinkedList<PositionVO>();

					while(resSet.next()) {
						PositionVO posVO = new PositionVO();
						
						posVO.setId(Long.valueOf(resSet.getString(PositionDAO.POS_COL_ID)));
						posVO.setName(resSet.getString(PositionDAO.POS_COL_NAME));
						posVO.setType(resSet.getString(PositionDAO.POS_COL_TYPE).charAt(0));
						posVO.setPositionArtc1(Integer.valueOf(resSet.getString(PositionDAO.POS_COL_ARTC1)));
						posVO.setPositionArtc2(Integer.valueOf(resSet.getString(PositionDAO.POS_COL_ARTC2)));
						posVO.setPositionArtc3(Integer.valueOf(resSet.getString(PositionDAO.POS_COL_ARTC3)));
						posVO.setPositionArtc4(Integer.valueOf(resSet.getString(PositionDAO.POS_COL_ARTC4)));
						posVO.setPositionArtc5(Integer.valueOf(resSet.getString(PositionDAO.POS_COL_ARTC5)));
						
						lstPosVO.add(posVO);
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
		return lstPosVO;
	}
}
