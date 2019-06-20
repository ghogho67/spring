package kr.or.ddit.board.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IboardDao;

@Service
public class BoardService implements IboardService {
	
	//property of filed
	
	private IboardDao boardDao;
	private String name;
	
	public BoardService() {
	}
	public BoardService(IboardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	
	public IboardDao getBoardDao() {
		return boardDao;
	}
	public void setBoardDao(IboardDao boardDao) {
		this.boardDao = boardDao;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String sayHello() {
		return boardDao.sayHello();
	}
}
