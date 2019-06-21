package kr.or.ddit.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfilingAspect {
	private static final Logger logger = LoggerFactory.getLogger(ProfilingAspect.class);
	
//	@Pointcut("execution(* kr.or.ddit..service*.*(..))")
	@Pointcut("within(kr.or.ddit..service.*)")
	public void dummy(){}
	
	
	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		
		
		logger.debug("method name : {}", joinPoint.getSignature().getName());
		long startTime = System.currentTimeMillis();
		logger.debug("startTime : {}", startTime);
		
		
		Object[] methodArgs = joinPoint.getArgs(); // joinpoint를 통해서 메서드 인자값을 받아준다.
		logger.debug("methodArgs : {}", methodArgs);
		Object returnObj = joinPoint.proceed(methodArgs); //인자값을 받아서 리턴해준다.
		
		long endTime = System.currentTimeMillis();
		logger.debug("endTime : {}", endTime);
		long time = endTime - startTime;
		logger.debug("time : {}", time);
		
		return returnObj;
	}
	

}
