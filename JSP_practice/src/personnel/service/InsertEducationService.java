package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.EducationDao;
import personnel.model.Education;

public class InsertEducationService {
	private EducationDao educationDao = new EducationDao();
	
	public Education insert(EducationRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			Education education = toEducation(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			Education savedEducation = educationDao.insert(conn, education);
			if(savedEducation == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert education");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedEducation;
			
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
	
	private Education toEducation(EducationRequest req) {
		return new Education(
				req.getEmp_no(),
				req.getSchool(),
				req.getSchool_start(),
				req.getSchool_end(),
				req.getSchool_name(),
				req.getSchool_major(),
				req.getSchool_state());
	}
	
}