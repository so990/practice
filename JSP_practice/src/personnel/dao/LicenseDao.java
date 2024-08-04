package personnel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import personnel.model.License;

public class LicenseDao {
	
	public License insert(Connection conn, License lsc) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into license values(?,?,?,?,?,?)");
			pstmt.setInt(1, lsc.getEmp_no());
			pstmt.setString(2, lsc.getLsc_name());
			pstmt.setString(3, lsc.getLsc_date());
			pstmt.setString(4, lsc.getLsc_dep());
			pstmt.setString(5, lsc.getLsc_num());
			pstmt.setString(6, lsc.getLsc_note());
			
			int insertedCount = pstmt.executeUpdate();
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT emp_no FROM license ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					Integer newNum = rs.getInt(1);
					
					return new License(newNum, 
							lsc.getLsc_name(),
							lsc.getLsc_date(),
							lsc.getLsc_dep(),
							lsc.getLsc_num(),
							lsc.getLsc_note()
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
	
	public License selectByNo(Connection conn, String no) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from license where emp_no=?");
	         pstmt.setString(1, no);
	         rs = pstmt.executeQuery();
	         License license = null;
	         if(rs.next()) {
	        	 license = convertLicense(rs);
	         }
	         return license;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	public List<License> selectList(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM License where emp_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			List<License> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertLicense(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private License convertLicense(ResultSet rs) throws SQLException {
		return new License(
				rs.getInt("emp_no"),
                rs.getString("lsc_name"),
                rs.getString("lsc_date"),
                rs.getString("lsc_dep"),
                rs.getString("lsc_num"),
                rs.getString("lsc_note")
				);
	}
}