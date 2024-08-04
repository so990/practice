package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import personnel.dao.EmployeeDao;
import personnel.model.EmpSetting;
import personnel.model.Employee;

public class SelectEmployeeService {
	private EmployeeDao employeeDao = new EmployeeDao();

	public Employee select(int emp_no) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			// 해당 bs_num의 객체를 받아옴
			Employee employee = employeeDao.selectByNo(conn, emp_no);
			return employee;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int selectLastNo() {
		try (Connection conn = ConnectionProvider.getConnection()) {

			// 해당 bs_num의 객체를 받아옴
			int cnt = employeeDao.selectCount(conn);

			return cnt;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Employee> selectAll() {
	      try(Connection conn = ConnectionProvider.getConnection()) {
	         
	         
	         List<Employee> emp_list = employeeDao.selectAll(conn);         
	         return emp_list;
	         
	      }catch(SQLException e) {
	         throw new RuntimeException(e);
	      }
	   }
	
	public List<EmpSetting> selectSet() {
		try (Connection conn = ConnectionProvider.getConnection()) {

			List<EmpSetting> emp_set = employeeDao.selectSet(conn);
			return emp_set;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}