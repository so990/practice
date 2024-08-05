package payded_items.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import payded_items.dao.Payment_itemsDao;
import payded_items.model.Payment_items;

public class SelectPayment_itemsService {
	private Payment_itemsDao payment_itemsDao = new Payment_itemsDao();

	public List<Payment_items> select() {
		try(Connection conn = ConnectionProvider.getConnection()) {

			List<Payment_items> payment_items = payment_itemsDao.selectAll(conn);	
			
			return payment_items;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Payment_items selectbyName(String name) {
		try(Connection conn = ConnectionProvider.getConnection()) {

			Payment_items payment_items = payment_itemsDao.selectByName(conn, name);
			
			return payment_items;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}