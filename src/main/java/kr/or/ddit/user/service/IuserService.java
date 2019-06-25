package kr.or.ddit.user.service;

import java.util.List;

import kr.or.ddit.user.model.UserVo;

public interface IuserService {

	/**
	 * Method : userList
	 * 작성자 : PC21
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 유저 정보 조회
	 */
	List<UserVo> userList();

	int insertUser(UserVo vo);

	int deleteUser(String userId);
	
	/**
	 * Method : getuser
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 정보조회
	 */
	UserVo getuser(String userId);
}
