package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.MilitaryDao;
import personnel.model.Military;

public class InsertMilitaryService {
	private MilitaryDao militaryDao = new MilitaryDao();
	
	public Military insert(MilitaryRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			Military military = toMilitary(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			Military savedMilitary = militaryDao.insert(conn, military);
			if(savedMilitary == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert military");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedMilitary;
			
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
	
	private Military toMilitary(MilitaryRequest req) {
		return new Military(
				req.getEmp_no(),
				req.getMil(),
				req.getMil_type(),
				req.getMil_start(),
				req.getMil_end(),
				req.getMil_rank(),
				req.getMil_job(),
				req.getMil_no_reason());
	}
	
}