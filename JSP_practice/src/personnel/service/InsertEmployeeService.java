package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.EmployeeDao;
import personnel.model.Employee;

public class InsertEmployeeService {
	private EmployeeDao employeeDao = new EmployeeDao();
	
	public Employee insert(EmployeeRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			Employee employee = toEmployee(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			Employee savedEmployee = employeeDao.insert(conn, employee);
			if(savedEmployee == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert employ");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedEmployee;
			
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
	
	private Employee toEmployee(EmployeeRequest req) {
		return new Employee(
				req.getEmp_no(),
				req.getEmp_type(),
				req.getName_kor(),
				req.getName_eng(),
				req.getHired_date(),
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