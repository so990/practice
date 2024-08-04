package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.StudyDao;
import personnel.model.Study;

public class InsertStudyService {
	private StudyDao studyDao = new StudyDao();
	
	public Study insert(StudyRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			Study study = toStudy(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			Study savedStudy = studyDao.insert(conn, study);
			if(savedStudy == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert study");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedStudy;
			
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	private Study toStudy(StudyRequest req) {
		return new Study(
				req.getEmp_no(),
				req.getStudy_type(),
				req.getStudy_name(),
				req.getStudy_start(),
				req.getStudy_end(),
				req.getStudy_dep(),
				req.getStudy_cost(),
				req.getStudy_refund());
	}
	
}