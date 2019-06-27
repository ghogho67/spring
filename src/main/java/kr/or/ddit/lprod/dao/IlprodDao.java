package kr.or.ddit.lprod.dao;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.page.model.PageVo;

public interface IlprodDao {
	
	/**
	 * Method : lprodList
	 * 작성자 : PC21
	 * 변경이력 :
	 * @return
	 * Method 설명 :lprod 리스트 조회
	 */
	List<LprodVo> lprodList();
	
	/**
	 * Method : lprodPagingList
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 :lprod 페이징리스트 조회
	 */
	List<LprodVo> lprodPagingList(PageVo pageVo);
	
	/**
	 * Method : lprodCnt
	 * 작성자 : PC21
	 * 변경이력 :
	 * @return
	 * Method 설명 : lprod전체수 조회
	 */
	int lprodCnt();
	

}
