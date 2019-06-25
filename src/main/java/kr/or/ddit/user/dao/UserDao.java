package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;

@Repository
public class UserDao implements IuserDao {

	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	 * Method : userList
	 * 작성자 : PC21
	 * 변경이력 : 
	 * @return
	 * Method 설명 : 사용자 전체 리스트 조회
	 */
	@Override
	public List<UserVo> userList() {
		return sqlSession.selectList("user.userList");
	}

	@Override
	public int insertUser(UserVo vo) {
		return sqlSession.insert("user.insertUser",vo);
	}

	@Override
	public int deleteUser(String userId) {
		return sqlSession.delete("user.deleteUser",userId);
	}
	
	
	/**
	 * Method : getuser
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 정보조회
	 */
	@Override
	public UserVo getuser(String userId) {
		return sqlSession.selectOne("user.getUser", userId);
	}
	
}
