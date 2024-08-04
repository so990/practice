package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.CareerDao;
import personnel.model.Career;

public class InsertCareerService {
	private CareerDao careerDao = new CareerDao();
	
	public Career insert(CareerRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			Career career = toCareer(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			Career savedCareer = careerDao.insert(conn, career);
			if(savedCareer == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert career");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedCareer;
			
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
	
	private Career toCareer(CareerRequest req) {
		return new Career(
				req.getEmp_no(),
				req.getFirm(),
				req.getFirm_start(),
				req.getFirm_end(),
				req.getFirm_term(),
				req.getFirm_job(),
				req.getFirm_task(),
				req.getFirm_retire());
	}
	
}