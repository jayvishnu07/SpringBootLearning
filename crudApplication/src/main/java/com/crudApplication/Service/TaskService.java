package com.crudApplication.Service;

import com.crudApplication.Entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public interface TaskService {

    ResponseEntity<Object> addTask(Task task);

    ResponseEntity<Object> getTaskByAssignedDate(Set<String> assigned_date, String assigned_by, String assigned_to);
    ResponseEntity<Object> getTaskByAssignedDate(String assigned_date);
    ResponseEntity<Object> getTask(Long taskId);
    ResponseEntity<Object> getTask();
    ResponseEntity<Object> getTaskByDue(Set<String> deadline, String assignedBy, String assignedTo);
    ResponseEntity<Object> getTaskByDue(String deadline);

    ResponseEntity<Object> getTaskByPriority(String priority);

    ResponseEntity<Object> getTaskByIsCompleted(boolean isCompleted);

    ResponseEntity<Object> deleteTaskById(Long taskId);

    ResponseEntity<Object> deleteTaskByAssignedDate(String assignedDate);

    ResponseEntity<Object> deleteTaskByDue(String deadline);

    ResponseEntity<Object> deleteTaskByPriority(String priority);

    ResponseEntity<Object> deleteTaskByIsCompleted(boolean isCompleted);

    ResponseEntity<Object> updateTask(Long task_id, Task data);

    ResponseEntity<Object> addTaskV2(Task task);

}
