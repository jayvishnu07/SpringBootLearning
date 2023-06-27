package com.crudApplication.Service;

import com.crudApplication.Entity.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task addTask(Task task);

    List<Task> getTask();
    Optional<Task> getTask(Long taskId);

    List<Task> getTaskByAssignedDate(LocalDate assignedDate);
    List<Task> getTaskByDeadLine(LocalDate deadLine);

    List<Task> getTaskByPriority(String priority);

    List<Task> getTaskByStatus(String status);

    String deleteTaskById(Long taskId);

    String deleteTaskByAssignedDate(LocalDate assignedDate);

    String deleteTaskByDeadLine(LocalDate deadLine);

    String deleteTaskByPriority(String priority);

    String deleteTaskByStatus(String status);

    Task updateTask(Long taskId, Task data);
}
