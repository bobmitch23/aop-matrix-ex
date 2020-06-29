package com.matrix.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Aspect
@Component
@Slf4j
public class AopLogger {

    @Before(value = "execution(* com.matrix.service.MatrixFunctionsServiceImpl.multiply(..))")
    public void logBeforeMultiply(JoinPoint joinPoint) {
        Object[] listOfMatrices = joinPoint.getArgs();
        log.info("We are going to multiply {} ", listOfMatrices);
    }

    @AfterReturning(pointcut = "execution(* com.matrix.service.MatrixFunctionsServiceImpl.multiply(..))", returning = "matrix")
    public void logAfterMultiply(Object matrix) {
        log.info("The multiplication resulted in {}", matrix.toString());
    }

    @Before(value = "execution(* com.matrix.service.MatrixFunctionsServiceImpl.findDeterminant(..))")
    public void logBeforeFindingDeterminant(JoinPoint joinPoint) {
        Object[] matrixObjects = joinPoint.getArgs();
        log.info("We are going to find the determinant of {}", matrixObjects[0].toString());
    }

    @AfterReturning(pointcut = "execution(* com.matrix.service.MatrixFunctionsServiceImpl.findDeterminant(..))", returning = "determinant")
    public void logAfterFindingDeterminant(Object determinant) {
        DecimalFormat df = new DecimalFormat("0.00");
        log.info("The determinant is {}", df.format(determinant));
    }
}
