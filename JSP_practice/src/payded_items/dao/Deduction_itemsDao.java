package payded_items.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import attvac_items.model.Attend_items;
import attvac_items.model.Vacation_items;
import jdbc.JdbcUtil;
import payded_items.model.Deduction_items;
import payded_items.model.Payment_items;

public class Deduction_itemsDao {
	
	public Deduction_items insert(Connection conn, Deduction_items ded) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into deduction_items values(?,?,?,?,?)");
			pstmt.setString(1, ded.getDed_name());
			pstmt.setString(2, ded.getDed_memo());
			pstmt.setString(3, ded.getDed_cut_unit());
			pstmt.setString(4, ded.getDed_note());
			pstmt.setString(5, ded.getDed_used());
						
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT ded_name FROM deduction_items ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					String newNum = rs.getString(1);
					
					return new Deduction_items(newNum, 
							ded.getDed_memo(),
							ded.getDed_cut_unit(),
							ded.getDed_note(),
							ded.getDed_used()
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
	
	public Deduction_items selectByName(Connection conn, String name) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from deduction_items where ded_name=?");
	         pstmt.setString(1, name);
	         rs = pstmt.executeQuery();
	         Deduction_items deduction_items = null;
	         if(rs.next()) {
	        	 deduction_items = convertDeduction_items(rs);
	         }
	         return deduction_items;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	 public List<Deduction_items> selectAll(Connection conn) throws SQLException {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            pstmt = conn.prepareStatement("SELECT * FROM deduction_items");
	            rs = pstmt.executeQuery();
	            List<Deduction_items> result = new ArrayList<>();
	            while (rs.next()) {
	                result.add(convertDeduction_items(rs));
	            }
	            return result;
	        } finally {
	            JdbcUtil.close(rs);
	            JdbcUtil.close(pstmt);
	        }
	    }
	 
	//공제항목 삭제
	 public void delete (Connection conn, String name) throws SQLException{
			try(PreparedStatement pstmt = conn.prepareStatement(
					"delete from deduction_items where ded_name =?")){
				pstmt.setString(1, name);
				pstmt.executeUpdate();
			}
		}
	 
	//공제항목 수정
		 public int update (Connection conn, String before_name, Deduction_items ded) throws SQLException{
				try(PreparedStatement pstmt = conn.prepareStatement(
						"update deduction_items set ded_name=?, ded_memo=?, ded_cut_unit=?, ded_note =?, ded_used =? "
						+ "where ded_name =?")){
					
					pstmt.setString(1, ded.getDed_name());
					pstmt.setString(2, ded.getDed_memo());
					pstmt.setString(3, ded.getDed_cut_unit());
					pstmt.setString(4, ded.getDed_note());
					pstmt.setString(5, ded.getDed_used());
					pstmt.setString(6, before_name);
					
					return pstmt.executeUpdate();
				}
			}
	
	private Deduction_items convertDeduction_items(ResultSet rs) throws SQLException {
		return new Deduction_items(
			rs.getString("ded_name"),
			rs.getString("ded_memo"),
			rs.getString("ded_cut_unit"),
			rs.getString("ded_note"),
			rs.getString("ded_used")			
			);
	}	
}