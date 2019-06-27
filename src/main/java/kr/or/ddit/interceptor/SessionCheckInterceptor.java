package kr.or.ddit.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.user.dao.IuserDao;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter {
	//세션을 체크 할꺼다 있으면 main 으로 가고 없으면 login 화면에 남는다.
	//세션이 없는 상태로 main으로 바로 들어가면 login 으로 들어간다. 세션이 없기때문이다.
	private static final Logger logger = LoggerFactory.getLogger(SessionCheckInterceptor.class);
	
	@Resource(name = "userDao")
	private IuserDao userDao;
	
	
	/**
	 * Method : preHandle
	 * 작성자 : PC21
	 * 변경이력 : 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * Method 설명 : 로그인한 사용자만 controller에 접근이 가능 하도록 체크
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("userDao :{}", userDao);
		request.setAttribute("userList", userDao.userList());
	 HttpSession session = request.getSession();
	 
	 //사용자 로그인 상태
	 if(session.getAttribute("USER_INFO")!=null)
		 return true;
	 
	 //로그인 상태가 아니라면? 로그인 페이지로 이동
	 else
		 response.sendRedirect(request.getContextPath()+"/login");
	 	return false;
		 
	
	}
	
	
	
	

}
