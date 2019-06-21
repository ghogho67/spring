package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	//joinPoint는 application-aop.xml 에서 expression 설정에 준 범위에 메서드를 갖고올수있다.
	public void before(JoinPoint joinpoint) {
		logger.debug("LOgging Aspect before method");
	}
	
	public void after(JoinPoint joinpoint) {
		logger.debug("LOgging Aspect after method");
	}
	
	public Object around(ProceedingJoinPoint joinpoint) throws Throwable {
		//business logic 실행전
		logger.debug("Logging Aspect around method before");
		
		//business logic 실행
		logger.debug("method name : {}", joinpoint.getSignature().getName());
		Object[] methodArgs = joinpoint.getArgs(); // joinpoint를 통해서 메서드 인자값을 받아준다.
		logger.debug("methodArgs : {}", methodArgs);
		Object returnObj = joinpoint.proceed(methodArgs); //인자값을 받아서 리턴해준다.
		logger.debug("returnObj : {}", returnObj);
		
		//business logic 실행후
		logger.debug("Logging Aspect around method after");
		
		
		return returnObj;
	}
}
