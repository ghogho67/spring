package kr.or.ddit.batch;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/applicattion-batch-dev.xml",
		"classpath:kr/or/ddit/config/spring/application-datasource-dev.xml",
		"classpath:kr/or/ddit/config/spring/application-transaction.xml"})
public class YogultBatchTest {
	
	@Resource(name = "jobLauncher")
	private JobLauncher jobLauncher;
	
//	@Resource(name = "rangerJob")
//	private Job rangerJob;
	
	@Resource(name = "yogultJob")
	private Job yogultJob;
	
	
	
	/**
	 * Method : rangerBatchTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 발효유 일 식적 생성 배치
	 */
	@Test
	public void yogultBatchTest() throws Exception {
		/***Given***/
		String st = "201907";

		/***When***/
		JobExecution jobExecution = jobLauncher.run(yogultJob, new JobParametersBuilder().addParameter("ym", new JobParameter(st))
																						.addLong("start_dt", System.currentTimeMillis())
																						.toJobParameters());
		/***Then***/
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Method : rangerBatchTest
	 * 작성자 : PC21
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 레인저스 이름 수정 배치 테스트
	 */
//	@Test
//	public void rangerBatchTest() throws Exception {
		/***Given***/
		

		/***When***/
//		JobExecution jobExecution = jobLauncher.run(rangerJob, new JobParameters());
		/***Then***/
//		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
//	}

}
