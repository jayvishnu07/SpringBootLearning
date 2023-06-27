package AOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("AOP");
        ShoppingCart shoppingCart = context.getBean(ShoppingCart.class);
        shoppingCart.checkout();
    }
}
