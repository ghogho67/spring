package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;

public interface IprodDao {
	
	/**
	 * Method : prodList
	 * 작성자 : PC21
	 * 변경이력 :
	 * @return
	 * Method 설명 :prod 리스트 조회
	 */
	List<ProdVo> prodList();
	
	/**
	 * Method : prodPagingList
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 :prod 페이징리스트 조회
	 */
	List<ProdVo> prodPagingList(PageVo pageVo);
	
	/**
	 * Method : prodCnt
	 * 작성자 : PC21
	 * 변경이력 :
	 * @return
	 * Method 설명 : prod전체수 조회
	 */
	int prodCnt();
	

}
