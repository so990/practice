package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.LicenseDao;
import personnel.model.License;

public class InsertLicenseService {
	private LicenseDao licenseDao = new LicenseDao();
	
	public License insert(LicenseRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			License license = toLicense(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			License savedLicense = licenseDao.insert(conn, license);
			if(savedLicense == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert License");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedLicense;
			
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
	
	private License toLicense(LicenseRequest req) {
		return new License(
				req.getEmp_no(),
				req.getLsc_name(),
				req.getLsc_date(),
				req.getLsc_dep(),
				req.getLsc_num(),
				req.getLsc_note());
	}
	
}