package com.backend.springbootLearning.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student jaivishnu = new Student("Jaivishnu","jaivishnu@gmail.com", LocalDate.of(2003,4,23));
            Student jvs = new Student("JVS","jvs@gmail.com", LocalDate.of(2003,4,23));
            Student siva = new Student("siva","siva@gmail.com", LocalDate.of(2003,4,22));
            repository.saveAll(List.of(jaivishnu,jvs,siva));
        };
    }
}
