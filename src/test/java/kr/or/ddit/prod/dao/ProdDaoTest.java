package kr.or.ddit.prod.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.testenv.LogicTestEnv;

public class ProdDaoTest extends LogicTestEnv {
	
	@Resource(name="prodDao")
	private IprodDao prodDao;
	
	@Test
	public void prodListTest() {
		/***Given***/
		/***When***/
		List<ProdVo> prodVo = prodDao.prodList();
		
		/***Then***/
		assertNotNull(prodVo);
		assertEquals(74 ,prodVo.size());
	}
	
	@Test
	public void prodPagingListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1,5);
		/***When***/
		List<ProdVo> prodVo = prodDao.prodPagingList(pageVo);
		
		/***Then***/
		assertNotNull(prodVo);
		assertEquals(5,prodVo.size());
	}
	
	@Test
	public void prodCntTest() {
		/***Given***/
		/***When***/
		int prodCnt = prodDao.prodCnt();
		
		/***Then***/
		assertNotNull(prodCnt);
		assertEquals(74,prodCnt);
	}

}
