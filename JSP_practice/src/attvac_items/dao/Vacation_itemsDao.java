package attvac_items.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import attvac_items.model.Vacation_days_setting;
import attvac_items.model.Vacation_items;
import jdbc.JdbcUtil;

public class Vacation_itemsDao {
	
	public Vacation_items insert(Connection conn, Vacation_items vac) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into vacation_items values(?,?,?,?)");
			pstmt.setString(1, vac.getVac_name());
			pstmt.setDate(2, vac.getVac_start() );
			pstmt.setDate(3, vac.getVac_end());
			pstmt.setString(4, vac.getVac_used());
						
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT vac_name FROM vacation_items ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					String newNum = rs.getString(1);
					
					return new Vacation_items(newNum, 
							vac.getVac_start(),
							vac.getVac_end(),
							vac.getVac_used()
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
	
	 public List<Vacation_items> selectAll(Connection conn) throws SQLException {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            pstmt = conn.prepareStatement("SELECT * FROM vacation_items");
	            rs = pstmt.executeQuery();
	            List<Vacation_items> result = new ArrayList<>();
	            while (rs.next()) {
	                result.add(convertVacation_items(rs));
	            }
	            return result;
	        } finally {
	            JdbcUtil.close(rs);
	            JdbcUtil.close(pstmt);
	        }
	    }
	 
	 public Vacation_items selectByName(Connection conn, String name) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from vacation_items where vac_name=?");
	         pstmt.setString(1, name);
	         rs = pstmt.executeQuery();
	         Vacation_items vacation_items = null;
	         if(rs.next()) {
	        	 vacation_items = convertVacation_items(rs);
	         }
	         return vacation_items;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	 
	 
	 //모달창 구현 join
	 public List<Vacation_days_setting> selectModal(Connection conn) throws SQLException {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            pstmt = conn.prepareStatement("SELECT e.emp_type, e.emp_no, e.name_kor, e.dept, e.job, e.hired_date, vd.has_vac_days FROM employee e LEFT OUTER JOIN vacation_days vd ON e.emp_no = vd.emp_no ORDER BY e.dept ASC");
	            rs = pstmt.executeQuery();
	            List<Vacation_days_setting> result = new ArrayList<>();
	            while (rs.next()) {
	                result.add(convertVacation_days_setting(rs));
	            }
	            return result;
	        } finally {
	            JdbcUtil.close(rs);
	            JdbcUtil.close(pstmt);
	        }
	    }
	 
	 //휴가항목 삭제
	 public void delete (Connection conn, String name) throws SQLException{
			try(PreparedStatement pstmt = conn.prepareStatement(
					"delete from vacation_items where vac_name =?")){
				pstmt.setString(1, name);
				pstmt.executeUpdate();
			}
		}
	 
	 //휴가항목 수정
	 public int update (Connection conn, String before_name, Vacation_items vac) throws SQLException{
			try(PreparedStatement pstmt = conn.prepareStatement(
					"update vacation_items set vac_name=?, vac_start=?, vac_end=?, vac_used =? where vac_name =?")){
				
				pstmt.setString(1, vac.getVac_name());
				pstmt.setDate(2, vac.getVac_start());
				pstmt.setDate(3, vac.getVac_end());
				pstmt.setString(4, vac.getVac_used());
				pstmt.setString(5, before_name);
				
				return pstmt.executeUpdate();
			}
		}
	
	private Vacation_items convertVacation_items(ResultSet rs) throws SQLException {
		return new Vacation_items(
			rs.getString("vac_name"),
            rs.getDate("vac_start"),
            rs.getDate("vac_end"),
            rs.getString("vac_used")
			);
	}	
	
	private Vacation_days_setting convertVacation_days_setting(ResultSet rs) throws SQLException {
		return new Vacation_days_setting(
            rs.getString("emp_type"),
            rs.getInt("emp_no"),
            rs.getString("name_kor"),
            rs.getString("dept"),
            rs.getString("job"),
            rs.getDate("hired_date"),
            rs.getInt("has_vac_days")
			);
	}
}