package attvac_items.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import attvac_items.model.Attend_items;
import attvac_items.model.Vacation_items;
import jdbc.JdbcUtil;

public class Attend_itemsDao {
	
	public Attend_items insert(Connection conn, Attend_items att) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement("insert into attend_items values(?,?,?,?,?,?)");
			pstmt.setString(1, att.getAtt_name());
			pstmt.setString(2, att.getAtt_unit());
			pstmt.setString(3, att.getAtt_grp());
			pstmt.setString(4, att.getAtt_deduction());
			pstmt.setString(5, att.getAtt_conn());
			pstmt.setString(6, att.getAtt_used());
		
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT att_name FROM attend_items ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					String newNum = rs.getString(1);
					return new Attend_items(newNum, 
							att.getAtt_unit(),
							att.getAtt_grp(),
							att.getAtt_deduction(),
							att.getAtt_conn(),
							att.getAtt_used()
							);	
				}
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Attend_items selectByName(Connection conn, String name) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from attend_items where att_name=?");
	         pstmt.setString(1, name);
	         rs = pstmt.executeQuery();
	         Attend_items attend_items = null;
	         if(rs.next()) {
	        	 attend_items = convertAttend_items(rs);
	         }
	         return attend_items;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	 public List<Attend_items> selectAll(Connection conn) throws SQLException {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            pstmt = conn.prepareStatement("SELECT * FROM attend_items");
	            rs = pstmt.executeQuery();
	            List<Attend_items> result = new ArrayList<>();
	            while (rs.next()) {
	                result.add(convertAttend_items(rs));
	            }
	            return result;
	        } finally {
	            JdbcUtil.close(rs);
	            JdbcUtil.close(pstmt);
	        }
	    }
	 
	 public void delete (Connection conn, String name) throws SQLException{
			try(PreparedStatement pstmt = conn.prepareStatement(
					"delete from attend_items where att_name =?")){
				pstmt.setString(1, name);
				pstmt.executeUpdate();
			}
		}
	 
	 //근태항목 수정
	 public int update (Connection conn, String before_name, Attend_items att) throws SQLException{
			try(PreparedStatement pstmt = conn.prepareStatement(
					"update attend_items set att_name=?, att_unit=?, att_grp=?, att_deduction =?, att_conn =?, att_used=? where att_name =?")){
				
				pstmt.setString(1, att.getAtt_name());
				pstmt.setString(2, att.getAtt_unit());
				pstmt.setString(3, att.getAtt_grp());
				pstmt.setString(4, att.getAtt_deduction());
				pstmt.setString(5, att.getAtt_conn());
				pstmt.setString(6, att.getAtt_used());
				pstmt.setString(7, before_name);
				
				return pstmt.executeUpdate();
			}
		}
	
	private Attend_items convertAttend_items(ResultSet rs) throws SQLException {
		return new Attend_items(
				rs.getString("att_name"),        
                rs.getString("att_unit"),
                rs.getString("att_grp"),
                rs.getString("att_deduction"),
                rs.getString("att_conn"),
                rs.getString("att_used")
				);
	}
}