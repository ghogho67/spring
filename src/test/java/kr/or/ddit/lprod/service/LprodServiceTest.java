package kr.or.ddit.lprod.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVo;

public class LprodServiceTest extends LogicTestEnv{
	
	@Resource(name = "lprodService")
	private IlprodService lprodService;
	
	@Test
	public void lprodListTest() {
		/***Given***/
		/***When***/
		List<LprodVo> lprodVo = lprodService.lprodList();
		
		/***Then***/
		assertNotNull(lprodVo);
		assertEquals(17,lprodVo.size());
	}
	
	@Test
	public void lprodPagingListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1,5);
		/***When***/
		Map<String, Object> upl = lprodService.lprodPagingList(pageVo);
		List<LprodVo> lprodList = (List<LprodVo>) upl.get("lprodList");
		int paginationSize = (int) upl.get("paginationSize");
		
		/***Then***/
		assertEquals(5, lprodList.size());
		assertEquals(4, paginationSize);
	}
	

}
