package kr.or.ddit.main.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerEnv;



public class MainControllerTest extends ControllerEnv {
	
	private static final Logger logger = LoggerFactory.getLogger(MainControllerTest.class);
	

	/**
	 * Method : mainVeiwTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 : Main View 호출 테스트
	 * @throws Exception 
	 */
	
	
	@Test
	public void mainVeiwTest() throws Exception {
		//***Given***/
		

		/***When***/
	
		MvcResult mvcResult1 = mockMvc.perform(get("/main")).andReturn(); //mockmvc 가 dispather url 에서 리턴값을 갖고온다.
		logger.debug("===========================================================================================");
		logger.debug("mvcResult1 :{}",mvcResult1);
		ModelAndView mav = mvcResult1.getModelAndView();
		logger.debug("mav :{}",mav);
		String viewName = mav.getViewName();
		logger.debug("viewName :{}",viewName);
		String userId = (String)mav.getModel().get("mainUserId");
		logger.debug("userId :{}",userId);
		logger.debug("===========================================================================================");
		/***Then***/
		assertEquals("tiles.main", viewName);
		assertEquals("brown", userId);
		assertNotNull(mav.getModel().get("rangers"));
		assertNotNull(mav.getModel().get("userVo"));
	}
	
	
	@Test
	public void mainVeiwAndExpectTest() throws Exception {
		mockMvc.perform(get("/main")).andExpect(status().isOk()) //main url 로 요청을 보냈을때 응답상태여야한다.
									 .andExpect(view().name("tiles.main"))
									 .andExpect(model().attribute("mainUserId", "brown"))
									 .andExpect(model().attributeExists("rangers"))
									 .andExpect(model().attributeExists("userVo"));
	}
	
	/**
	 * Method : mainViewMavTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 :ModelAndView 객체를 이용한 main 페이지 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void mainViewMavTest() throws Exception {
		/***Given***/
	
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/mainMav")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		/***Then***/
		
		//viewName이 기대하는 문자열로 리턴 되는지(mav 에서 네임값을 반환하는지)
		assertEquals("main", mav.getViewName());
		//model객체에 controller에서 설정한 속성이 있는지
		assertEquals("brown", mav.getModel().get("mainUserId"));
		assertNotNull(mav.getModel().get("rangers"));
		
	}
	
	
	/**
	 * Method : pathvarableTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 : @Pathvariable 테스트
	 * @throws Exception 
	 */
	@Test
	public void pathvarableTest() throws Exception {
		mockMvc.perform(get("/main/pathvariable/brown"))
						.andExpect(status().is(200))
						.andExpect(view().name("main"));
	}
	
	/**
	 * Method : pathvarableTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 : @Pathvariable 테스트
	 * @throws Exception 
	 */
	@Test
	public void pathvarableTest2() throws Exception {
		mockMvc.perform(get("/main/pathvariable/sally"))
						.andExpect(status().is(200))
						.andExpect(view().name("main"));
	}
	
	
	/**
	 * Method : pathvarableTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 : @RequestHeader Test
	 * @throws Exception 
	 */
	@Test
	public void requestHeaderTest() throws Exception {
		mockMvc.perform(get("/main/header").accept("text/html"))
						.andExpect(status().is(200))
						.andExpect(view().name("main"));
	}
	
}
