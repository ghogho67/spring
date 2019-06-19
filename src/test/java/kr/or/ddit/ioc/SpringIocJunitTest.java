package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.service.IboardService;

/**
 * @author PC21
 * DI방법
 */
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 환경에서 junit을 쓸수있는 환경으로 만들어주는 이노테이션
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")//경로를 넣어주면된다.
public class SpringIocJunitTest {
	
	@Resource(name ="boardService") // DI 하기 위한 거임 name엔 스프링 아이디를 넣어준다.
	private IboardService boardService;  //bean 을 주입 받았다.
	
	/**
	 * 스프링 컨테이너 생성 테스트
	 */
	@Test
	public void springIocTest() {
		/***Given***/

		/***When***/
		
		String msg = boardService.sayHello();
		/***Then***/
		assertNotNull(boardService);
		assertEquals("boardDao say Hello", msg);
	}

}
