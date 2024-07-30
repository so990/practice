package company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import company.model.Company;
import jdbc.JdbcUtil;
import company.model.Company;

public class CompanyDao {
	public Company selectByBsNo(Connection conn, String bs_num)
			throws SQLException{ //해당아이디를 가진 company형 객체를 반환하는 매서드
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from company where bs_num = ?");
			pstmt.setString(1, bs_num);				// 쿼리문 만들고 ?에 값 대입
			rs = pstmt.executeQuery();			// rs에 쿼리문 실행결과를 저장
			Company company = null;
			if(rs.next()) {
				// rs에 저장된 정보를 이용하여 company 객체 초기화
				company = new Company(
						rs.getString("cp_name"),
						rs.getString("ceo_job"),
						rs.getString("ceo_name"),
						rs.getString("bs_num"),
						rs.getString("bs_regnum"),
						toDate(rs.getTimestamp("founded_date")),
						rs.getString("hp"),
						rs.getString("bs_post"),
						rs.getString("bs_addr"),
						rs.getString("bs_phone"),
						rs.getString("bs_fax"),
						rs.getString("bs_type"),
						rs.getString("cp_type"),
						toDate(rs.getTimestamp("calc_start")),
						toDate(rs.getTimestamp("calc_end")),
						toDate(rs.getTimestamp("payday")),
						rs.getString("bs_bank"),
						rs.getString("bs_account"),
						rs.getString("bs_acc_name"));
			}
			return company;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Date toDate(Timestamp date) {
		//Timestamp 객체 date가 null이 아니면 Date 객체로 바꾸어 반환 
		return date == null ? null : new Date(date.getTime());
	}
	
	public Company insert(Connection conn, Company cpn) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("insert into company values("
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, cpn.getCp_name());
			pstmt.setString(2, cpn.getCeo_job());
			pstmt.setString(3, cpn.getCeo_name());
			pstmt.setString(4, cpn.getBs_num());
			pstmt.setString(5, cpn.getBs_regnum());
			if(cpn.getFounded_date() == null)
				pstmt.setTimestamp(6, null);
			else pstmt.setTimestamp(6, new Timestamp(cpn.getFounded_date().getTime()));
			pstmt.setString(7, cpn.getHp());
			pstmt.setString(8, cpn.getBs_post());
			pstmt.setString(9, cpn.getBs_addr());
			pstmt.setString(10, cpn.getBs_phone());
			pstmt.setString(11, cpn.getBs_fax());
			pstmt.setString(12, cpn.getBs_type());
			pstmt.setString(13, cpn.getCp_type());
			if(cpn.getCalc_start() == null)
				pstmt.setTimestamp(14, null);
			else pstmt.setTimestamp(14, new Timestamp(cpn.getCalc_start().getTime()));
			if(cpn.getCalc_end() == null)
				pstmt.setTimestamp(15, null);
			else pstmt.setTimestamp(15, new Timestamp(cpn.getCalc_end().getTime()));
			if(cpn.getPayday() == null)
				pstmt.setTimestamp(16, null);
			else pstmt.setTimestamp(16, new Timestamp(cpn.getPayday().getTime()));
			pstmt.setString(17, cpn.getBs_bank());
			pstmt.setString(18, cpn.getBs_account());
			pstmt.setString(19, cpn.getBs_acc_name());
			
			int insertedCount = pstmt.executeUpdate();
			// 쿼리를 실행하고 영향을 받은 레코드 수를 반환받음
			
			if(insertedCount > 0) { // 입력이 정상적으로 수행되었다면
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * "
						+ "FROM( SELECT cp_name FROM company"
						+ " ORDER BY ROWNUM DESC )"			// article_no을 내림차순 정렬한 뒤
						+ "WHERE ROWNUM = 1");				// 첫번째 열 정보를 선택
		
				if(rs.next()) {
					// rs쿼리문에 첫번째필드를 받아옴
					String cp_name = rs.getString(1);
					// DB에 저장된 내용과 같은 Article객체를 만들어 반환
					return new Company(
							cp_name,
							cpn.getCeo_job(),
							cpn.getCeo_name(),
							cpn.getBs_num(),
							cpn.getBs_regnum(),
							cpn.getFounded_date(),
							cpn.getHp(),
							cpn.getBs_post(),
							cpn.getBs_addr(),
							cpn.getBs_phone(),
							cpn.getBs_fax(),
							cpn.getBs_type(),
							cpn.getCp_type(),
							cpn.getCalc_start(),
							cpn.getCalc_end(),
							cpn.getPayday(),
							cpn.getBs_bank(),
							cpn.getBs_account(),
							cpn.getBs_acc_name()
							);
				}
			}
			return null;		// 입력이 수행되지 않았다면 null 반환
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	/*
	 * public void update(Connection conn, Company cpn) throws SQLException{
	 * //Company 객체의 이름과 비밀번호를 수정하는 매서드 try(PreparedStatement pstmt =
	 * conn.prepareStatement(
	 * "update member set name = ?, password = ? where memberid = ?")){
	 * pstmt.setString(1, member.getName()); pstmt.setString(2,
	 * member.getPassword()); pstmt.setString(3, member.getId());
	 * pstmt.executeUpdate(); } }
	 */
}
