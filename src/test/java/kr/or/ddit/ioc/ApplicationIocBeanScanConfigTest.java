package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.IboardService;


@ContextConfiguration(classes = {ApplicationIocBeanScanConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationIocBeanScanConfigTest {
	
	//<bean 태그를 이용하여 스프링 빈을 등록하는 방식을 사용하지않고
	//@Controller, @service, @Respository 이노테이션을 적용한 클래스를
	//base package 하위 모든 클래스를 scan 하여 스플이 빈으로 등록
	
	//boardDao, boardService 스프링 빈이 정상적으로 생성 되었는지
	
	
	//스캔된 이름은 클래스 명을 갖고오는데 앞자리가 소문자가 된다.
	@Resource(name = "boardDao")
	private IboardDao boardDao;
	
	@Resource(name = "boardService")
	private IboardService boardService;
	
	
	@Test
	public void springBeanScanTest() {
		/***Given***/
		/***When***/
		/***Then***/
		
		assertNotNull(boardDao);
		assertEquals("boardDao say Hello", boardDao.sayHello());
		assertNotNull(boardService);
		
		
	}

}
