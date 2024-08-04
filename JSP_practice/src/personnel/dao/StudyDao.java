package personnel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import personnel.model.Study;

public class StudyDao {
	
	public Study insert(Connection conn, Study std) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into study values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, std.getEmp_no());
			pstmt.setString(2, std.getStudy_type());
			pstmt.setString(3, std.getStudy_name());
			pstmt.setString(4, std.getStudy_start());
			pstmt.setString(5, std.getStudy_end());
			pstmt.setString(6, std.getStudy_dep());
			pstmt.setString(7, std.getStudy_cost());
			pstmt.setString(8, std.getStudy_refund());
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT emp_no FROM study ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					Integer newNum = rs.getInt(1);
					
					return new Study(newNum, 
							std.getStudy_type(),
							std.getStudy_name(),
							std.getStudy_start(),
							std.getStudy_end(),
							std.getStudy_dep(),
							std.getStudy_cost(),
							std.getStudy_refund()
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
	
	 public Study selectByNo(Connection conn, String no) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from study where emp_no=?");
	         pstmt.setString(1, no);
	         rs = pstmt.executeQuery();
	         Study study = null;
	         if(rs.next()) {
	        	 study = convertStudy(rs);
	         }
	         return study;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
		public List<Study> selectList(Connection conn, int no) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement("SELECT * FROM Study where emp_no=?");
				rs = pstmt.executeQuery();
				List<Study> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertStudy(rs));
				}
				return result;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
	
	private Study convertStudy(ResultSet rs) throws SQLException {
		return new Study(
			rs.getInt("emp_no"),
            rs.getString("study_type"),
            rs.getString("study_name"),
            rs.getString("study_start"),
            rs.getString("study_end"),
            rs.getString("study_dep"),
            rs.getString("study_cost"),
            rs.getString("study_refund")
			);
	}
}