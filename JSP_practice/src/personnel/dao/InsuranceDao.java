package personnel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import personnel.model.Appointment;
import personnel.model.Employee;
import personnel.model.Insurance;

public class InsuranceDao {
	
	public Insurance insert(Connection conn, Insurance ins) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into insurance values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
			pstmt.setInt(1, ins.getEmp_no());
			pstmt.setString(2, ins.getPension());
			pstmt.setString(3, ins.getInsur_heal());
			pstmt.setString(4, ins.getInsur_heal_per());
			pstmt.setString(5, ins.getInsur_care());
			pstmt.setString(6, ins.getInsure_care_per());
			pstmt.setString(7, ins.getInsur_hire());
			pstmt.setString(8, ins.getGapgeunse());
			pstmt.setString(9, ins.getWage_earner_per());
			pstmt.setString(10, ins.getYouth_red());
			pstmt.setString(11, ins.getYouth_red_per());
			pstmt.setString(12, ins.getDurunuri());
			pstmt.setString(13, ins.getSalary());
			pstmt.setString(14, ins.getPension_month());
			pstmt.setString(15, ins.getHeal_month());
			pstmt.setString(16, ins.getHire_month());
			pstmt.setString(17, ins.getSalary_bank());
			pstmt.setString(18, ins.getSalary_account());
			pstmt.setString(19, ins.getPension_num());
			pstmt.setString(20, ins.getPension_start());
			pstmt.setString(21, ins.getPension_end());
			pstmt.setString(22, ins.getHeal_num());
			pstmt.setString(23, ins.getHeal_start());
			pstmt.setString(24, ins.getHeal_end());
			pstmt.setString(25, ins.getHire_num());
			pstmt.setString(26, ins.getHire_start());
			pstmt.setString(27, ins.getHire_end());
			pstmt.setString(28, ins.getIndus_num());
			pstmt.setString(29, ins.getIndus_start());
			pstmt.setString(30, ins.getIndus_end());
			
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT emp_no FROM insurance ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					Integer newNum = rs.getInt(1);
					
					return new Insurance(newNum, 
							ins.getPension(),
							ins.getInsur_heal(),
							ins.getInsur_heal_per(),
							ins.getInsur_care(),
							ins.getInsure_care_per(),
							ins.getInsur_hire(),
							ins.getGapgeunse(),
							ins.getWage_earner_per(),
							ins.getYouth_red(),
							ins.getYouth_red_per(),
							ins.getDurunuri(),
							ins.getSalary(),
							ins.getPension_month(),
							ins.getHeal_month(),
							ins.getHire_month(),
							ins.getSalary_bank(),
							ins.getSalary_account(),
							ins.getPension_num(),
							ins.getPension_start(),
							ins.getPension_end(),
							ins.getHeal_num(),
							ins.getHeal_start(),
							ins.getHeal_end(),
							ins.getHire_num(),
							ins.getHire_start(),
							ins.getHire_end(),
							ins.getIndus_num(),
							ins.getIndus_start(),
							ins.getIndus_end()
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
	
	public Insurance selectByNo(Connection conn, String no) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from insurance where emp_no=?");
	         pstmt.setString(1, no);
	         rs = pstmt.executeQuery();
	         Insurance insurance = null;
	         if(rs.next()) {
	        	 insurance = convertInsurance(rs);
	         }
	         return insurance;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	private Insurance convertInsurance(ResultSet rs) throws SQLException {
		return new Insurance(
				rs.getInt("emp_no"),
                rs.getString("pension"),
                rs.getString("insur_heal"),
                rs.getString("insur_heal_per"),
                rs.getString("insur_care"),
                rs.getString("insure_care_per"),
                rs.getString("insur_hire"),
                rs.getString("gapgeunse"),
                rs.getString("wage_earner_per"),
                rs.getString("youth_red"),
                rs.getString("youth_red_per"),
                rs.getString("durunuri"),
                rs.getString("salary"),
                rs.getString("pension_month"),
                rs.getString("heal_month"),
                rs.getString("hire_month"),
                rs.getString("salary_bank"),
                rs.getString("salary_account"),
                rs.getString("pension_num"),
                rs.getString("pension_start"),
                rs.getString("pension_end"),
                rs.getString("heal_num"),
                rs.getString("heal_start"),
                rs.getString("heal_end"),
                rs.getString("hire_num"),
                rs.getString("hire_start"),
                rs.getString("hire_end"),
                rs.getString("indus_num"),
                rs.getString("indus_start"),
                rs.getString("indus_end")
				);
	}
}
