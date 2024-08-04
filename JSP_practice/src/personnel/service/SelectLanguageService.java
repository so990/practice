package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import personnel.dao.LanguageDao;
import personnel.model.Language;

public class SelectLanguageService {
	private LanguageDao languageDao = new LanguageDao();
	
	public List<Language> selectListByNo(int emp_no) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//해당 bs_num의 객체를 받아옴
			List<Language> language = languageDao.selectList(conn, emp_no);			
			return language;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}