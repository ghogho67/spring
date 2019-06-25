package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.page.model.PageVo;
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
	
	/**
	 * Method : insertUser
	 * 작성자 : PC21
	 * 변경이력 : 
	 * @param vo
	 * @return
	 * Method 설명 : 사용자 등록
	 */
	int insertUser(UserVo vo);

	/**
	 * Method : deleteUser
	 * 작성자 : PC21
	 * 변경이력 : 
	 * @param userId
	 * @return
	 * Method 설명 :사용자 삭제
	 */
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
	
	/**
	 * Method : updateUser
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param userVo
	 * @return
	 * Method 설명 :사용자 업데이트
	 */
	int updateUser(UserVo userVo);
	
	
	/**
	 * Method : userPagingList
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 : 사용자 페이징 
	 */
	Map<String, Object> userPagingList(PageVo pageVo);
}
