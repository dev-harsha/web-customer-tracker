package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declaration for controller package
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	// setup pointcut declaration for service package
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	// setup pointcut declaration for DAO package
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void beore(JoinPoint joinPoint) {
		
		// display method we are calling
		String method = joinPoint.getSignature().toShortString();
		logger.info("===> @Before: calling method: " + method);
		
		// display the arguments to the method
		Object[] args = joinPoint.getArgs();
		
		for(Object arg : args) {
			logger.info("===> argument: " + arg);
		}
		
	}
	
	@AfterReturning(pointcut = "forAppFlow()",
					returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		
		// display method we are calling
		String method = joinPoint.getSignature().toShortString();
		logger.info("===> @Before: calling method: " + method);
		
		// display data returned
		logger.info("===> Result: " + result);
				
	}
	
	
}
