package kr.or.ddit.lprod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.page.model.PageVo;

public interface IlprodService {	
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
	Map<String, Object> lprodPagingList(PageVo pageVo);
	

}
