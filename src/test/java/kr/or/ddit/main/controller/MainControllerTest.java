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
		assertEquals("main", viewName);
		assertEquals("brown", userId);
	}
	
	
	@Test
	public void mainVeiwAndExpectTest() throws Exception {
		//***Given***/
		

		/***When***/
	
		
		mockMvc.perform(get("/main")).andExpect(status().isOk()) //main url 로 요청을 보냈을때 응답상태여야한다.
									 .andExpect(view().name("main"))
									 .andExpect(model().attribute("mainUserId", "brown"));
				
		/***Then***/
	}
}
