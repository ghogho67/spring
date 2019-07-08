package kr.or.ddit.batch;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/applicattion-batch-dev.xml"})
public class RangerBatchJobLauncherTestUtilsTest {
	
	@Autowired
	private JobLauncherTestUtils jobLaunchaerTestUtils;
	
	
	@Test
	public void rangerBatchTest() throws Exception {
		/***Given***/
		

		/***When***/
		JobExecution jobexecution = jobLaunchaerTestUtils.launchJob();
		/***Then***/
		assertEquals(ExitStatus.COMPLETED, jobexecution.getExitStatus());
	}

}
