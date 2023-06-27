package demoForSpring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        //<!--  XML BASED CONFIGURATION  -->
//
//        //USING CLASS TYPE
//        Doctor doc = context.getBean(Doctor.class);
//        doc.help();
//
//        //USING OBJECT
//        Nurse nrs = (Nurse) context.getBean("nurse");
//        nrs.help();
//
//        //USING INTERFACE -> DECOUPLED
//        Staff staff = context.getBean(Nurse.class);
//        staff.help();
//
//        //CAN ASSIGN VALUE FOR A FIELD THROUGH XML FILE
//        System.out.println(doc.getQualification() );
//
//        //CAN CREATE OBJECT FOR ANOTHER CLASS

        //<!--  ANNOTATION BASED CONFIGURATION  -->
//
//        Staff staff = context.getBean(Doctor.class);
//        staff.help();

        //<!--  JAVA BASED CONFIGURATION  -->
        ApplicationContext context = new AnnotationConfigApplicationContext("demoForSpring");
        Doctor staff = context.getBean(Doctor.class);
        staff.setQualification("MBBS");
        System.out.println(staff);

        Doctor newDoc = context.getBean(Doctor.class);
        System.out.println(newDoc);
    }
}
