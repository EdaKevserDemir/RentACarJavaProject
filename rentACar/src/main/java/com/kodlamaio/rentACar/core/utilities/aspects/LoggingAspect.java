package com.kodlamaio.rentACar.core.utilities.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
//
//	@Pointcut("execution(*(com.kodlamaio.rentACar.business.concretes.*.*)")
//	public void pointcut() {
//	}

	// Advice
//	@Around("execution(* com.kodlamaio.rentACar.business.concretes.*.*)")
//	public void beforeLog(ProceedingJoinPoint proceedingJoinPoint) {
//		try {
//			System.out.println("before logging");
//			proceedingJoinPoint.proceed();
//			System.out.println("after returning");
//
//		} catch (Throwable e) {
//			System.out.println("after throwing");
//
//			e.printStackTrace();
//
//		}
//		System.out.println("after throwing");
//	}

//	public void beforeLog(JoinPoint joinPoint) {
//		MethodSignature signature=(MethodSignature)joinPoint.getSignature();
//		System.out.println("before brand manager deleteById");
//		System.out.println(joinPoint.FIELD_GET);
//		System.out.println(signature.getParameterNames()[0]);
//	
//	}

//	@After("pointcut()")
//	public void afterLog() {
//		System.out.println("after brand manager deleteById");
//	}
}
