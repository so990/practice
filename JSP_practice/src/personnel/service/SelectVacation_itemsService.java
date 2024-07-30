package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import company.model.Company;
import jdbc.connection.ConnectionProvider;
import personnel.dao.Vacation_itemsDao;
import personnel.model.Vacation_items;

public class SelectVacation_itemsService {
	private Vacation_itemsDao vacation_itemsDao = new Vacation_itemsDao();
	
	public Vacation_items select(String name) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//해당 bs_num의 객체를 받아옴
			Vacation_items vacation_items = Vacation_itemsDao.selectByName(conn, name);			
			return vacation_items;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
