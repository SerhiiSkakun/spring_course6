package aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspects {

    @Pointcut("execution(* get*())")
    public void allGetMethods() {}

    @Pointcut("execution(* add*(..))")
    public void allAddMethods() {}
}
