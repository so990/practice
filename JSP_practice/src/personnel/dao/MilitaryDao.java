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
import personnel.model.Family;
import personnel.model.Military;

public class MilitaryDao {
	
	public Military insert(Connection conn, Military mil) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into military values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, mil.getEmp_no());
			pstmt.setString(2, mil.getMil());
			pstmt.setString(3, mil.getMil_type());
			pstmt.setString(4, mil.getMil_start());
			pstmt.setString(5, mil.getMil_end());
			pstmt.setString(6, mil.getMil_rank());
			pstmt.setString(7, mil.getMil_job());
			pstmt.setString(8, mil.getMil_no_reason());			
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT emp_no FROM military ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					Integer newNum = rs.getInt(1);
					
					return new Military(newNum, 
							mil.getMil(),
							mil.getMil_type(),
							mil.getMil_start(),
							mil.getMil_end(),
							mil.getMil_rank(),
							mil.getMil_job(),
							mil.getMil_no_reason()
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
	 public Military selectByNo(Connection conn, String no) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from military where emp_no=?");
	         pstmt.setString(1, no);
	         rs = pstmt.executeQuery();
	         Military military = null;
	         if(rs.next()) {
	        	 military = convertMilitary(rs);
	         }
	         return military;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	private Military convertMilitary(ResultSet rs) throws SQLException {
		return new Military(
				rs.getInt("emp_no"),
				rs.getString("mil"),
				rs.getString("mil_type"),
				rs.getString("mil_start"),
				rs.getString("mil_end"),
				rs.getString("mil_rank"),
				rs.getString("mil_job"),
				rs.getString("mil_no_reason")
				);
	}
}