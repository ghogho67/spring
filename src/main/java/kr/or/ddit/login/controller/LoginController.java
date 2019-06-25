package kr.or.ddit.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IuserService;
import kr.or.ddit.user.service.UserService;

@Controller
public class LoginController {
		@Resource(name ="userService")
		private IuserService userService;

	/**
	 * Method : loginView
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param session
	 * @return
	 * Method 설명 : 사용자 로그인 화면 요청 GET
	 */
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginView(HttpSession session){
		if(session.getAttribute("USER_INFO") != null)
			return "main"; // /WEB-INF/views/main.jsp
		else
		return "login/login";// /WEB-INF/login/login.jsp
		
		//이런형식이기 때문에 login 폴더는 WEB-INF 에 있어야한다.
		//prefix : /WEB-INF/views/
		//suffix: .jsp
	}
	
	
	
	/**
	 * Method : loginProcess
	 * 작성자 : PC21
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 로그인 요청처리 POST
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginProcess(String userId, String password, String remember
								,HttpServletResponse response, HttpSession session) {
		String encyptPassword = KISA_SHA256.encrypt(password);
		UserVo userVo = userService.getuser(userId);
		
		if(userVo != null && encyptPassword.equals(userVo.getPass())){
			rememberMeCookie(userId, remember, response);
			session.setAttribute("USER_INFO", userVo);
			return "main";
		}
		else
			return "login/login";
	}



	/**
	 * Method : rememberMeCookie
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param userId
	 * @param remember
	 * @param response
	 * Method 설명 : rememberme 쿠키를 응답으로 생성
	 */
	private void rememberMeCookie(String userId, String remember, HttpServletResponse response) {
		int cookieMaxAge = 0;
		if(remember != null)
			cookieMaxAge = 60*60*24*30;
		
		Cookie userIdCookie = new Cookie("userId",userId);
		userIdCookie.setMaxAge(cookieMaxAge);
		
		Cookie rememberCookie = new Cookie("remember", "true");
		rememberCookie.setMaxAge(cookieMaxAge);
		
		response.addCookie(userIdCookie);
		response.addCookie(rememberCookie);
	}
}
