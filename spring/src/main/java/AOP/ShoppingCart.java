package AOP;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {
    public String checkout(){
        System.out.println("Check out.");
        return "null";
    }
}
