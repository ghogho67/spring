package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.placeholder.DbInfo;

@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-placeholder.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringPlaceHolderTest {
	
	@Resource(name="dbInfo")
	private DbInfo db;
	
	@Test
	public void dbInfoTest() {
		/***Given***/
		/***When***/
		/***Then***/
		
		assertEquals("oracle.jdbc.driver.OracleDriver", db.getDriver());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", db.getUrl());
		assertEquals("pc21", db.getUsername());
		assertEquals("java", db.getPassword());
		
	}

}
