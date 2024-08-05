package attvac_items.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import attvac_items.dao.Vacation_itemsDao;
import attvac_items.model.Vacation_days_setting;
import attvac_items.model.Vacation_items;
import jdbc.connection.ConnectionProvider;
import payded_items.model.Deduction_items;


public class SelectVacation_itemsService {
	
	private Vacation_itemsDao vacation_itemsDao = new Vacation_itemsDao();
	
	public List<Vacation_items> select() {
		try(Connection conn = ConnectionProvider.getConnection()) {

			List<Vacation_items> vacation_items = vacation_itemsDao.selectAll(conn);	
			
			return vacation_items;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Vacation_days_setting> selectM() {
		try(Connection conn = ConnectionProvider.getConnection()) {

			List<Vacation_days_setting> vacation_days_setting = vacation_itemsDao.selectModal(conn);	
			
			return vacation_days_setting;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Vacation_items selectbyName(String name) {
		try(Connection conn = ConnectionProvider.getConnection()) {

			Vacation_items vacation_items = vacation_itemsDao.selectByName(conn, name);
			
			return vacation_items;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}