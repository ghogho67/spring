package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.user.model.UserVo;
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
		return "user/userPagingList";
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
	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userForm(UserVo userVo, String userId, /* @RequestPart("profile") */MultipartFile profile,
			Model model) { // Date 는 UserVo에 가면 DateTimeFormat 을 해서 썻다.
		logger.debug("=======================================================================");
		logger.debug("===========================userForm====================================");
		logger.debug("=======================================================================");
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
	public void profile(String userId, HttpServletResponse response, HttpServletRequest request) throws IOException {
		// 사용자 정보(path)를 조회
		UserVo vo = userService.getuser(userId);
		ServletOutputStream sos = response.getOutputStream();
		FileInputStream fis = null;
		String filePath = null;
		// 사용자가 업로드한 파일이 존재할 경우 : path
		if (vo.getPath() != null) {
			filePath = vo.getPath();
		} else {
			filePath = request.getServletContext().getRealPath("/image/no_image.gif");
			// webapp/image/no_image.gif
		}
		// path정보로 file을 읽어 드여서
		File file = new File(filePath);
		fis = new FileInputStream(file);

		byte[] buffer = new byte[512];

		// response객체에 스트림으로 써준다.
		while (fis.read(buffer, 0, 512) != -1) {
			sos.write(buffer);
		}
		fis.close();
		sos.close();
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
	public String userModify(UserVo userVo,MultipartFile profile, HttpSession session, Model model) {
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
			session.setAttribute("msg", "등록되었습니다");
			return "redirect:/user/user?userId="+userVo.getUserId();
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
