package com.crudApplication.SuccessMessage;

import com.crudApplication.Entity.Task;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@NoArgsConstructor
public class SuccessHandler {

    public ResponseEntity<Object> successMessageHandler(String message, List<Task> task){
        SuccessMessage successMessage1 = new SuccessMessage(message, HttpStatus.OK,task);
        return ResponseEntity.status(HttpStatus.OK).body(successMessage1);
    }
    public ResponseEntity<Object> successMessageHandler(String message, Task task){
        List<Task> tasks = List.of(task);
        SuccessMessage successMessage1 = new SuccessMessage(message, HttpStatus.OK,tasks);
        return ResponseEntity.status(HttpStatus.OK).body(successMessage1);
    }
}
