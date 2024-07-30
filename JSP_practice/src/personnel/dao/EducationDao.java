package personnel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import personnel.model.Appointment;
import personnel.model.Career;
import personnel.model.Education;

public class EducationDao {
	
	public Education insert(Connection conn, Education edu) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into education values(?,?,?,?,?,?,?)");
			pstmt.setInt(1, edu.getEmp_no());
			pstmt.setString(2, edu.getSchool());
			pstmt.setString(3, edu.getSchool_start());
			pstmt.setString(4, edu.getSchool_end());
			pstmt.setString(5, edu.getSchool_name());
			pstmt.setString(6, edu.getSchool_major());
			pstmt.setString(7, edu.getSchool_state());
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT emp_no FROM education ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					Integer newNum = rs.getInt(1);
					
					return new Education(newNum, 
							edu.getSchool(),
							edu.getSchool_start(),
							edu.getSchool_end(),
							edu.getSchool_name(),
							edu.getSchool_major(),
							edu.getSchool_state()
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
	
	public Education selectByNo(Connection conn, String no) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from education where emp_no=?");
	         pstmt.setString(1, no);
	         rs = pstmt.executeQuery();
	         Education education = null;
	         if(rs.next()) {
	        	 education = convertEducation(rs);
	         }
	         return education;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	private Education convertEducation(ResultSet rs) throws SQLException {
		return new Education(
				rs.getInt("emp_no"),
                rs.getString("school"),
                rs.getString("school_start"),
                rs.getString("school_end"),
                rs.getString("school_name"),
                rs.getString("school_major"),
                rs.getString("school_state")
				);
	}
}