package company.service;

import java.sql.Connection;
import java.sql.SQLException;

import company.model.Company;
import company.service.CompanyRequest;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import company.dao.CompanyDao;

public class InsertCompanyService {
	private CompanyDao companyDao = new CompanyDao();
	
	public Company insert(CompanyRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			Company company = toCompany(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			Company savedCompany = companyDao.insert(conn, company);
			if(savedCompany == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert company");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedCompany;
			
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
	
	private Company toCompany(CompanyRequest req) {
		return new Company(
				req.getCp_name(),
				req.getCeo_job(),
				req.getCeo_name(),
				req.getBs_num(),
				req.getBs_regnum(),
				req.getFounded_date(),
				req.getHp(),
				req.getBs_post(),
				req.getBs_addr(),
				req.getBs_phone(),
				req.getBs_fax(),
				req.getBs_type(),
				req.getCp_type(),
				req.getCalc_start(),
				req.getCalc_end(),
				req.getPayday(),
				req.getBs_bank(),
				req.getBs_account(),
				req.getBs_acc_name());
	}
	
}