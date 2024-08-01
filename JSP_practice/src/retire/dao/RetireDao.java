package retire.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import retire.model.OneMemberRetireRequest;
import retire.model.RetireProcessRequest;
import retire.model.SearchingRequest;

public class RetireDao {

	// 퇴직 관리 페이지에서 전체 사원을 출력하기
	public List<RetireProcessRequest> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(
					"select e.state, e.emp_no, e.name_kor, e.dept, e.job, e.hired_date, e.retired_date, r.retire_type, r.retire_date, r.retire_reason, r.retire_phone, rp.ret_calc_type_mid, rp.ret_calc_type_retire from employee e left outer join retire r on (e.emp_no = r.emp_no) left outer join retire_payment rp on (r.emp_no = rp.emp_no) order by dept asc");
			rs = pstmt.executeQuery();

			List<RetireProcessRequest> list = new ArrayList<>();
			RetireProcessRequest rpr = null;
			while (rs.next()) {

				// 근속연수 계산을 위한 코드
				long retired_date;
				long years_service;

				if (rs.getDate("retired_date") == null) {
					retired_date = new Date().getTime();
				} else {
					retired_date = rs.getDate("retired_date").getTime();
				}
				years_service = (retired_date - rs.getDate("hired_date").getTime()) / (365 * 24 * 60 * 60 * 1000L);

				rpr = new RetireProcessRequest(rs.getString("state"), rs.getInt("emp_no"), rs.getString("name_kor"),
						rs.getString("dept"), rs.getString("job"), rs.getDate("hired_date"), rs.getDate("retired_date"),
						years_service + "년", rs.getString("retire_type"), rs.getDate("retire_date"),
						rs.getString("retire_reason"), rs.getString("retire_phone"), rs.getString("ret_calc_type_mid"),
						rs.getString("ret_calc_type_retire"));

				list.add(rpr);
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
	//퇴직관리 페이지에서 검색창별 출력
	public List<RetireProcessRequest> selectSearch(Connection conn, SearchingRequest searchReq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String searchCategori = searchReq.getSearchCategori();
			if (searchCategori.equals("emp_no")) {searchCategori = "e.emp_no";}
			pstmt = conn.prepareStatement(
					"select e.state, e.emp_no, e.name_kor, e.dept, e.job, e.hired_date, e.retired_date, r.retire_type, r.retire_date, r.retire_reason, r.retire_phone, rp.ret_calc_type_mid, rp.ret_calc_type_retire from employee e left outer join retire r on (e.emp_no = r.emp_no) left outer join retire_payment rp on (r.emp_no = rp.emp_no) where " + searchCategori + " like ? order by dept asc");
			pstmt.setString(1,  "%"+searchReq.getSearchWord()+"%");
			rs = pstmt.executeQuery();

			List<RetireProcessRequest> list = new ArrayList<>();
			RetireProcessRequest rpr = null;
			
			
			while (rs.next()) {

				// 근속연수 계산을 위한 코드
				long retired_date;
				long years_service;

				if (rs.getDate("retired_date") == null) {
					retired_date = new Date().getTime();
				} else {
					retired_date = rs.getDate("retired_date").getTime();
				}
				years_service = (retired_date - rs.getDate("hired_date").getTime()) / (365 * 24 * 60 * 60 * 1000L);

				rpr = new RetireProcessRequest(rs.getString("state"), rs.getInt("emp_no"), rs.getString("name_kor"),
						rs.getString("dept"), rs.getString("job"), rs.getDate("hired_date"), rs.getDate("retired_date"),
						years_service + "년", rs.getString("retire_type"), rs.getDate("retire_date"),
						rs.getString("retire_reason"), rs.getString("retire_phone"), rs.getString("ret_calc_type_mid"),
						rs.getString("ret_calc_type_retire"));
				System.out.println(rpr.toString());
				
				list.add(rpr);
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
	////// OneMemberRetireRequest
	//// 퇴사자 퇴직 처리
	// 퇴사자퇴직처리모달에서 퇴직 처리 저장할 때 사용
	public void oneMemberRetireInsert(Connection conn, OneMemberRetireRequest omrr) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into retire values( ?, ?, ?, ?, ?, ?)")) {
			pstmt.setInt(1, omrr.getEmp_no());
			pstmt.setString(2, omrr.getRetire_type());
			pstmt.setTimestamp(3, new Timestamp(omrr.getRetire_date().getTime()));
			pstmt.setString(4, omrr.getRetire_reason());
			pstmt.setString(5, omrr.getRetire_phone());
			pstmt.setString(6, "");
			pstmt.executeUpdate();
		}
	}

	// 퇴직처리 모달에서 퇴직 처리 저장 시 사원 테이블에 업데이트하기
	public void updateRetireDateToEmployeeTable(Connection conn, OneMemberRetireRequest omrr) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update employee set retired_date=?, state='퇴직' where emp_no=?")) {
			pstmt.setTimestamp(1, new Timestamp(omrr.getRetire_date().getTime()));
			pstmt.setInt(2, omrr.getEmp_no());
			pstmt.executeUpdate();
		}
	}

	
	
	//// 퇴사자 퇴직 처리 취소
	
	// 퇴직처리 취소 모달에서 퇴직 취소 시 retire에서 삭제
	public void oneMemberRetireDelete(Connection conn, OneMemberRetireRequest omrr) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("delete from retire where emp_no=?")) {
			pstmt.setInt(1, omrr.getEmp_no());
			pstmt.executeUpdate();
		}
	}

	// 퇴직처리 취소 시 사원 테이블에 업데이트
	public void deleteRetireDateToEmployeeTable(Connection conn, OneMemberRetireRequest omrr) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update employee set retired_date='', state='재직' where emp_no=?")) {
			pstmt.setInt(1, omrr.getEmp_no());
			pstmt.executeUpdate();
		}
	}
}
