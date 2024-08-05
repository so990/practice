package issuing.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import issuing.dao.IssuingDao;
import issuing.model.Issuing;
import jdbc.connection.ConnectionProvider;

public class SelectIssuingService {
	private IssuingDao issuingDao = new IssuingDao();
	
	public Issuing select(int emp_no) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//해당 bs_num의 객체를 받아옴
			Issuing issuing = issuingDao.selectByNum(conn, emp_no);			
			return issuing;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Issuing> selectAll() {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			
			List<Issuing> emp_list = issuingDao.selectAll(conn);			
			return emp_list;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int selectLastNo() {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//해당 bs_num의 객체를 받아옴
		int cnt = issuingDao.selectCount(conn);
			
		return cnt;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}