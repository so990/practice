package issuing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import issuing.model.Issuing;
import jdbc.JdbcUtil;

public class IssuingDao {
	
	public Issuing insert(Connection conn, Issuing isu) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into issuing values(?,?,?,?,?)");
			pstmt.setInt(1, isu.getEmp_no());
			pstmt.setInt(2, isu.getIsu_num());
			pstmt.setString(3, isu.getIsu_led());
			pstmt.setString(4, isu.getIsu_pur());
			pstmt.setDate(5, isu.getIsu_date());

			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT emp_no FROM issuing ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					Integer newNum = rs.getInt(1);
					
					return new Issuing(newNum,
							isu.getIsu_num(),
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
	
	public List<Issuing> selectAll(Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement("SELECT * FROM issuing");
            rs = pstmt.executeQuery();
            List<Issuing> result = new ArrayList<>();
            while (rs.next()) {
                result.add(convertIssuing(rs));
            }
            return result;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }
	
	public Issuing selectByNum(Connection conn, int num) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from issuing where isu_num=?");
	         pstmt.setInt(1, num);
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
	
	public int selectCount(Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * "
					+ "FROM( SELECT isu_num FROM issuing"
					+ " ORDER BY ROWNUM DESC )"			// article_no을 내림차순 정렬한 뒤
					+ "WHERE ROWNUM = 1");				// 첫번째 열 정보를 선택
	
			if(rs.next()) {
                return rs.getInt(2);
            }
            return 0;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
        }
    }

	private Issuing convertIssuing(ResultSet rs) throws SQLException {
		return new Issuing(
				rs.getInt("emp_no"),
				rs.getInt("isu_num"),
                rs.getString("isu_led"),
                rs.getString("isu_pur"),
                rs.getDate("isu_date")
				);
	}
}