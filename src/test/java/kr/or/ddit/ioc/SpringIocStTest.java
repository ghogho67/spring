package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.IboardService;



@RunWith(SpringJUnit4ClassRunner.class)//spring 을 쓰기위해 클래스에 RunWith를 하여 권한을주고
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-st.xml") //Spring이 있는 경로를 설정해준다.
public class SpringIocStTest {

	@Resource(name = "bDao") // Spring에 설정된 bean 객체 id 를 표시해 준다.
	private IboardDao boardDao;
	
	@Resource(name = "bService") //이노테이션은 한개의 변수위에 설정한다. 중복되는 이노테이션은 없다.
	private IboardService boardService;
	
	@Test
	public void test() {
		/***Given***/
		

		/***When***/
		

		/***Then***/
		assertEquals(boardDao, boardService.getBoardDao());
	}

}
