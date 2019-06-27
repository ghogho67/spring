package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.IlprodService;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.prod.service.IprodService;

@RequestMapping("/prod")
@Controller
public class ProdController {
	
	@Resource(name = "prodService")
	private IprodService prodService;
	
	@Resource(name = "lprodService")
	private IlprodService lprodService;
	
	@RequestMapping("/pagingList")
	public String prodPagingList(PageVo pageVo, Model model) {
		
		Map<String, Object> resultMap = prodService.prodPagingList(pageVo);
		List<ProdVo> prodList = (List<ProdVo>) resultMap.get("prodList");
		List<LprodVo> lprodList = lprodService.lprodList();
		int paginationSize = (Integer)resultMap.get("paginationSize");
		
		model.addAttribute("prodList",prodList);
		model.addAttribute("lprodList",lprodList);
		model.addAttribute("paginationSize",paginationSize);
		model.addAttribute("PageVo",pageVo);
		
		return "prod/prodPagingList";
	}
	
}
