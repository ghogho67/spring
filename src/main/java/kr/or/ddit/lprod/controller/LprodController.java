package kr.or.ddit.lprod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.IlprodService;
import kr.or.ddit.page.model.PageVo;

@RequestMapping("/lprod")
@Controller
public class LprodController {
	
	@Resource(name = "lprodService")
	private IlprodService lprodService;
	
	@RequestMapping("/pagingList")
	public String lprodPagingList(PageVo pageVo, Model model) {
		
		Map<String, Object> resultMap = lprodService.lprodPagingList(pageVo);
		List<LprodVo> lprodList = (List<LprodVo>) resultMap.get("lprodList");
		int paginationSize = (Integer)resultMap.get("paginationSize");
		model.addAttribute("lprodList",lprodList);
		model.addAttribute("paginationSize",paginationSize);
		model.addAttribute("PageVo",pageVo);
		
		
		
		return "lprod/lprodPagingList";
		
	}
	
}
