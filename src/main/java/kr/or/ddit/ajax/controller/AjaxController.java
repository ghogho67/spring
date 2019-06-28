package kr.or.ddit.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IuserService;
@RequestMapping("/ajax")
@Controller
public class AjaxController {

		@Resource(name = "userService")
		private IuserService userService;
	
		/**
		 * Method : view
		 * 작성자 : PC21
		 * 변경이력 :
		 * @return
		 * Method 설명 : ajax 호출용
		 */
		@RequestMapping("/view")
		public String view() {
			return "tiles.ajaxView";
		}
		
		
		@RequestMapping("/requestData")
		public String requestData(Model model) {
			model.addAttribute("pageVo", new PageVo(5,10));
			model.addAttribute("pageVo2", new PageVo(2,10));
			
			List<String> rangers =new ArrayList<String>();
			rangers.add("brown");
			rangers.add("sally");
			rangers.add("cony");
			model.addAttribute("rangers",rangers);
			
			/*
				{pagVo:{page:5, pageSize:10}}
				{pagVo:{page:5, pageSize:10}},{pagVo:{page:2, pageSize:10}}
				{pagVo:{page:5, pageSize:10}},{pagVo:{page:2, pageSize:10}}
				
			 */
			return "jsonView"; //application-context 에 설정 함
		}
		
		@RequestMapping("/user")
		public String user(String userId, Model model) {
			UserVo userVo = userService.getuser(userId);
			model.addAttribute("userVo",userVo);
			//json 형태로 갖고간다면
			//{userVo :{userId :"brown", name : "브라운" alias :"곰"...등....등..}}
			
			return "jsonView";
		}
		
		
		@RequestMapping("/userHtml")
		public String userHtml(String userId, Model model) {
			UserVo userVo = userService.getuser(userId);
			model.addAttribute("userVo",userVo);
			//json 형태로 갖고간다면
			//{userVo :{userId :"brown", name : "브라운" alias :"곰"...등....등..}}
			
			return "user/userHtml";
		}
		
}
