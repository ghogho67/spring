package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.IboardService;

/**
 * @author PC21
 * DI방법
 */
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 환경에서 junit을 쓸수있는 환경으로 만들어주는 이노테이션
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")//경로를 넣어주면된다.
public class SpringIocJunitTest {
	
	 // SingletonTest 예제
	
	//field 기준 boardService, boardService2 : spring boardService bean(scope=singleton)
	// 			boardserviceConstructor : spring boardServiceConstructor bean (scope=singleton)
	//1.boardService , boardSErvice2 : 같아야함
	//2.boardService , boardserviceConstructor : 같은 클래스에서 만들어진 객체이지만 Spring singleton 개념에 따라 다른 객체 
	//2.boardService2 , boardserviceConstructor : 같은 클래스에서 만들어진 객체이지만 Spring singleton 개념에 따라 다른 객체 
	//---------------------------------------------------------------------------------------------------------------
	
	// PrototypeTest 예제
	
	//boardDao : spring boardDao(scope = singleton)
	//boardDaoprototype : spring boardDaoprototype(scope = prototype)
	//boardDaoprototype2 : spring boardDaoprototype(scope = prototype)
	
	//1.boardDao boardDaoPrototype : spring bean id가 다르므로 다른객체
	//2.boardDaoPrototype, boardDaoPrototype2 : 두객체는 같은 스프링빈이지만 scope가 prototype이므로 다른객체
	
	//----------------------------------------------------------------------------------------------------------------
	
	@Resource(name ="boardService") // DI 하기 위한 거임 name엔 스프링 아이디를 넣어준다.
	private IboardService boardService;  //bean 을 주입 받았다.
	
	@Resource(name ="boardService")
	private IboardService boardService2;
	
	@Resource(name="boardServiceConstructor")
	private IboardService boardServiceConstructor;
	
	@Resource(name = "boardDao")
	private IboardDao boardDao;
	
	@Resource(name = "boardDaoPrototype")
	private IboardDao boardDaoPrototype;
	
	@Resource(name = "boardDaoPrototype")
	private IboardDao boardDaoPrototype2;
	
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
	
	@Test
	public void springSingletonScopeTest() {
		/***Given***/
		/***When***/
		/***Then***/
		assertNotNull(boardService);
		assertNotNull(boardService2);
		assertNotNull(boardServiceConstructor);
		assertEquals(boardService, boardService2);
		assertNotEquals(boardService, boardServiceConstructor);
		assertNotEquals(boardService2, boardServiceConstructor);
	}
	
	@Test
	public void springPrototypeScopeTest() {
		
		/***Given***/
		/***When***/
		/***Then***/
		assertNotEquals(boardDao, boardDaoPrototype);
		assertNotEquals(boardDao, boardDaoPrototype2);
		assertNotEquals(boardDaoPrototype, boardDaoPrototype2);
		
	}
	
	

}
