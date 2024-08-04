package attend.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import attend.dao.AttendHistoryDao;
import attend.model.AttendHistory;
import jdbc.connection.ConnectionProvider;


public class AttendHistorySelectService {
	
	private AttendHistoryDao attendDao = new AttendHistoryDao();

	   public List<AttendHistory> select() {
	      try(Connection conn = ConnectionProvider.getConnection()) {

	         List<AttendHistory> ai = attendDao.selectAll(conn);   
	         
	         return ai;

	      }catch(SQLException e) {
	         throw new RuntimeException(e);
	      }
	   }


}
