package aop.aspects;

import aop.Book;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Component
@Aspect
@Order(10)
public class LoggingAspect {

    @Before("execution(void aop.UniLibrary.get*(String))")
    public void beforeGetBookAdviceFromUniLibrary() {
        System.out.println("beforeGetBookAdvice: Trying to get book");
    }

    @Before("execution(public void get*(aop.Book, ..))")
//    @Before("execution(public void get*(..))")
    public void beforeGetBookAdvice() {
        System.out.println("beforeGetBookAdvice: Trying to get book");
    }

    @Before("execution(public * returnBook())")
    public void beforeReturnBookAdvice() {
        System.out.println("beforeReturnBookAdvice: Trying to return book");
    }

    @Pointcut("execution(* aop.UniLibrary.get*())")
    private void allGetMethodsFromUniLibrary() {}

    @Pointcut("execution(* aop.UniLibrary.return*())")
    private void allReturnMethodsToUniLibrary() {}

    @Pointcut("allGetMethodsFromUniLibrary() || allReturnMethodsToUniLibrary()")
    private void allGetAndReturnMethodsFromUniLibrary() {}

    @Pointcut("execution(* aop.UniLibrary.*(..))")
    private void allMethodsFromUniLibrary() {}

    @Pointcut("allMethodsFromUniLibrary() && !allReturnMethodsToUniLibrary()")
    private void allMethodsExceptReturnMagazineToUniLibrary() {}

    @Before("allGetMethodsFromUniLibrary()")
    public void beforeGetLoggingAdvice() {
        System.out.println("beforeGetLoggingAdvice: writing Log#1");
    }

    @Before("allReturnMethodsToUniLibrary()")
    public void beforeReturnLoggingAdvice() {
        System.out.println("beforeReturnLoggingAdvice: writing Log#2");
    }

    @Before("allGetAndReturnMethodsFromUniLibrary()")
    public void beforeGetAndReturnLoggingAdvice() {
        System.out.println("beforeGetAndReturnLoggingAdvice: writing Log#3");
    }

    @Before("allMethodsExceptReturnMagazineToUniLibrary()")
    public void beforeAllMethodsExceptReturnMagazineLoggingAdvice() {
        System.out.println("beforeAllMethodsExceptReturnMagazineLoggingAdvice: writing Log#10");
    }

    @Before("aop.aspects.MyAspects.allAddMethods()")
    public void beforeAddLoggingAdvice(JoinPoint joinPoint) {
        System.out.println("beforeAddLoggingAdvice: Logging of trying add book");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("methodSignature = " + methodSignature);
        System.out.println("methodSignature.getMethod() = " + methodSignature.getMethod());
        System.out.println("methodSignature.getReturnType() = " + methodSignature.getReturnType());
        System.out.println("methodSignature.getName() = " + methodSignature.getName());
        if("addBook".equals(methodSignature.getName())) {
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if(arg instanceof Book) {
                    Book book = (Book) arg;
                    System.out.println("Book info: " + book);
                } else if(arg instanceof String) {
                    System.out.println("Book added by: " + arg);
                }
            }
        }
    }

    @Around("execution(public String returnBook())")
    public String aroundReturnBookLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        System.out.println("aroundReturnBookLoggingAdvice: в библиотеку возвращают книгу");
        String result = "111111";
        try {
            result = (String)proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println("Logging exception: " + e);
            throw e;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Время: " + (endTime - beginTime) + "mc");
        return result;
    }

}
