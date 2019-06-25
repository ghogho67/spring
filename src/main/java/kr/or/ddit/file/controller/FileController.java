package kr.or.ddit.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import utill.PartUtill;


@RequestMapping("/file")
@Controller
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	/**
	 * Method : uploadView
	 * 작성자 : PC21
	 * 변경이력 :
	 * @return
	 * Method 설명 : file upload를 할 수 있는 테스트 view리턴(화면처리)
	 */
	@RequestMapping("/uploadView")
	public String uploadView() {
		return "upload/upload";
	}
	
	
	
	/**
	 * Method : upload
	 * 작성자 : PC21
	 * 변경이력 :
	 * @param file
	 * @param model
	 * @return
	 * Method 설명 : file upload를 처리해준다.
	 */
	@RequestMapping("/upload")
	public String upload(@RequestPart("img")MultipartFile file,Model model){

		logger.debug("file.getOriginalFilename() : {}", file.getOriginalFilename());
		logger.debug("file.getName() : {}", file.getName());
		logger.debug("file.getSize() : {}", file.getSize());
		
		String path = PartUtill.getUploadPath();
		String ext = PartUtill.getExt(file.getOriginalFilename());
		String fileName = UUID.randomUUID().toString();
	
		File uploadfile = new File(path + File.separator + fileName + ext);
		logger.debug("file.getSize() : {}", File.separator);
				
		try {
			file.transferTo(uploadfile);
			model.addAttribute("msg", "SUCCESS");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			model.addAttribute("msg", "FAIL");
		}
		
		return "upload/upload";
	}
}
