package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.IboardService;


@ContextConfiguration(classes = {ApplicationIocConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationIocConfigTest {
	
	@Resource(name = "boardDao") //해당하는 클레스의 메서드 이름을 갖고온다
	private IboardDao boardDao;
	
	@Resource(name = "boardDao") //해당하는 클레스의 메서드 이름을 갖고온다
	private IboardDao boardDao2;
	
	@Resource(name = "boardSerivce") //해당하는 클레스의 메서드 이름을 갖고온다
	private IboardService boardSerivce;
	
	@Test
	public void test() {
		/***Given***/
		
		/***When***/
		String msg = boardDao.sayHello();

		/***Then***/
		assertNotNull(boardDao);
		assertEquals("boardDao say Hello", msg);
		assertEquals(boardDao2, boardDao);
		
		assertEquals(boardDao, boardSerivce.getBoardDao());
	}

}
