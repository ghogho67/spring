package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.model.UserVoValidator;
import kr.or.ddit.user.service.IuserService;
import utill.PartUtill;

@RequestMapping("/user")
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userService")
	private IuserService userService;

	/**
	 * Method : userList 작성자 : PC21 변경이력 :
	 * 
	 * @param model
	 * @return Method 설명 : 사용자 전체 조회
	 */
	@RequestMapping("/list")
	public String userList(Model model) {
		/*
		 * userList객체를 userList.jsp에서 참고할 수 있도록 request객체에 속성으로 넣어준다
		 * request.setAttribute("userList",userService.userList()); userList객체를 이용하여 사용자
		 * 화면 생성하는 jsp
		 * request.getRequestDispatcher("/user/userList.jsp").forward(request,
		 * response);
		 */

		model.addAttribute("userList", userService.userList());
		return "user/userList";
	}
	
	
	@RequestMapping("/userListExcel")
	public String  userListExcel(Model model, String filename) {
		List<String> header = new ArrayList<String>();
		header.add("UserId");
		header.add("Name");
		header.add("Alias");
		header.add("Addr1");
		header.add("Addr2");
		header.add("Zipcd");
		header.add("Birth");
		
		model.addAttribute("header",header);
		model.addAttribute("filename",filename);
		model.addAttribute("data",userService.userList());
		return "userExcelView";
		
	}
	
	

	@SuppressWarnings("unchecked")
	@RequestMapping("/pagingList")
	/*
	 * 인자 값으로 개별파라미터를 갖고는 로직 디폴트도 설정하였다. vo필드에 동일한 이름 값이 같은게 있으면 자동으로 파라미터값으로 들어온다
	 * public String userPagingList(@RequestParam(name ="page", defaultValue =
	 * "1")int page,
	 * 
	 * @RequestParam(name ="pageSize", defaultValue = "10")int pageSize, Model
	 * model) {
	 * 
	 * PageVo pageVo = new PageVo(page, pageSize); 밑에는 jsp필드에 동일한 이름을 갖고있어 value 값을
	 * 받아본다.
	 */
	public String userPagingList(PageVo pageVo, Model model) {
		// page, pageSize의 값이 없을때 기본 값을 설정하는것은 pageVo get에 설정한다

		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);
//		return "user/userPagingList";
		return "tiles.userPagingList";
	}
	
	
	
	/**
	 * Method : userPagingListAjax
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param pageVo
	 * @param model
	 * @return
	 * Method 설명 :사용자 페이징 리스트 ajax 처리
	 */
	@RequestMapping("/pagingListAjax")
	public String userPagingListAjax(PageVo pageVo, Model model) {
		model.addAttribute("data", userService.userPagingList(pageVo));
		//{data:{userLisst : {userId : 'brown', name: '브라운'...},{},{},..........,}
		return "jsonView";
	}
	
	@RequestMapping("/pagingListAjaxHtml")
	public String userPagingListAjaxHtml(PageVo pageVo, Model model) {
		model.addAttribute("data", userService.userPagingList(pageVo));
		//{data:{userLisst : {userId : 'brown', name: '브라운'...},{},{},..........,}
		return "user/userPagingListAjaxHtml";
	}
	
	@RequestMapping("/pagingListAjaxView")
	public String pagingListAjaxView() {
		
		return "tiles.pagingListAjaxView";
	}

	/**
	 * Method : user 작성자 : PC21 변경이력 :
	 * 
	 * @param userId
	 * @param model
	 * @return Method 설명 : 사용자 상세조회
	 */
	@RequestMapping("/user")
	public String user(String userId, Model model) {
		model.addAttribute("userVo", userService.getuser(userId));
		return "user/user"; // 접두 접미 application-context에 지정된걸 보면 저렇게 넣어줘야된다.
	}
	
	
	/**
	 * Method : user 작성자 : PC21 변경이력 :
	 * 
	 * @param userId
	 * @param model
	 * @return Method 설명 : 사용자 정보 
	 */
	@RequestMapping("/userJson") // 다른 방법이 가능하지만 그것은 계속 호출하고 또 하고 이지만 
								 //이런방법으로하면 재사용으로 가능하다는뜻이다.
	public String userJson(String userId, Model model) {
		model.addAttribute("userVo", userService.getuser(userId));
		return "jsonView"; 
	}

	
	
	/**
	 * Method : userForm
	 * 작성자 : PC21
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 등록 화면 호출
	 */
	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public String userForm() {
		return "user/userForm";
	}

	/**
	 * Method : userForm 작성자 : PC21 변경이력 :
	 * 
	 * @param userVo
	 * @param userId
	 * @param profile
	 * @param model
	 * @return Method 설명 : 사용자 등록
	 */
