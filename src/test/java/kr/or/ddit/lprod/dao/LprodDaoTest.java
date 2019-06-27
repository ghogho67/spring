package kr.or.ddit.lprod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.testenv.LogicTestEnv;

public class LprodDaoTest extends LogicTestEnv {
	
	@Resource(name="lprodDao")
	private IlprodDao lprodDao;
	
	@Test
	public void lprodListTest() {
		/***Given***/
		/***When***/
		List<LprodVo> lprodVo = lprodDao.lprodList();
		
		/***Then***/
		assertNotNull(lprodVo);
		assertEquals(17,lprodVo.size());
	}
	
	@Test
	public void lprodPagingListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1,5);
		/***When***/
		List<LprodVo> lprodVo = lprodDao.lprodPagingList(pageVo);
		
		/***Then***/
		assertNotNull(lprodVo);
		assertEquals(5,lprodVo.size());
	}
	
	@Test
	public void lprodCntTest() {
		/***Given***/
		/***When***/
		int lprodCnt = lprodDao.lprodCnt();
		
		/***Then***/
		assertNotNull(lprodCnt);
		assertEquals(17,lprodCnt);
	}

}
