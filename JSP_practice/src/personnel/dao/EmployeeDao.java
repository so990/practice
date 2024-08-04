package personnel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import attvac_items.model.Vacation_days_setting;
import jdbc.JdbcUtil;
import personnel.model.EmpSetting;
import personnel.model.Employee;

public class EmployeeDao {
	
	public Employee insert(Connection conn, Employee emp) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, emp.getEmp_no());
			pstmt.setString(2, emp.getEmp_type());
			pstmt.setString(3, emp.getName_kor());
			pstmt.setString(4, emp.getName_eng());
			pstmt.setTimestamp(5, toTimestamp(emp.getHired_date()));
			pstmt.setString(6, emp.getDept());
			pstmt.setString(7, emp.getJob());
			pstmt.setString(8, emp.getState());
			pstmt.setString(9, emp.getNationality());
			pstmt.setString(10, emp.getId_number());
			pstmt.setString(11, emp.getPost_code());
			pstmt.setString(12, emp.getAddr());
			pstmt.setString(13, emp.getHome_number());
			pstmt.setString(14, emp.getPhone());
			pstmt.setString(15, emp.getEmail());
			pstmt.setString(16, emp.getSns());
			pstmt.setString(17, emp.getNote());
			pstmt.setString(18, emp.getBank());
			pstmt.setString(19, emp.getAccount());
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT emp_no FROM employee ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					Integer newNum = rs.getInt(1);
					
					return new Employee(newNum, 
							emp.getEmp_type(),
							emp.getName_kor(),
							emp.getName_eng(),
							emp.getHired_date(),
							emp.getDept(),
							emp.getJob(),
							emp.getState(),
							emp.getNationality(),
							emp.getId_number(),
							emp.getPost_code(),
							emp.getAddr(),
							emp.getHome_number(),
							emp.getPhone(),
							emp.getEmail(),
							emp.getSns(),
							emp.getNote(),
							emp.getBank(),
							emp.getAccount()							
							);	
				}
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	 public Employee selectByNo(Connection conn, int no) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from employee where emp_no=?");
	         pstmt.setInt(1, no);
	         rs = pstmt.executeQuery();
	         Employee employee = null;
	         if(rs.next()) {
	        	 employee = convertEmployee(rs);
	         }
	         return employee;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }

	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	// 직원 정보의 수를 반환하는 메소드
    public int selectCount(Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM employee");
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
        }
    }

    // 직원 정보를 페이지네이션하여 반환하는 메소드
    public List<Employee> select(Connection conn, int firstRow, int endRow) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(
                "SELECT * FROM (SELECT ROWNUM AS rnum, e.* FROM (SELECT * FROM employee ORDER BY emp_no DESC) e WHERE ROWNUM <= ?) WHERE rnum >= ?"
            );
            pstmt.setInt(1, endRow);
            pstmt.setInt(2, firstRow);
            rs = pstmt.executeQuery();
            List<Employee> result = new ArrayList<>();
            while (rs.next()) {
                result.add(convertEmployee(rs));
            }
            return result;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    // 모든 직원 정보를 반환하는 메소드
    public List<Employee> selectAll(Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement("SELECT * FROM employee");
            rs = pstmt.executeQuery();
            List<Employee> result = new ArrayList<>();
            while (rs.next()) {
                result.add(convertEmployee(rs));
            }
            return result;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }
    
    //사원현황 관리
    public List<EmpSetting> selectSet(Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement("SELECT e.emp_type, e.hired_date, e.emp_no, e.name_kor, e.dept, e.job, e.id_number, e.phone, e.email, r.retired_date, e.state FROM employee e LEFT OUTER JOIN retire r ON (e.emp_no = r.emp_no)");
            rs = pstmt.executeQuery();
            List<EmpSetting> result = new ArrayList<>();
            while (rs.next()) {
                result.add(convertEmpSetting(rs));
            }
            return result;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }
    
    private Employee convertEmployee(ResultSet rs) throws SQLException {
		return new Employee(
                rs.getInt("emp_no"),
                rs.getString("emp_type"),
                rs.getString("name_kor"),
                rs.getString("name_eng"),
                rs.getDate("hired_date"),
                rs.getString("dept"),
                rs.getString("job"),
                rs.getString("state"),
                rs.getString("nationality"),
                rs.getString("id_number"),
                rs.getString("post_code"),
                rs.getString("addr"),
                rs.getString("home_number"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getString("sns"),
                rs.getString("note"),
                rs.getString("bank"),
                rs.getString("account")
                );
	}
    
    private EmpSetting convertEmpSetting(ResultSet rs) throws SQLException {
		return new EmpSetting(
                rs.getString("emp_type"),
                rs.getDate("hired_date"),
                rs.getInt("emp_no"),
                rs.getString("name_kor"),
                rs.getString("dept"),
                rs.getString("job"),
                rs.getString("id_number"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getDate("retired_date"),
                rs.getString("state")
                );
	}

}