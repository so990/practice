package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import personnel.dao.StudyDao;
import personnel.model.Study;

public class SelectStudyService {
	private StudyDao studyDao = new StudyDao();
	
	public List<Study> selectListByNo(int emp_no) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//해당 bs_num의 객체를 받아옴
			List<Study> study = studyDao.selectList(conn, emp_no);			
			return study;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}