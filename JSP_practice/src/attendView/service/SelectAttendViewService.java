package attendView.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import attendView.dao.AttendViewDao;
import attendView.model.AttendView;
import jdbc.connection.ConnectionProvider;


public class SelectAttendViewService {
	
	private AttendViewDao attendDao = new AttendViewDao();

	   public List<AttendView> select() {
	      try(Connection conn = ConnectionProvider.getConnection()) {

	         List<AttendView> ai = attendDao.selectAll(conn);   
	         
	         return ai;

	      }catch(SQLException e) {
	         throw new RuntimeException(e);
	      }
	   }


}
