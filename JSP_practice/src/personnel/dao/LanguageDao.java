package personnel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import personnel.model.Language;

public class LanguageDao {
	public Language insert(Connection conn, Language lng) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into language values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, lng.getEmp_no());
			pstmt.setString(2, lng.getLang_name());
			pstmt.setString(3, lng.getLang_test());
			pstmt.setString(4, lng.getLang_score());
			pstmt.setString(5, lng.getLang_date());
			pstmt.setString(6, lng.getLang_read());
			pstmt.setString(7, lng.getLang_listen());
			pstmt.setString(8, lng.getLang_speak());
			
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT emp_no FROM language ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					Integer newNum = rs.getInt(1);
					
					return new Language(newNum, 
							lng.getLang_name(),
							lng.getLang_test(),
							lng.getLang_score(),
							lng.getLang_date(),
							lng.getLang_read(),
							lng.getLang_listen(),
							lng.getLang_speak()
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
	
	public Language selectByNo(Connection conn, String no) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from language where emp_no=?");
	         pstmt.setString(1, no);
	         rs = pstmt.executeQuery();
	         Language language = null;
	         if(rs.next()) {
	        	 language = convertLanguage(rs);
	         }
	         return language;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	public List<Language> selectList(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM Language where emp_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			List<Language> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertLanguage(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Language convertLanguage(ResultSet rs) throws SQLException {
		return new Language(
				rs.getInt("emp_no"),
                rs.getString("lang_name"),
                rs.getString("lang_test"),
                rs.getString("lang_score"),
                rs.getString("lang_date"),
                rs.getString("lang_read"),
                rs.getString("lang_listen"),
                rs.getString("lang_speak")
				);
	}
}