package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.testenv.ControllerEnv;
import kr.or.ddit.user.model.UserVo;

public class UserControllerTest extends ControllerEnv {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	
	/**
	 * Method : userListTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 : 사용자 전체 리스트 테스트
	 * @throws Exception 
	 */
	@Test
	public void userListTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/list")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		/***Then***/
		assertEquals("user/userList", mav.getViewName());
		assertEquals(109, ((List<UserVo>)mav.getModelMap().get("userList")).size());
	}
	
	/**
	 * Method : userPagingListTset
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 :사용자 페이징 리스트 테스트
	 * 			    파라미터를 보냈을때 제대로 처리되는지 확인해보는테스트
	 * @throws Exception 
	 */
	@Test
	public void userPagingListTset() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList").param("page", "2").param("pageSize", "10")).andReturn();
		
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVo> userList = (List<UserVo>) mav.getModelMap().get("userList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		/***Then***/
		assertEquals("tiles.userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
		assertEquals(2, pageVo.getPage());	
		assertEquals(10, pageVo.getPageSize());	
		//hashcode pageVo1 과 pageVo2 같은경우는 서로 다른 객체이기때문에 테스트가 통과하지 못한다. 그래서 pageVo에 hashcode를 만든다.
		//hashcode 를 만들어도 디폴트 처리때문에 getPage(), getPageSize()을 적용 시켜줘야한다. pageVo 참고
		
		//PageVo equals, hashCode메소드를 구현해서 비교한다.
	}
	
	
	/**
	 * Method : userPagingListTset
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 :사용자 페이징 리스트 테스트
	 * 			    파라미터 없이 호출할때 테스트
	 * @throws Exception 
	 */
	@Test
	public void userPagingListWithOutTset() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList")).andReturn();
		
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVo> userList = (List<UserVo>) mav.getModelMap().get("userList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		/***Then***/
		assertEquals("tiles.userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
		assertEquals(1, pageVo.getPage());	
		assertEquals(10, pageVo.getPageSize());	
	}
	
	@Test
	public void userTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo) mav.getModelMap().get("userVo");
		/***Then***/
		assertEquals("user/user", viewName);
		assertEquals("곰", userVo.getAlias());
	
	}
	
	/**
	 * Method : userForm
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 : 사용자 입력 화면 요청
	 * @throws Exception 
	 */
	@Test
	public void userFormGetTest() throws Exception{
		MvcResult mvcResult = mockMvc.perform(get("user/form")).andReturn();
	}
	
	/**
	 * Method : userFormPostSuccessTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록테스트(Success 시나리오)
	 */
	@Test
	public void userFormPostSuccessTest() throws Exception{
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/main02.jpg");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));

		/***When***/

		mockMvc.perform(fileUpload("/user/form").file(file)
									.param("userId", "userTest")
									.param("name", "대덕인")
									.param("alias", "대덕")
									.param("addr1", "대전광역시 중구 중앙로76")
									.param("addr2", "영민빌딩 2층 204호")
									.param("zipcd", "12345")
									.param("birth", "2019-05-03")
									.param("pass", "userTest1234"))
									.andExpect(view().name("redirect:/user/pagingList"));
	}	
	
	/**
	 * Method : userFormPostSuccessTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록테스트(Fail 시나리오)
	 */
	@Test
	public void userFormPostFailTest() throws Exception{
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/main02.jpg");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));

		/***When***/

		mockMvc.perform(fileUpload("/user/form").file(file)
									.param("userId", "sujitasan") //이미 존재하는 아이디
									.param("name", "대덕인")
									.param("alias", "대덕")
									.param("addr1", "대전광역시 중구 중앙로76")
									.param("addr2", "영민빌딩 2층 204호")
									.param("zipcd", "12345")
									.param("birth", "2019-05-03")
									.param("pass", "userTest1234"))
									.andExpect(view().name("user/userForm"));
	}	
	
	/**
	 * Method : profileTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 : 사용자 사진 응답 생성 테스트
	 * @throws Exception 
	 */
	@Test
	public void profileTest() throws Exception {
		
		mockMvc.perform(get("/user/profile").param("userId", "brown"))
//										.andExpect(status().is(200))
										.andExpect(status().isOk());
			
		
	}
	
	/**
	 * Method : userModifyGetTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 :사용자 수정화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void userModifyGetTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/user/modify").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo) mav.getModelMap().get("userVo");
		
		assertEquals("곰", userVo.getAlias());
		assertEquals("user/userModify", viewName);
	}
	
	/**
	 * Method : userModifyPostTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 ::사용자 수정Post 테스트
	 * @throws Exception 
	 */
	@Test
	public void userModifyPostTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/main02.jpg");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));

		/***When***/

		mockMvc.perform(fileUpload("/user/modify").file(file)
									.param("userId", "sujitasan") 
									.param("name", "수지님")
									.param("alias", "水地打山")
									.param("addr1", "대전광역시 중구 중앙로76")
									.param("addr2", "영민빌딩 2층 204호")
									.param("zipcd", "12345")
									.param("birth", "2019-05-03")
									.param("pass", "userTest1234"))
									.andExpect(view().name("redirect:/user/user"));
		
	}
	
}
