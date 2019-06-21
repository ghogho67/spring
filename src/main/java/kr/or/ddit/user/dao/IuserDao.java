package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.user.model.UserVo;

public interface IuserDao {

	/**
	 * Method : userList
	 * 작성자 : PC21
	 * 변경이력 : 
	 * @return
	 * Method 설명 : 사용자 전체 리스트 조회
	 */
	List<UserVo> userList();

	int insertUser(UserVo vo);

	int deleteUser(String userId);
	
}
