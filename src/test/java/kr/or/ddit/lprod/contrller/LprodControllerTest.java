package kr.or.ddit.lprod.contrller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.dao.IlprodDao;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.testenv.ControllerEnv;

public class LprodControllerTest extends ControllerEnv {

	/**
	 * Method : userPagingListTset
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 :사용자 페이징 리스트 테스트
	 * 			    파라미터를 보냈을때 제대로 처리되는지 확인해보는테스트
	 * @throws Exception 
	 */
	@Test
	public void lprodPagingListTset() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/pagingList").param("page", "2").param("pageSize", "10")).andReturn();
		
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<LprodVo> lprodList = (List<LprodVo>) mav.getModelMap().get("lprodList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		/***Then***/
		assertEquals("lprod/lprodPagingList", viewName);
		assertEquals(7, lprodList.size());
		assertEquals(2, paginationSize);
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
	public void lprodPagingListWithOutTset() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/pagingList")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<LprodVo> lprodList = (List<LprodVo>) mav.getModelMap().get("lprodList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		/***Then***/
		assertEquals("lprod/lprodPagingList", viewName);
		assertEquals(10, lprodList.size());
		assertEquals(2, paginationSize);
		assertEquals(1, pageVo.getPage());	
		assertEquals(10, pageVo.getPageSize());	
	}

}
