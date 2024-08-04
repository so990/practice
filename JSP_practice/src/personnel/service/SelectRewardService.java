package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import personnel.dao.RewardDao;
import personnel.model.Reward;

public class SelectRewardService {
	private RewardDao rewardDao = new RewardDao();
	
	public List<Reward> selectListByNo(int emp_no) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			//해당 bs_num의 객체를 받아옴
			List<Reward> reward = rewardDao.selectList(conn, emp_no);			
			return reward;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}