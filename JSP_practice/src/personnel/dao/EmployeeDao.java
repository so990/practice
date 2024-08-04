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

import jdbc.JdbcUtil;
import personnel.model.Employee;

public class EmployeeDao {
	
	public Employee insert(Connection conn, Employee emp) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, emp.getEmp_no());
			pstmt.setString(2, emp.getEmp_type());
			pstmt.setString(3, emp.getName_kor());
			pstmt.setString(4, emp.getName_eng());
			pstmt.setTimestamp(5, toTimestamp(emp.getHired_date()));
			pstmt.setTimestamp(6, toTimestamp(emp.getRetired_date()));
			pstmt.setString(7, emp.getDept());
			pstmt.setString(8, emp.getJob());
			pstmt.setString(9, emp.getState());
			pstmt.setString(10, emp.getNationality());
			pstmt.setString(11, emp.getId_number());
			pstmt.setString(12, emp.getPost_code());
			pstmt.setString(13, emp.getAddr());
			pstmt.setString(14, emp.getHome_number());
			pstmt.setString(15, emp.getPhone());
			pstmt.setString(16, emp.getEmail());
			pstmt.setString(17, emp.getSns());
			pstmt.setString(18, emp.getNote());
			pstmt.setString(19, emp.getBank());
			pstmt.setString(20, emp.getAcoount());
			
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
							emp.getRetired_date(),
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
							emp.getAcoount()							
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
	
	 public Employee selectByNo(Connection conn, String no) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from employee where emp_no=?");
	         pstmt.setString(1, no);
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
    
    private Employee convertEmployee(ResultSet rs) throws SQLException {
		return new Employee(
                rs.getInt("emp_no"),
                rs.getString("emp_type"),
                rs.getString("name_kor"),
                rs.getString("name_eng"),
                rs.getDate("hired_date"),
                rs.getDate("retired_date"),
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

	public Employee selectByNo(Connection conn, int emp_no) {
		// TODO Auto-generated method stub
		return null;
	}
}