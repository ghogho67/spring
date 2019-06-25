package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.page.model.PageVo;
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
	 * Method : userCnt
	 * 작성자 : PC21
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 전체수 조회
	 */
	int userCnt();
	
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
	List<UserVo> userPagingList(PageVo pageVo);
	
	
}
