package personnel.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import personnel.dao.RewardDao;
import personnel.model.Reward;

public class InsertRewardService {
	private RewardDao rewardDao = new RewardDao();
	
	public Reward insert(RewardRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			Reward reward = toReward(req);
			//DB에 저장 하고 저장에 성공한 객체를 받아옴
			Reward savedReward = rewardDao.insert(conn, reward);
			if(savedReward == null) {	// 저장에 실패하면 RuntimeException
				throw new RuntimeException("fail to insert Reward");
			}
			
			conn.commit();
			//트랜잭션 끝
			return savedReward;
			
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
	
	private Reward toReward(RewardRequest req) {
		return new Reward(
				req.getEmp_no(),
				req.getReward_type(),
				req.getReward_name(),
				req.getReward_who(),
				req.getReward_date(),
				req.getReward_detail(),
				req.getReward_note());
	}
	
}