//	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userForm(UserVo userVo,BindingResult result,String userId, /* @RequestPart("profile") */MultipartFile profile,
			Model model) { // Date 는 UserVo에 가면 DateTimeFormat 을 해서 썻다.
		new UserVoValidator().validate(userVo, result);
		
		if(result.hasErrors())
			return "user/userForm";
		
		String viewName = "";

		UserVo dbUser = userService.getuser(userId);
		if (dbUser == null) {
			if (profile.getSize() > 0) {
				String filePath = fileUpload(userVo, profile);
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			int insertCnt = userService.insertUser(userVo);
			if (insertCnt == 1)
				viewName = "redirect:/user/pagingList";
		} else {
			model.addAttribute("msg", "이미 존재하는 사용자 입니다.");
			viewName = userForm();
		}
		return viewName;
	}
	
	/**
	 * Method : userForm 작성자 : PC21 변경이력 :
	 * 
	 * @param userVo
	 * @param userId
	 * @param profile
	 * @param model
	 * @return Method 설명 : 사용자 등록
	 */
	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userFormJsr(@Valid UserVo userVo,BindingResult result,String userId, /* @RequestPart("profile") */MultipartFile profile,
			Model model) { // Date 는 UserVo에 가면 DateTimeFormat 을 해서 썻다.
		
		if(result.hasErrors())
			return "user/userForm";
		
		String viewName = "";

		UserVo dbUser = userService.getuser(userId);
		if (dbUser == null) {
			if (profile.getSize() > 0) {
				String filePath = fileUpload(userVo, profile);
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			int insertCnt = userService.insertUser(userVo);
			if (insertCnt == 1)
				viewName = "redirect:/user/pagingList";
		} else {
			model.addAttribute("msg", "이미 존재하는 사용자 입니다.");
			viewName = userForm();
		}
		return viewName;
	}



	/**
	 * Method : profile
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param userId
	 * @param response
	 * @param request
	 * @throws IOException
	 * Method 설명 : 사용자 사진 응답 생성
	 */
	@RequestMapping("/profile")
	public String profile(String userId,Model model) throws IOException {
		// 사용자 정보(path)를 조회
		UserVo userVo = userService.getuser(userId);
		model.addAttribute("userVo",userVo);
		
		return "profileView";
	}
	
	/**
	 * Method : userModify
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param userId
	 * @param model
	 * @return
	 * Method 설명 : 사용자 수정화면
	 */
	@RequestMapping(path = "/modify", method = RequestMethod.GET)
	public String userModify(String userId, Model model) {
		
		model.addAttribute("userVo",userService.getuser(userId));
		return "user/userModify";
		
	}
	
	//사용자 정보 수정
	@RequestMapping(path = "/modify", method = RequestMethod.POST)
	public String userModify(UserVo userVo,MultipartFile profile, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		//추후 ajax 요청으로 분리
		//userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		
		if(profile.getSize() > 0) {
			String filePath = fileUpload(userVo, profile);
			try {
				profile.transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int updateCnt = userService.updateUser(userVo);
		if(updateCnt == 1) {
//			session.setAttribute("msg", "등록되었습니다");
			redirectAttributes.addFlashAttribute("msg", "업데이트 되셧습니다.");
			redirectAttributes.addAttribute("userId",userVo.getUserId()); //자동으로 파라미터를 등록한다.
//			return "redirect:/user/user?userId="+userVo.getUserId(); // 직접 적어줘서 파라미터를 등록한다.
			return "redirect:/user/user";
		}else 
		
		return userModify(userVo.getUserId(), model);
		
	}
	
	
	private String fileUpload(UserVo userVo, MultipartFile profile) {
		String fileName = profile.getOriginalFilename();
		String ext = PartUtill.getExt(fileName);
		String uploadPath = PartUtill.getUploadPath();
		String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
		userVo.setPath(filePath);
		userVo.setFilename(fileName);
		return filePath;
	}

	

}
