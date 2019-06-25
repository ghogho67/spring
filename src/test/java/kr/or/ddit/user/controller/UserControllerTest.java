package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.testenv.ControllerEnv;
import kr.or.ddit.user.model.UserVo;

public class UserControllerTest extends ControllerEnv {
	
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
		MvcResult mvcResult = mockMvc.perform(get("/userList")).andReturn();
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
		MvcResult mvcResult = mockMvc.perform(get("/userPagingList").param("page", "2").param("page", "10")).andReturn();
		
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVo> userList = (List<UserVo>) mav.getModelMap().get("userList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		/***Then***/
		assertEquals("user/userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
		assertEquals(2, pageVo.getPage());	
		assertEquals(10, pageVo.getPageSize());	
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
		MvcResult mvcResult = mockMvc.perform(get("/userPagingList")).andReturn();
		
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVo> userList = (List<UserVo>) mav.getModelMap().get("userList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		/***Then***/
		assertEquals("user/userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
		assertEquals(1, pageVo.getPage());	
		assertEquals(10, pageVo.getPageSize());	
	}
}
