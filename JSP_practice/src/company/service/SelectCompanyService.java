package company.service;

import java.sql.Connection;
import java.sql.SQLException;

import company.dao.CompanyDao;
import company.model.Company;
import jdbc.connection.ConnectionProvider;

public class SelectCompanyService {
	private CompanyDao companyDao = new CompanyDao();
	
	public Company select(String bs_num) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//해당 bs_num의 객체를 받아옴
			Company company = companyDao.selectByBsNo(conn, bs_num);			
			return company;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
