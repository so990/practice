package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.RetireDao;
import personnel.model.Retire;

public class InsertRetireService {
	private RetireDao retireDao = new RetireDao();
	
	public Retire insert(RetireRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			Retire retire = toRetire(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			Retire savedRetire = retireDao.insert(conn, retire);
			if(savedRetire == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert Retire");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedRetire;
			
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
	
	private Retire toRetire(RetireRequest req) {
		return new Retire(
				req.getEmp_no(),
				req.getRetire_type(),
				req.getRetired_date(),
				req.getRetire_reason(),
				req.getRetire_phone(),
				req.getRetire_cost());
	}
	
}