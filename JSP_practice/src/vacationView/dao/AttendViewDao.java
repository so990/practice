package vacationView.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import attend.model.AttendHistory;
import attendView.model.AttendView;
import jdbc.JdbcUtil;


public class AttendViewDao {
	
	public List<AttendView>selectAll(Connection conn)throws SQLException{

		PreparedStatement pstmt=null;
		ResultSet rs=null;
	
		try {
			pstmt = conn.prepareStatement("select a.atth_inserted, a.emp_no , a.atth_name, a.atth_start, atth_end, a.atth_days, a.atth_cost, a.atth_note, e.emp_type, e.name_kor, e.dept, e.job from attend_history a left outer join employee e on (a.emp_no=e.emp_no)");
			rs=pstmt.executeQuery();
			
			List<AttendView>result=new ArrayList<>();
			while(rs.next()) {
				result.add(convertArticle(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private AttendView convertArticle(ResultSet rs)throws SQLException {
		return new AttendView(
				rs.getInt("emp_no"),
				rs.getDate("atth_inserted"),
				rs.getString("emp_type"),
				rs.getString("name_kor"),
				rs.getString("dept"),
				rs.getString("job"),
                rs.getString("atth_name"),
                rs.getDate("atth_start"),
                rs.getDate("atth_end"),
                rs.getInt("atth_days"),
                rs.getString("atth_cost"),
                rs.getString("atth_note")
				);
		
	}
	
}