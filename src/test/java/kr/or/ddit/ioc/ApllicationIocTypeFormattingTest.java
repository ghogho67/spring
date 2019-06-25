package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.typeConvert.model.FormattingVo;

@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-formatting.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ApllicationIocTypeFormattingTest {
	private static final Logger logger = LoggerFactory.getLogger(ApllicationIocTypeFormattingTest.class);
	
	@Resource(name = "formattingVo")
	private FormattingVo forVo;
	
	@Test
	public void test() {
		/***Given***/
		/***When***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String reg_dt = sdf.format(forVo.getReg_dt());
		String mod_dt = sdf.format(forVo.getReg_dt());
		
		/***Then***/
		
		assertNotNull(forVo.getReg_dt());
		logger.debug("forVo.getReg_dt() :{}",forVo.getReg_dt());
		assertNotNull(forVo.getMod_dt());
		logger.debug("forVo.getMod_dt() :{}",forVo.getMod_dt());
		
		assertEquals("2019-06-21", reg_dt);
		assertEquals("2019-06-21", mod_dt);
		assertEquals(22200, forVo.getNumber());
	}

}
