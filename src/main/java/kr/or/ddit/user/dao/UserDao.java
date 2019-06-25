package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.page.model.PageVo;
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
		return sqlSession.insert("user.insertUser",vo);
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
	
	/**
	 * Method : userCnt
	 * 작성자 : PC21
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 전체수 조회
	 */
	@Override
	public int userCnt() {
		return sqlSession.selectOne("user.usersCnt");
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
		return sqlSession.update("user.updateUser",userVo);
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
	public List<UserVo> userPagingList(PageVo pageVo) {
		return sqlSession.selectList("user.userPagingList", pageVo);
	}
	
	
	
}
