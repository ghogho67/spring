package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.collection.IocCollection;

@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-collection.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringIocCollection {
	
	@Resource(name = "collectionBean")
	private IocCollection cb;

	@Test
	public void springCollectionTest() {
		/***Given***/
		/***When***/
		/***Then***/
		assertEquals("brown", cb.getList().get(0) );
		assertEquals("sally", cb.getList().get(1));
		assertEquals("cony", cb.getList().get(2));
		assertEquals("brown", cb.getMap().get("name"));
		assertEquals("2019-08-08", cb.getMap().get("birth"));
	
		assertTrue(cb.getSet().contains("brown"));
		assertTrue(cb.getSet().contains("sally"));
		assertTrue(cb.getSet().contains("cony"));
		assertEquals("brown", cb.getProperties().get("userId"));
		assertEquals("브라운", cb.getProperties().get("name"));
	}

}
