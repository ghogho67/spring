package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVo;

public class UserServiceTest extends LogicTestEnv {
	@Resource(name ="userService")
	private IuserService userService;
		
	@Test
	public void userListTest() {
		/***Given***/
		

		/***When***/
		List<UserVo> userList = userService.userList();
		
		/***Then***/
		assertNotNull(userList);
		
	}
	
	/**
	* Method : InsertUserTest
	* 작성자 : PC21
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	 * @throws ParseException 
	*/
	@Test
	public void InsertUserTest() throws ParseException{
		/***Given***/
		//사용자 담고 있는 vo 객체 준비
		
		UserVo vo = new UserVo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
	     vo.setUserId("us1erTest1212313");
		 vo.setAlias("대덕인");
		 vo.setName("대덕");
		 vo.setAddr1("대전광역시 중구 중앙로76");
		 vo.setAddr2("영민빌딩 2층 204호");
		 vo.setZipcd("12345");
		 vo.setBirth(sdf.parse("2019-05-03"));
		 vo.setPass("userTest1234");
		

		/***When***/
		//userDao.insertUser()
		 
		 int insertCnt = userService.insertUser(vo);

		/***Then***/
		
		 assertEquals(1, insertCnt);
		 //data 삭제 
		int delete = userService.deleteUser(vo.getUserId());
		 assertEquals(1, delete);
		
	}
	
	/**
	 * Method : getUserTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 : 사용자 정보 조회 테스트
	 */
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";
		
		/***When***/
		UserVo userVo = userService.getuser(userId);
		/***Then***/
		
		assertNotNull(userVo);
		assertEquals("곰", userVo.getAlias());
	}
	
	/**
	 * Method : updateUser
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param userVo
	 * @return
	 * Method 설명 :사용자 업데이트
	 */
	@Test
	public void updateUser() throws ParseException {
		/***Given***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birth = sdf.parse("2090-01-07");
		
		UserVo userVo = new UserVo("user1", "공부", "배짱2", "user11234", "대전", "길바닥", "12354", birth);
		/***When***/
		int userUpdate = userService.updateUser(userVo);
		/***Then***/
		
		assertNotNull(userUpdate);
		assertEquals(1, userUpdate);
	}
	
	/**
	 * Method : userPagingList
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 : 사용자 페이징 
	 */
	@Test
	public void userPagingListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1,110);

		/***When***/
		Map<String, Object> upl = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>) upl.get("userList");
		int paginationSize = (int) upl.get("paginationSize");
		/***Then***/
//		resultMap.put("userList", userService.userPagingList(pageVo));// 10개씩 갖고오는거고 지금은 105개라서 11번을 갖고와야된다.
//		resultMap.put("paginationSize", paginationSize); //전체 갯수를 갖고오는거고
		assertEquals(109, userList.size());
		assertEquals(1, paginationSize);
	}
	
	

}
