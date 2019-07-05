package kr.or.ddit.testenv;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.Classes;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jdk.nashorn.internal.ir.annotations.Ignore;
import kr.or.ddit.config.spring.ApplicationContext;
import kr.or.ddit.config.spring.ApplicationDatasource_dev;
import kr.or.ddit.config.spring.ApplicationTransaction;
import kr.or.ddit.config.spring.RootContext;

/*@ContextConfiguration({"classpath:kr/or/ddit/config/spring/application-datasource-dev.xml",
	   "classpath:kr/or/ddit/config/spring/application-context.xml",
	   "classpath:kr/or/ddit/config/spring/application-transaction.xml",
	   "classpath:kr/or/ddit/config/spring/root-context.xml"})*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContext.class, RootContext.class , ApplicationDatasource_dev.class, ApplicationTransaction.class})
//일반 자바 환경 -> 웹환경
//applicationContext --> 웹환경으로 바꿔주는 이노테이션은@WebAppConfiguration , 웹환경의 applicationContext 생성
//mockMvc 객체를 잘알아야된다. 이것과 같이 쓰는게 webApplication 이 있다. 프레젠테이션 3-4 33 
@WebAppConfiguration
public class ControllerEnv {
	@Resource(name ="datasource")
	private DataSource datasource;
	
	@Autowired  //
	protected WebApplicationContext ctx; // webApplication Context Spring container
	protected MockMvc mockMvc; //dispatcher Servlet
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.setContinueOnError(false);
		rdp.addScript(new ClassPathResource("kr/or/ddit/testenv/dbInit.sql"));
		DatabasePopulatorUtils.execute(rdp, datasource);
	}
	
	@Ignore
	@Test
	public void test() {}
}
