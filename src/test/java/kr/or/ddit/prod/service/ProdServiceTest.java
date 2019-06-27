package kr.or.ddit.prod.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.testenv.LogicTestEnv;

public class ProdServiceTest extends LogicTestEnv{
	
	@Resource(name = "prodService")
	private IprodService prodService;
	
	@Test
	public void prodListTest() {
		/***Given***/
		/***When***/
		List<ProdVo> prodVo = prodService.prodList();
		
		/***Then***/
		assertNotNull(prodVo);
		assertEquals(74,prodVo.size());
	}
	
	@Test
	public void prodPagingListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1,5);
		/***When***/
		Map<String, Object> upl = prodService.prodPagingList(pageVo);
		List<ProdVo> prodList = (List<ProdVo>) upl.get("prodList");
		int paginationSize = (int) upl.get("paginationSize");
		
		/***Then***/
		assertEquals(5, prodList.size());
		assertEquals(15, paginationSize);
	}
	

}
