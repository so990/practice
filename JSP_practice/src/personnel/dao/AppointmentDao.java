package personnel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import personnel.model.Appointment;

public class AppointmentDao {
	
	public Appointment insert(Connection conn, Appointment apm) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into appointment values(?,?,?,?,?,?,?)");
			pstmt.setInt(1, apm.getEmp_no());
			pstmt.setString(2, apm.getAppo_type());
			pstmt.setDate(3, apm.getAppo_date());
			pstmt.setString(4, apm.getAppo_dep());
			pstmt.setString(5, apm.getAppo_job());
			pstmt.setString(6, apm.getAppo_task());
			pstmt.setString(7, apm.getAppo_note());
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT emp_no FROM appointment ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					Integer newNum = rs.getInt(1);
					
					return new Appointment(newNum, 
							apm.getAppo_type(),
							apm.getAppo_date(),
							apm.getAppo_dep(),
							apm.getAppo_job(),
							apm.getAppo_task(),
							apm.getAppo_note()
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
	
	 public Appointment selectByNo(Connection conn, String no) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from appointment where emp_no=?");
	         pstmt.setString(1, no);
	         rs = pstmt.executeQuery();
	         Appointment appointment = null;
	         if(rs.next()) {
	        	 appointment = convertAppointment(rs);
	         }
	         return appointment;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	 
	 public List<Appointment> selectList(Connection conn, int no) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement("SELECT * FROM Appointment where emp_no=?");
				pstmt.setInt(1, no);
				
				rs = pstmt.executeQuery();
				List<Appointment> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertAppointment(rs));
				}
				return result;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
	
	private Appointment convertAppointment(ResultSet rs) throws SQLException {
		return new Appointment(
				rs.getInt("emp_no"),
                rs.getString("appo_type"),
                rs.getDate("appo_date"),
                rs.getString("appo_dep"),
                rs.getString("appo_job"),
                rs.getString("appo_task"),
                rs.getString("appo_note")
				);
	}
}