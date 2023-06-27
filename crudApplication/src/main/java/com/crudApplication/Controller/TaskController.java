package com.crudApplication.Controller;

import com.crudApplication.Entity.Task;
import com.crudApplication.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping("/add")
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    };
    @GetMapping("/")
    public List<Task> getTask(){
        return taskService.getTask();
    }
    @GetMapping("/id/{taskId}")
    public Optional<Task> getTask(@PathVariable Long taskId){
        return taskService.getTask(taskId);
    }
    @GetMapping("/assigned-date/{assignedDate}")
    public List<Task> getTaskByAssignedDate(@PathVariable LocalDate assignedDate){
        return taskService.getTaskByAssignedDate(assignedDate);
    }
    @GetMapping("/deadLine/{deadLine}")
    public List<Task> getTaskByDeadLine(@PathVariable LocalDate deadLine){
        return taskService.getTaskByDeadLine(deadLine);
    }
    @GetMapping("/priority/{priority}")
    public List<Task> getTaskByPriority(@PathVariable String priority){
        return taskService.getTaskByPriority(priority);
    }
    @GetMapping("status/{status}")
    public List<Task> getTaskByStatus(@PathVariable String status){
        return taskService.getTaskByStatus(status);
    }
    @DeleteMapping("/delete/id/{taskId}")
    public String deleteTaskById(@PathVariable Long taskId){
        return taskService.deleteTaskById(taskId);
    }
    @DeleteMapping("/delete/assigned-date/{assignedDate}")
    public String deleteTaskByAssignedDate(@PathVariable LocalDate assignedDate){
        return taskService.deleteTaskByAssignedDate(assignedDate);
    }
    @DeleteMapping("/delete/deadline/{deadLine}")
    public String deleteTaskByDeadLine(@PathVariable LocalDate deadLine){
        return taskService.deleteTaskByDeadLine(deadLine);
    }
    @DeleteMapping("/delete/priority/{priority}")
    public String deleteTaskByPriority(@PathVariable String priority){
        return taskService.deleteTaskByPriority(priority);
    }
    @DeleteMapping("/delete/status/{status}")
    public String deleteTaskByStatus(@PathVariable String status){
        return taskService.deleteTaskByStatus(status);
    }
    @PutMapping("/update/id/{taskId}")
    public Task updateTask(@PathVariable Long taskId,@RequestBody Task data){
        return taskService.updateTask(taskId,data);
    }


}
