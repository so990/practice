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
import personnel.model.Issuing;

public class IssuingDao {
	
	public Issuing insert(Connection conn, Issuing isu) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into issuing values(?,?,?,?)");
			pstmt.setInt(1, isu.getIsu_num());
			pstmt.setString(2, isu.getIsu_led());
			pstmt.setString(3, isu.getIsu_pur());
			pstmt.setTimestamp(4, toTimestamp(isu.getIsu_date()));

			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT isu_num FROM issuing ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					Integer newNum = rs.getInt(1);
					
					return new Issuing(newNum,
							isu.getIsu_led(),
							isu.getIsu_pur(),
							isu.getIsu_date()
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
	
	public Issuing selectByNum(Connection conn, String num) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from issuing where isu_num=?");
	         pstmt.setString(1, num);
	         rs = pstmt.executeQuery();
	         Issuing issuing = null;
	         if(rs.next()) {
	        	 issuing = convertIssuing(rs);
	         }
	         return issuing;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	private Issuing convertIssuing(ResultSet rs) throws SQLException {
		return new Issuing(
				rs.getInt("isu_num"),
                rs.getString("isu_led"),
                rs.getString("isu_pur"),
                rs.getDate("isu_date")
				);
	}
}