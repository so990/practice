package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.LanguageDao;
import personnel.model.Language;

public class InsertLanguageService {
	private LanguageDao languageDao = new LanguageDao();
	
	public Language insert(LanguageRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			Language language = toLanguage(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			Language savedLanguage = languageDao.insert(conn, language);
			if(savedLanguage == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert language");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedLanguage;
			
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
	
	private Language toLanguage(LanguageRequest req) {
		return new Language(
				req.getEmp_no(),
				req.getLang_name(),
				req.getLang_test(),
				req.getLang_score(),
				req.getLang_date(),
				req.getLang_read(),
				req.getLang_listen(),
				req.getLang_speak());
	}
	
}