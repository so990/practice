package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import personnel.dao.MilitaryDao;
import personnel.model.Military;

public class SelectMilitaryService {
	private MilitaryDao militaryDao = new MilitaryDao();
	
	public Military select(int emp_no) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//해당 bs_num의 객체를 받아옴
			Military military = militaryDao.selectByNo(conn, emp_no);			
			return military;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}