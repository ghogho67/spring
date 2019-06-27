package kr.or.ddit.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.prod.dao.IprodDao;
import kr.or.ddit.prod.model.ProdVo;

@Service
public class ProdService implements IprodService{
	
	@Resource(name = "prodDao")
	private IprodDao prodDao;	
	@Override
	public List<ProdVo> prodList() {
		return prodDao.prodList();
	}

	@Override
	public Map<String, Object> prodPagingList(PageVo pageVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("prodList", prodDao.prodPagingList(pageVo));
		
		int lprodCnt = prodDao.prodCnt();
		int paginationSize = (int)Math.ceil((double)lprodCnt/pageVo.getPageSize());
		
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}


}
