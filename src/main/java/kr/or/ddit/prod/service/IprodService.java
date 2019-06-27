package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;

public interface IprodService {
	
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
	Map<String, Object> prodPagingList(PageVo pageVo);
	

}
