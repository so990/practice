package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.InsuranceDao;
import personnel.model.Insurance;

public class InsertInsuranceService {
	private InsuranceDao insuranceDao = new InsuranceDao();
	
	public Insurance insert(InsuranceRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			Insurance insurance = toInsurance(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			Insurance savedInsurance = insuranceDao.insert(conn, insurance);
			if(savedInsurance == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert insurance");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedInsurance;
			
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
	
	private Insurance toInsurance(InsuranceRequest req) {
		return new Insurance(
				req.getEmp_no(),
				req.getPension(),
				req.getInsur_heal(),
				req.getInsur_care(),
				req.getInsur_hire(),
				req.getGapgeunse(),
				req.getWage_earner_per(),
				req.getYouth_red(),
				req.getDurunuri(),
				req.getSalary(),
				req.getPension_month(),
				req.getHeal_month(),
				req.getHire_month(),
				req.getSalary_bank(),
				req.getSalary_account(),
				req.getPension_num(),
				req.getPension_start(),
				req.getPension_end(),
				req.getHeal_num(),
				req.getHeal_start(),
				req.getHeal_end(),
				req.getHire_num(),
				req.getHire_start(),
				req.getHire_end(),
				req.getIndus_num(),
				req.getIndus_start(),
				req.getIndus_end());
	}
	
}