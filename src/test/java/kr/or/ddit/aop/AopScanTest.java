package kr.or.ddit.aop;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.testenv.LogicTestEnv;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/aop/application-aop-Scan.xml")
public class AopScanTest {

	@Resource(name = "boardService")
	private IboardService boardService;
	
	@Test
	public void aopBefore() {
		/***Given***/
		

		/***When***/
		String msg = boardService.sayHello();
		/***Then***/
		assertEquals("boardDao say Hello", msg);
	}

}
