package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import personnel.dao.RetireDao;
import personnel.model.Retire;

public class SelectRetireService {
	private RetireDao retireDao = new RetireDao();
	
	public Retire select(int emp_no) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//해당 bs_num의 객체를 받아옴
			Retire retire = retireDao.selectByNo(conn, emp_no);
			return retire;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}