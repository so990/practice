package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.FamilyDao;
import personnel.model.Family;

public class InsertFamilyService {
	private FamilyDao familyDao = new FamilyDao();
	
	public Family insert(FamilyRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			Family family = toFamily(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			Family savedFamily = familyDao.insert(conn, family);
			if(savedFamily == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert family");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedFamily;
			
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
	
	private Family toFamily(FamilyRequest req) {
		return new Family(
				req.getEmp_no(),
				req.getF_relation(),
				req.getF_name(),
				req.getF_nationality(),
				req.getF_id_num(),
				req.getF_disability(),
				req.getF_per_deduction(),
				req.getF_heal_insur(),
				req.getF_live(),
				req.getF_gapgeunse(),
				req.getF_child());
	}
	
}