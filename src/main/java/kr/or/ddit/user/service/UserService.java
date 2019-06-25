package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.user.dao.IuserDao;
import kr.or.ddit.user.model.UserVo;

@Service
public class UserService implements IuserService {
	
	@Resource(name = "userDao")
	private IuserDao userDao;
	
	
	/**
	 * Method : userList
	 * 작성자 : PC21
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 유저 정보 조회
	 */
	@Override
	public List<UserVo> userList() {
		return userDao.userList();
	}

	/**
	 * Method : insertUser
	 * 작성자 : PC21
	 * 변경이력 : 
	 * @param vo
	 * @return
	 * Method 설명 : 사용자 등록
	 */
	@Override
	public int insertUser(UserVo vo) {
		return userDao.insertUser(vo);
	}

	
	/**
	 * Method : deleteUser
	 * 작성자 : PC21
	 * 변경이력 : 
	 * @param userId
	 * @return
	 * Method 설명 :사용자 삭제
	 */
	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
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
		return userDao.getuser(userId);
	}
	
	/**
	 * Method : updateUser
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param userVo
	 * @return
	 * Method 설명 :사용자 업데이트
	 */
	@Override
	public int updateUser(UserVo userVo) {
		return userDao.updateUser(userVo);
	}
	
	/**
	 * Method : userPagingList
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 : 사용자 페이징 
	 */
	@Override
	public Map<String, Object> userPagingList(PageVo pageVo) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userList", userDao.userPagingList(pageVo));// 10개씩 갖고오는거고 지금은 105개라서 11번을 갖고와야된다.
		
		int usersCnt = userDao.userCnt();
		int paginationSize = (int)Math.ceil((double)usersCnt/pageVo.getPageSize());
		
		resultMap.put("paginationSize", paginationSize); //전체 갯수를 갖고오는거고
		
		return resultMap;
	}

}
