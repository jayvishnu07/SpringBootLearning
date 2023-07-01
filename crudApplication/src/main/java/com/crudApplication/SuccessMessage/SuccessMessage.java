package com.crudApplication.SuccessMessage;

import com.crudApplication.Entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
@AllArgsConstructor
@Getter
@Component
public class SuccessMessage {
    private final String message;
    private final HttpStatus httpStatus;
    private List<Task> data=null;
}
