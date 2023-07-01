package com.crudApplication.Controller;

import com.crudApplication.Entity.Task;
import com.crudApplication.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Set;


@RestController
@RequestMapping("api/")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("v1/tasks")
    public ResponseEntity<Object> addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }
    @PostMapping("v2/tasks")
    public ResponseEntity<Object> addTaskV2(@RequestBody Task task) {
        task.setAssignedDate(String.valueOf(LocalDate.now()));
        System.out.println("task = >" + task);
        return taskService.addTaskV2(task);
    }

    ;

    @GetMapping("/v1/tasks/")
    public ResponseEntity<Object> getTask() {
        return taskService.getTask();
    }

    @GetMapping("/v1/tasks/created")
    public ResponseEntity<Object> getTaskByAssignedDate(@RequestParam(value = "created", required = true) @Valid Set<String> assigned_date,
                                                        @RequestParam(value = "by", required = true) @Valid String assigned_by,
                                                        @RequestParam(value = "to", required = true) @Valid String assigned_to
    ) {
        return taskService.getTaskByAssignedDate(assigned_date, assigned_by, assigned_to);
    }

    @GetMapping("/v1/tasks/due")
    public ResponseEntity<Object> getTaskByDue(@RequestParam(value = "due", required = false) Set<String> deadline,
                                               @RequestParam(value = "by", required = false) String assigned_by,
                                               @RequestParam(value = "to", required = false) String assigned_to
    ) {
        return taskService.getTaskByDue(deadline, assigned_by, assigned_to);
    }

    @GetMapping("/v1/tasksid/{taskId}")
    public ResponseEntity<Object> getTask(@PathVariable Long taskId) {
        return taskService.getTask(taskId);
    }

    @GetMapping("created/{assignedDate}")
    public ResponseEntity<Object> getTaskByAssignedDate(@PathVariable String assignedDate) {
        return taskService.getTaskByAssignedDate(assignedDate);
    }

    @GetMapping("/v1/tasks/due/{deadline}")
    public ResponseEntity<Object> getTaskByDue(@PathVariable String deadline) {
        return taskService.getTaskByDue(deadline);
    }

    @GetMapping("/v1/tasks/priority/{priority}")
    public ResponseEntity<Object> getTaskByPriority(@PathVariable String priority) {
        return taskService.getTaskByPriority(priority);
    }

    @GetMapping("/v1/tasks/status/{isCompleted}")
    public ResponseEntity<Object> getTaskByStatus(@PathVariable boolean isCompleted) {
        return taskService.getTaskByIsCompleted(isCompleted);
    }

    @DeleteMapping("/v1/tasks/id/{taskId}")
    @ResponseBody
    public ResponseEntity<Object> deleteTaskById(@PathVariable Long taskId) {
        return taskService.deleteTaskById(taskId);
    }

    @DeleteMapping("/v1/tasks/created/{assignedDate}")
    public ResponseEntity<Object> deleteTaskByAssignedDate(@PathVariable String assignedDate) {
        return taskService.deleteTaskByAssignedDate(assignedDate);
    }

    @DeleteMapping("/v1/tasks/due/{deadline}")
    public ResponseEntity<Object> deleteTaskByDue(@PathVariable String deadline) {
        return taskService.deleteTaskByDue(deadline);
    }

    @DeleteMapping("/v1/tasks/priority/{priority}")
    public ResponseEntity<Object> deleteTaskByPriority(@PathVariable String priority) {
        return taskService.deleteTaskByPriority(priority);
    }

    @DeleteMapping("/v1/tasks/status/{status}")
    public ResponseEntity<Object> deleteTaskByStatus(@PathVariable boolean isCompleted) {
        return taskService.deleteTaskByIsCompleted(isCompleted);
    }

    @PutMapping("/v1/tasks/id/{taskId}")
    @ResponseBody
    public ResponseEntity<Object> updateTask(@PathVariable Long taskId, @RequestBody Task data) {
        return taskService.updateTask(taskId, data);
    }

}
