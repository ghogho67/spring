package kr.or.ddit.login.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerEnv;
import kr.or.ddit.user.model.UserVo;

public class LoginControllerTest extends ControllerEnv{

	/**
	 * Method : loginViewNotLoginedtest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 : 접속하지 않은 상황에서 loginView요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void loginViewNotLoginedtest() throws Exception {
		
		/***Given***/
		
		/***When***/
		
		MvcResult mvcResult1 = mockMvc.perform(get("/login")).andReturn(); //mockmvc 가 dispather url 에서 리턴값을 갖고온다.
		ModelAndView mav = mvcResult1.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("login/login", viewName);
	}
	
	
	/**
	 * Method : loginViewLoginedtest
	 * 작성자 : PC21
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 로그인한 상황에서 로그인 뷰 요청 테스트
	 */
	@Test
	public void loginViewLoginedtest() throws Exception {
		
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login").sessionAttr("USER_INFO",new UserVo())).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("main", viewName);
	}
	
	/**
	 * Method : loginProcessSuccessTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 로그인 요청 처리 성공 테스트 세션들어오는것도 확인함
	 */
	@Test
	public void loginProcessSuccessTest() throws Exception {
		/***Given***/
		String userId = "brown";
		String password = "brown1234";
		/***When***/
		
		MvcResult mvcResult = mockMvc.perform(post("/login").param("userId", userId)
									.param("password", password))
									.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
//		UserVo userVo = (UserVo) mvcResult.getRequest().getSession().getAttribute("USER_INFO");
		HttpSession session = mvcResult.getRequest().getSession();
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo)session.getAttribute("USER_INFO");
		/***Then***/
		assertEquals("main", viewName);
		assertEquals("brown", userVo.getUserId());
		assertEquals("브라운", userVo.getName());
		assertEquals("곰", userVo.getAlias());
	}
	
	
	/**
	 * Method : loginProcessFailTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 로그인 요청 처리 실패 테스트 
	 */
	@Test
	public void loginProcessFailTest() throws Exception {
		/***Given***/
		String userId = "brown";
		String password = "brown12345678"; //틀린비밀번호
		/***When***/
		
		MvcResult mvcResult = mockMvc.perform(post("/login").param("userId", userId)
									.param("password", password))
									.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("login/login", viewName);
	}

}
