package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.board.service.IboardService;

/**
 * @author PC21
 *
 */
public class SpringIocTest {

	
	/**
	 * 
	 */
	@Test
	public void springIocTest() {
		/***Given***/
		//스프링 컨테이너 생성
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:kr/or/ddit/ioc/application-ioc-test.xml");

		/***When***/
		
		IboardService boardService = (IboardService)context.getBean("boardService");
		String msg = boardService.sayHello();
		/***Then***/
		assertNotNull(boardService);
		assertEquals("boardDao say Hello", msg);
	}

}
