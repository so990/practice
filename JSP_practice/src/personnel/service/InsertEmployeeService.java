package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.EmployeeDao;
import personnel.model.Employee;

public class InsertEmployeeService {
	
	private EmployeeDao employeeDao = new EmployeeDao();
	
	public int insert(EmployeeRequest req) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Employee employee = toEmployee(req);
			Employee savedEmployee= employeeDao.insert(conn, employee);
			
			if(savedEmployee == null) {
				throw new RuntimeException("fail to insert employee");
			}
			
			conn.commit();
			
			return savedEmployee.getEmp_no();
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	private Employee toEmployee(EmployeeRequest req) {
		return new Employee (
				req.getEmp_no(),
				req.getEmp_type(),
				req.getName_kor(),
				req.getName_eng(),
				req.getHired_date(),
				req.getRetired_date(),
				req.getDept(),
				req.getJob(),
				req.getState(),
				req.getNationality(),
				req.getId_number(),
				req.getPost_code(),
				req.getAddr(),
				req.getHome_number(),
				req.getPhone(),
				req.getEmail(),
				req.getSns(),
				req.getNote(),
				req.getBank(),
				req.getAccount());
	}
}
