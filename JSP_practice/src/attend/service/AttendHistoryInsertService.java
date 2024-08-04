package attend.service;

import java.sql.Connection;
import java.sql.SQLException;

import attend.dao.AttendHistoryDao;
import attend.model.AttendHistory;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class AttendHistoryInsertService {

	private AttendHistoryDao ahDao = new AttendHistoryDao();
	
	public AttendHistory insert(AttendManageRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			AttendHistory company = toAttend(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			AttendHistory savedAH = ahDao.insert(conn, company);
			if(savedAH == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert attend");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedAH;
			
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
	
	private AttendHistory toAttend(AttendManageRequest req) {
		return new AttendHistory(
				req.getEmp_no(),
				req.getAtth_inserted(),
				req.getAtth_name(),
				req.getAtth_start(),
				req.getAtth_end(),
				req.getAtth_days(),
				req.getAtth_cost(),
				req.getAtth_note()
				);
	}
}	
