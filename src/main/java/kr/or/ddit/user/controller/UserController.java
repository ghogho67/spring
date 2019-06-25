package kr.or.ddit.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IuserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	private IuserService userService;

	/**
	 * Method : userList
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param model
	 * @return
	 * Method 설명 : 사용자 전체 조회
	 */
	@RequestMapping("/userList")
	public String userList(Model model) {
		/*
		userList객체를 userList.jsp에서 참고할 수 있도록 request객체에 속성으로 넣어준다
		request.setAttribute("userList",userService.userList());
		userList객체를 이용하여 사용자 화면 생성하는 jsp
		request.getRequestDispatcher("/user/userList.jsp").forward(request, response);
		*/
			
		model.addAttribute("userList", userService.userList());
		return "user/userList";
	}
	
	@RequestMapping("/userPagingList")
	/*인자 값으로 개별파라미터를 갖고는 로직 디폴트도 설정하였다.  vo필드에 동일한 이름 값이 같은게 있으면 자동으로 파라미터값으로 들어온다
	public String userPagingList(@RequestParam(name ="page", defaultValue = "1")int page,
								 @RequestParam(name ="pageSize", defaultValue = "10")int pageSize,
								 Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		밑에는 jsp필드에 동일한 이름을 갖고있어 value 값을 받아본다. */
	
	public String userPagingList(PageVo pageVo, Model model) {
		//page, pageSize의 값이 없을때 기본 값을 설정하는것은 pageVo get에 설정한다
		
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int paginationSize = (Integer)resultMap.get("paginationSize");
		
		model.addAttribute("userList",userList);
		model.addAttribute("paginationSize",paginationSize);
		model.addAttribute("pageVo",pageVo);
		return "user/userPagingList";
	}
	
}
