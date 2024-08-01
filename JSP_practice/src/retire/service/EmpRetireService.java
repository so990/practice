package retire.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import retire.dao.RetireDao;
import retire.model.OneMemberRetireRequest;
import retire.model.RetireProcessRequest;
import retire.model.SearchingRequest;

public class EmpRetireService {

	private Connection conn = null;
	private RetireDao retireDao = new RetireDao();

	// 퇴직관리 페이지에서 전체 사원을 출력할 때 사용
	public List<RetireProcessRequest> selectAll() throws SQLException {

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			List<RetireProcessRequest> list = retireDao.selectAll(conn);
			if (list != null) {
				JdbcUtil.rollback(conn);
			}
			conn.commit();
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 퇴직관리 페이지에서 검색창별 출력
	public List<RetireProcessRequest> selectSearch(SearchingRequest searchReq) throws SQLException {
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			List<RetireProcessRequest> list = retireDao.selectSearch(conn, searchReq);
			if (list != null) {
				JdbcUtil.rollback(conn);
			}
			conn.commit();
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	////// OneMemberRetireRequest
	//// 퇴사자 퇴직 처리
	// 퇴사자퇴직처리모달에서 퇴직 처리를 퇴직 테이블에 저장할 때 사용
	public void oneMemberRetireInsert(OneMemberRetireRequest omrr) throws SQLException {

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			retireDao.oneMemberRetireInsert(conn, omrr);
			retireDao.updateRetireDateToEmployeeTable(conn, omrr);

			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 퇴사자 퇴직처리 취소 모달에서 퇴직 처리를 취소할 때 사용
	public void oneMemberRetireDelete(OneMemberRetireRequest omrr) throws SQLException {

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			retireDao.oneMemberRetireDelete(conn, omrr);
			retireDao.deleteRetireDateToEmployeeTable(conn, omrr);

			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
