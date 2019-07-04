package kr.or.ddit.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IuserService;
@RequestMapping("/ajax")
@Controller
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
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
		
		
		//jsonView 를 통한 ajax 인데 이거 말고도 실무에서는 converter를 통해서 하는 경우도 있따.
		@RequestMapping("/requestDataResponseBody")
		@ResponseBody //응답내용을 responseBody에 다가 작성 해라 라는 의
		public PageVo requestResponseBody() {
			
			return new PageVo(5,10); 
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
		
//		@RequestMapping("/requestBody")
		@RequestMapping(path = "/requestBody", 
											//consumes :content-type 제한
											consumes = {"application/json"},
											// produces = 메소드가 생성 가능 한 타입(accept헤더를 보고 판단 xml 에는 xml화 할 객체같은곳에
											// @XmlRootElement(name = "userVo")같이 붙여준다,)
											produces = {"application/json","application/xml"}
				
				) 
		@ResponseBody
		public UserVo requestBody(@RequestBody UserVo userVo) {
			logger.debug("☞userVo:{}",userVo);
			userVo.setUserId(userVo.getUserId()+"_MODIFY");
			userVo.setPass(userVo.getPass()+"_MODIFY");
			return userVo;
		}
		
}
