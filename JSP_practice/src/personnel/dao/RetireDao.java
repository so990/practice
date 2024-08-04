package personnel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.JdbcUtil;
import personnel.model.Retire;

public class RetireDao {
	
	public Retire insert(Connection conn, Retire ret) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into retire values(?,?,?,?,?,?)");
			pstmt.setInt(1, ret.getEmp_no());
			pstmt.setString(2, ret.getRetire_type());
			pstmt.setDate(3, ret.getRetired_date());
			pstmt.setString(4, ret.getRetire_reason());
			pstmt.setString(5, ret.getRetire_phone());
			pstmt.setString(6, ret.getRetire_cost());
						
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT emp_no FROM retire ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					Integer newNum = rs.getInt(1);
					
					return new Retire(newNum, 
							ret.getRetire_type(),
							ret.getRetired_date(),
							ret.getRetire_reason(),
							ret.getRetire_phone(),
							ret.getRetire_cost()
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
	
	 public Retire selectByNo(Connection conn, int no) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from retire where emp_no=?");
	         pstmt.setInt(1, no);
	         rs = pstmt.executeQuery();
	         Retire retire = null;
	         if(rs.next()) {
	        	 retire = convertRetire(rs);
	         }
	         return retire;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }

	private Retire convertRetire(ResultSet rs) throws SQLException {
		return new Retire(
			rs.getInt("emp_no"),
            rs.getString("retire_type"),
            rs.getDate("retired_date"),
            rs.getString("retire_reason"),
            rs.getString("retire_phone"),
            rs.getString("retire_cost")
			);
	}
}