package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;

@Repository
public class ProdDao implements IprodDao{
	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public List<ProdVo> prodList() {
		return sqlSession.selectList("prod.prodList");
	}

	@Override
	public List<ProdVo> prodPagingList(PageVo pageVo) {
		return sqlSession.selectList("prod.prodPagingList",pageVo);
	}

	@Override
	public int prodCnt() {
		return sqlSession.selectOne("prod.prodCnt");
	}

}
