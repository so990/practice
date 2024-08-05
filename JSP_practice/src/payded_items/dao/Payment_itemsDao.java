package payded_items.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import attvac_items.model.Attend_items;
import jdbc.JdbcUtil;
import payded_items.model.Payment_items;

public class Payment_itemsDao {
	
	public Payment_items insert(Connection conn, Payment_items pay) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement("insert into payment_items values(?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, pay.getPay_name());
			pstmt.setString(2, pay.getPay_tax());
			pstmt.setString(3, pay.getTax_free_name());
			pstmt.setString(4, pay.getTax_free_limit());
			pstmt.setString(5, pay.getTax_memo());
			pstmt.setString(6, pay.getCut_unit());
			pstmt.setString(7, pay.getAttend_conn());
			pstmt.setString(8, pay.getPay_cost());
			pstmt.setString(9, pay.getPay_used());
				
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT*FROM(SELECT pay_name FROM payment_items ORDER BY ROWNUM DESC) WHERE ROWNUM = 1");
				if(rs.next()) {
					
					String newNum = rs.getString(1);
					return new Payment_items(newNum, 
							pay.getPay_tax(),
							pay.getTax_free_name(),
							pay.getTax_free_limit(),
							pay.getTax_memo(),
							pay.getCut_unit(),
							pay.getAttend_conn(),
							pay.getPay_cost(),
							pay.getPay_used()
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
	
	public Payment_items selectByName(Connection conn, String name) throws SQLException {
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         pstmt=conn.prepareStatement("select*from payment_items where pay_name=?");
	         pstmt.setString(1, name);
	         rs = pstmt.executeQuery();
	         Payment_items payment_items = null;
	         if(rs.next()) {
	        	 payment_items = convertPayment_items(rs);
	         }
	         return payment_items;
	      } finally {
	         JdbcUtil.close(rs);
	         JdbcUtil.close(pstmt);
	      }
	   }
	
	 public List<Payment_items> selectAll(Connection conn) throws SQLException {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            pstmt = conn.prepareStatement("SELECT * FROM payment_items");
	            rs = pstmt.executeQuery();
	            List<Payment_items> result = new ArrayList<>();
	            while (rs.next()) {
	                result.add(convertPayment_items(rs));
	            }
	            return result;
	        } finally {
	            JdbcUtil.close(rs);
	            JdbcUtil.close(pstmt);
	        }
	    }
	 
	//지급항목 삭제
	 public void delete (Connection conn, String name) throws SQLException{
			try(PreparedStatement pstmt = conn.prepareStatement(
					"delete from payment_items where pay_name =?")){
				pstmt.setString(1, name);
				pstmt.executeUpdate();
			}
		}
	
	private Payment_items convertPayment_items(ResultSet rs) throws SQLException {
		return new Payment_items(
				rs.getString("pay_name"),        
                rs.getString("pay_tax"),
                rs.getString("tax_free_name"),
                rs.getString("tax_free_limit"),
                rs.getString("tax_memo"),
                rs.getString("cut_unit"),
                rs.getString("attend_conn"),
                rs.getString("pay_cost"),
                rs.getString("pay_used")
				);
	}
}