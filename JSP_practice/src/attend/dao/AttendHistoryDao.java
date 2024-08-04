package attend.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import attend.model.AttendHistory;
import jdbc.JdbcUtil;
import personnel.model.Employee;


public class AttendHistoryDao {
	
	public AttendHistory insert(Connection conn, AttendHistory ah) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("insert into attend_history values("
					+ "?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, ah.getEmp_no());
			pstmt.setDate(2,new Date(ah.getAtth_inserted().getTime()));
			pstmt.setString(3, ah.getAtth_name());
			pstmt.setDate(4,new Date(ah.getAtth_start().getTime()));
			pstmt.setDate(5,new Date(ah.getAtth_end().getTime()));
			pstmt.setInt(6,ah.getAtth_days());
			pstmt.setString(7, ah.getAtth_cost());
			pstmt.setString(8, ah.getAtth_note());

			int insertedCount = pstmt.executeUpdate();
			// 쿼리를 실행하고 영향을 받은 레코드 수를 반환받음
			
			if(insertedCount > 0) { // 입력이 정상적으로 수행되었다면
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * "
						+ "FROM( SELECT emp_no FROM attend_history"
						+ " ORDER BY ROWNUM DESC )"			// article_no을 내림차순 정렬한 뒤
						+ "WHERE ROWNUM = 1");				// 첫번째 열 정보를 선택
		
				if(rs.next()) {
					// ah쿼리문에 첫번째필드를 받아옴
					Integer emp_no = rs.getInt(1);
					// DB에 저장된 내용과 같은 Article객체를 만들어 반환
					return new AttendHistory(
							emp_no,
							ah.getAtth_inserted(),
							ah.getAtth_name(),
							ah.getAtth_start(),
							ah.getAtth_end(),
							ah.getAtth_days(),
							ah.getAtth_cost(),
							ah.getAtth_note());
				}
			}
			return null;		// 입력이 수행되지 않았다면 null 반환
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	
	public List<AttendHistory>selectAll(Connection conn)throws SQLException{

		PreparedStatement pstmt=null;
		ResultSet rs=null;
	
		try {
			pstmt = conn.prepareStatement("select * from attend_history");
			rs=pstmt.executeQuery();
			
			List<AttendHistory>result=new ArrayList<>();
			while(rs.next()) {
				result.add(convertArticle(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private AttendHistory convertArticle(ResultSet rs)throws SQLException {
		return new AttendHistory(
				rs.getInt("emp_no"),
				rs.getDate("atth_inserted"),
                rs.getString("atth_name"),
                rs.getDate("atth_start"),
                rs.getDate("atth_end"),
                rs.getInt("atth_days"),
                rs.getString("atth_cost"),
                rs.getString("atth_note")
				);
		
	}
	
}