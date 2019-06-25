package kr.or.ddit.file.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerEnv;

public class FileControllerTest extends ControllerEnv {
	private static final Logger logger = LoggerFactory.getLogger(FileControllerTest.class);

	/**
	 * Method : uploadViewTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 : uploadView 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void uploadViewTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/file/uploadView")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("upload/upload", viewName);
	}
	
	/**
	 * Method : uploadTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * Method 설명 :file upload Test
	 * @throws Exception 
	 * @throws IOException 
	 */
//	@Test
//	public void uploadTest() throws IOException, Exception {
//		/***Given***/
//		File file = new File("d:\\main\\main02.jpg");
//		logger.debug("file.getName():{}",file.getName());
//		
//		FileInputStream fis = new FileInputStream(file);
//		MockMultipartFile mockMultipartFile = new MockMultipartFile("img", file.getName(), "", fis);
//		/***When***/
//		MvcResult mvcReslt = mockMvc.perform(fileUpload("/file/upload")
//							  .file(mockMultipartFile))
//							  .andReturn();
//		ModelAndView mav = mvcReslt.getModelAndView();
//		String viewName = mav.getViewName();
//		String msg = (String)mav.getModelMap().get("msg");
//		/***Then***/
//		assertEquals("SUCCESS", msg);
//		assertEquals("upload/upload", viewName);
//	}
	
	@Test
	public void uploadTest() throws IOException, Exception {
		/***Given***/
		
		// 배포할때 플로젝트 안에 이미지가 있어야되는데 그 경로를 맞춘듯 합니다.
//		File file = new File(getClass().getClassLoader().getResource("kr/or/ddit/testenv/main02.jsp").getFile());
		File file = new File("src/test/resources/kr/or/ddit/testenv/main02.jpg");
		
		FileInputStream fis = new FileInputStream(file);
		MockMultipartFile mockMultipartFile = new MockMultipartFile("img", file.getName(), "", fis);
		/***When***/
		MvcResult mvcReslt = mockMvc.perform(fileUpload("/file/upload")
							  .file(mockMultipartFile))
							  .andReturn();
		ModelAndView mav = mvcReslt.getModelAndView();
		String viewName = mav.getViewName();
		String msg = (String)mav.getModelMap().get("msg");
		/***Then***/
		assertEquals("SUCCESS", msg);
		assertEquals("upload/upload", viewName);
	}

}
