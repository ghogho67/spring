package kr.or.ddit.lprod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.page.model.PageVo;

@Repository
public class LprodDao implements IlprodDao{
	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public List<LprodVo> lprodList() {
		return sqlSession.selectList("lprod.lprodList");
	}

	@Override
	public List<LprodVo> lprodPagingList(PageVo pageVo) {
		return sqlSession.selectList("lprod.lprodPagingList",pageVo);
	}

	@Override
	public int lprodCnt() {
		return sqlSession.selectOne("lprod.lprodCnt");
	}

}
