package com.sports.goteam.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//Declaring this class as an Aspect for AOP
@Aspect
//Declaring this class as a Spring Component so it's detected during component scanning
@Component
public class LoggingAspect {
	
	/*
	 * This annotation defines a Before advice
     * It will be executed before the execution of methods in the package "com.sports.goteam.service"
     * The asterisks (*) signify a wildcard match for any method name and any number of arguments
	 */
	@Before("execution(* com.sports.goteam.service.*.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		/*
		 * This prints a message before the method execution
         * It shows which method is about to be executed
		 */
		System.out.println("Before method: " + joinPoint.getSignature());
		// Additional custom message indicating a specific action, in this case, creating a player
		System.out.println("Creating Player - TK");
	}
	
	/*
	 * This annotation defines an After advice
     * Similar to the Before advice, it targets methods in "com.sports.goteam.service"
	 */
	@After("execution(* com.sports.goteam.service.*.*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		/*
		 * This prints a message after the method execution
         * It shows which method has just finished executing
		 */
		System.out.println("After method:" + joinPoint.getSignature());
		// Additional custom message indicating the completion of an action, here, player creation
        System.out.println("Player created - TK");
		
	}

}
