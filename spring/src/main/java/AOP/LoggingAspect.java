package AOP;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(String AOP.ShoppingCart.checkout())")
    public void beforeLogging(){
        System.out.println("Before Logging.");
    }
    @After("execution(String AOP.ShoppingCart.checkout())")
    public void afterLogging(){
        System.out.println("After Logging.");
    }
}
