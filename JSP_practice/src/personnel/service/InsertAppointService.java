package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.AppointmentDao;
import personnel.model.Appointment;

public class InsertAppointService {
	private AppointmentDao appointmentDao = new AppointmentDao();
	
	public Appointment insert(AppointmentRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			Appointment appointment = toAppointment(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			Appointment savedAppointment = appointmentDao.insert(conn, appointment);
			if(savedAppointment == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert appointment");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedAppointment;
			
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	private Appointment toAppointment(AppointmentRequest req) {
		return new Appointment(
				req.getEmp_no(),
				req.getAppo_type(),
				req.getAppo_date(),
				req.getAppo_dep(),
				req.getAppo_job(),
				req.getAppo_task(),
				req.getAppo_note());
	}
	
}