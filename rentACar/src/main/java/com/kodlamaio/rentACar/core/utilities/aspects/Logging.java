package com.kodlamaio.rentACar.core.utilities.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {


//	@Before("execution(* com.kodlamaio.rentACar.business.concretes.*.getById())")
//	public void beforeLogging(ProceedingJoinPoint proceedingJoinPoint) {
//		try {
//			System.out.println("öncesinde kontrol et");
//			proceedingJoinPoint.proceed();
//			
//		} catch (Throwable e) {
//			System.out.println("hata verdiğinde kontrol et");
//			
//		}
//		finally {
//			System.out.println("her türlü calisti");
//		}
//	}
	


}
