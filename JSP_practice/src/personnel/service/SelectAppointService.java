package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import personnel.dao.AppointmentDao;
import personnel.model.Appointment;

public class SelectAppointService {
	private AppointmentDao appointmentDao = new AppointmentDao();
	
	public List<Appointment> selectListByNo(int emp_no) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//해당 bs_num의 객체를 받아옴
			List<Appointment> appointment = appointmentDao.selectList(conn, emp_no);			
			return appointment;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}