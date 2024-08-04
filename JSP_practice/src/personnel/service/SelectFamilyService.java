package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import personnel.dao.FamilyDao;
import personnel.model.Family;

public class SelectFamilyService {
	private FamilyDao familyDao = new FamilyDao();
	
	public List<Family> selectListByNo(int emp_no) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//해당 bs_num의 객체를 받아옴
			List<Family> family = familyDao.selectList(conn, emp_no);			
			return family;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}