package kr.or.ddit.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.main.model.MainVo;
import kr.or.ddit.user.model.UserVo;

/*
 
 우리가 여태 servlet
  	- extends HttpServlet 으로 썻다
 	- servlet-mapping(web.xml, @WebServlet)
 	- servlet -> doXXX 를 실행 했었다. 스프링은 @RequestMapping에 설정한 url method를 호출한다.
 
 spring
 	- pojo(Plain Old Java Object) 뭘 상속받고 그럴필요없는 거, @Controller
 	- @RequestMapping(class, method 에서 쓴다.)
 	- @RequestMapping에 설정한 url method를 호출
 	
 	지금 할것은 pojo 먼저 컨트롤러 어노테이션 을 만든다.
 
 */

@SessionAttributes("rangers")
@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	//메소드에 적용된 @ModelAttribute
	//@RequestMapping이 붙은 메소드가 실행될때(요청 처리될때)
	//@ModelAttribute가 적용된 메소드가 리턴하는 값을 Model객체에 자동적으로 넣어준다.
	//예시 사용자localhost/main 요청을 할때  
	//           --> @RequestMapping("/main") : mainView --> Model에는 rangers 속성이 들어가있다.
	//예시 사용자localhost/mainMav 요청을 할때  
	//			 --> @RequestMapping("/mainMav") : mainViewMav --> Model에는 rangers 속성이 들어가있다.
	
	@ModelAttribute("rangers")
	public List<String> rangers(){
		logger.debug(":{}", "rangers()");
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		rangers.add("james");
		rangers.add("moon");
		
		return rangers;
	}
	
	
	/**
	 * Method : mainView
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param model
	 * @return
	 * Method 설명 : viewName을 이용하여 요청
	 */
	@RequestMapping("/main") //main 이라는 url로 요청이오면 실행되는 mainView라는 메소드가 호출된다. 즉 localhost/main 이라고치면 이 게 실행
	public String mainView(Model model,@ModelAttribute("userVo")UserVo userVo) { // 값을 보낼때는 Model 이라는 인자(매개변수)를 추가한다. model 의 스코프는 request 이다
										  //httpServletRequest를 넣어도되지만 json의 처리가 안되기 때문이다 model은 json view 등등 view 처리가 다양하다.
		//prefix : /WEB-INF/view/
		//surffix : .jsp
		
		logger.debug("mainView");
		logger.debug("model.asMap().get(\"rangers\") : {}",model.asMap().get("rangers"));
		logger.debug("userVo");
		
		userVo.setName("브라운");
		
//		UserVo userVo = new UserVo();
//		userVo.setName("브라운");
//		model.addAttribute("userVo", userVo);
		
		
		/*
		 url 들어가는 형식
		 prefix + viewName + surffix
		 /WEB-INF/views/main.jsp
		  * 
		  * InternalResourceViewResolver 를 설정한것은 일반적으로 forward형태이다 결국 requestdispach 로 되는듯하다.
		 */
		
		//model.addAttribute 는 request.setAtrribute 와 같다.
		model.addAttribute("mainUserId","brown");
		
		//wiewName
		return "main";
	}
	
	
	/**
	 * Method : mainViewMav
	 * 작성자 : PC21
	 * 변경이력 :
	 * @return
	 * Method 설명 : main페이지 요청(ModelAndView 사용)
	 */
	@RequestMapping("/mainMav") //main 이라는 url로 요청이오면 실행되는 mainView라는 메소드가 호출된다. 즉 localhost/main 이라고치면 이 게 실행
	public ModelAndView mainViewMav(@ModelAttribute("rangers")List<String> rangers) { // 값을 보낼때는 Model 이라는 인자(매개변수)를 추가한다. 
																					  //model 의 스코프는 request 이다
		logger.debug("mainViewMav:{}", rangers);
		
		//viewName을 기반으로 ModelAndView 객체를 생성
		ModelAndView mav = new ModelAndView("main");
		//위문장은 아리 두문장과 같다
		//ModelAndView mav = new ModelAndView();
		//mav.setViewName("main"); 어떤걸 써도 괜찮아요
		
//		model.addAttribute("mainUserId","brown");
		mav.addObject("mainUserId", "brown");
		
		return mav;
	}
	
	
	// 사용자의 url 이 바뀔수도 있게 한다.
	//ex) localhost/main/pathvariable/brown , localhost/main/pathvariable/cony 로 바뀔수도있다.
	/**
	 * Method : pathvariable
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : pathvarible로 부터 사용자 아이디 가져오기(ex:d)
	 */
	@RequestMapping("/main/pathvariable/{userId}")
	public String pathvariable(@PathVariable("userId")String userId) {
		logger.debug("@PathVariable userId:{}",userId);
		return "main";
	}
	
	
	
	
	/**
	 * Method : header
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param accept
	 * @return
	 * Method 설명 : accept 헤더 정보 가져오기
	 */
	@RequestMapping("/main/header")
	public String header(@RequestHeader(name = "Accept"/* ,required = false */)String accept) {
		logger.debug("Accept : {}", accept);
		
		return "main";
	}
	
	
	
	//복수 파라미터 학습
	@RequestMapping("/main/view")
	public String view() {
		return "view";
	}
	
	
	//List<> 타입의 파라미터 경울 @RequestParam 적용
	@RequestMapping("/main/process")
	public String process(HttpServletRequest request,
							String[] userId, 
							@RequestParam("userId")List<String>userIdList, 
//							@RequestParam("name")List<String>name, 
							MainVo mainVo) {
		
		String[] userIdArr = request.getParameterValues("userId");
		String userIdParameter = request.getParameter("userId"); 
		// 파라미터는 순서대로 보내지만 파라미터는 하나밖에 못받아서 brown이나온다.2개를 보내면 1번째꺼 보냄
		logger.debug("=========================================================================================");
		logger.debug("userIdParameter:{}",userIdParameter);
		
		logger.debug("request.getParameterValues(\"userId\")");
		for(String u : userIdArr)
			logger.debug("userId:{}",u);
		logger.debug("=========================================================================================");
		logger.debug("String[]userId");
		for(String u : userId)
			logger.debug("userId:{}",u);
		logger.debug("=========================================================================================");
		logger.debug("userIdList");
		for(String u : userIdList)
		logger.debug("userId:{}",u);
		logger.debug("=========================================================================================");
		logger.debug("mainVo");
		
		logger.debug("mainVo :{}", mainVo);
		
		logger.debug("=========================================================================================");
		
		return "main";
		
	}
	

}


