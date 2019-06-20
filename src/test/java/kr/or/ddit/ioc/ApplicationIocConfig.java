package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.service.BoardService;

@Configuration
public class ApplicationIocConfig {

	//스프링빈을 만들때는 xml 로 하는 방법이 있고 java로 하는 방법이있다 여태까지 xml이고 지금 부터는 java로 해보는 걸해보자
	
	//<bean id="boardDao" class="kr.or.ddit.board.dao.BoardDao"></bean>
	
	@Bean
	public BoardDao boardDao() {
		return new BoardDao();
	}
	
	
	
	/*
	<bean id="boardService" class="kr.or.ddit.board.service.BoardService">
	<property name="name" value="brown"/>
	<property name="boardDao" ref="boardDao"></property>
	</bean>*/
	
	@Bean
	public BoardService boardSerivce() {
		BoardService boardService = new BoardService();
		boardService.setName("brown");
		boardService.setBoardDao(boardDao());
		
		return boardService;
	}
	
	
	
	
}
