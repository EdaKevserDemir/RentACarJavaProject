package com.kodlamaio.rentACar.core.utilities.aspects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component // classdan nesne üretme işini spring e bırakıyoruz
public class LoggingAspect {
	//List<StringBuilder> builderList = new ArrayList<StringBuilder>();

	// @Before("execution(*
	// com.kodlamaio.rentACar.business.concretes.BrandManager.*(..))")
	@Before("execution(* com.kodlamaio.rentACar.business.concretes.*.*(..))")
	public void beforeLog(JoinPoint joinPoint) throws JSONException, IOException {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		StringBuilder builder = new StringBuilder();
		ObjectMapper mapper = new ObjectMapper();
		
		builder.append("\n{");
		builder.append(("\n" + "\"date\":") + mapper.writeValueAsString(LocalDate.now().getYear() + "-"+LocalDate.now().getMonthValue() + "-" + LocalDate.now().getDayOfMonth()));
		
		builder.append("\n" + "\"className\":" + mapper.writeValueAsString(joinPoint.getTarget().getClass().getSimpleName()));
		builder.append("\n" +  "\"methodName\":"  + mapper.writeValueAsString(signature.getMethod().getName()));

		if (signature.getMethod().getName() != "getAll") {
			builder.append("\n" + "\"parameters:\":" + mapper.writeValueAsString(joinPoint.getArgs())); // java reflection
		
		} else {
			builder.append("\n" + "\"parameter:\":" + "null");
			
		}
		builder.append("\n" +"}");
		
		File file = new File("C:\\logs\\operations.json");
				
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true)) ) {
			bufferedWriter.write(builder.toString());
		} catch (Exception e) {
			
		}}
}