package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import personnel.dao.InsuranceDao;
import personnel.model.Insurance;

public class SelectInsuranceService {
	private InsuranceDao insuranceDao = new InsuranceDao();
	
	public Insurance select(int emp_no) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//해당 bs_num의 객체를 받아옴
			Insurance insurance = insuranceDao.selectByNo(conn, emp_no);			
			return insurance;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}