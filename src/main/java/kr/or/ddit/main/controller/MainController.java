package kr.or.ddit.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping("/main") //main 이라는 url로 요청이오면 실행되는 mainView라는 메소드가 호출된다. 즉 localhost/main 이라고치면 이 게 실행
	public String mainView(Model model) { // 값을 보낼때는 Model 이라는 인자(매개변수)를 추가한다. model 의 스코프는 request 이다
										  //httpServletRequest를 넣어도되지만 json의 처리가 안되기 때문이다 model은 json view 등등 view 처리가 다양하다.
		//prefix : /WEB-INF/view/
		//surffix : .jsp
		
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

}
