package com.crudApplication.Service;

import com.crudApplication.Entity.Task;
import com.crudApplication.ExceptionHandler.TaskNotFoundException;
import com.crudApplication.Repository.TaskRepository;
import com.crudApplication.SuccessMessage.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TaskServiceImplementation implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    private final SuccessHandler successHandler;

    @Autowired
    public TaskServiceImplementation(SuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public ResponseEntity<Object> addTask(Task data) {
        System.out.println(LocalDate.now());
        try {
            LocalDate.parse((data.getDue().trim()), formatter1);
        } catch (Exception e) {
            throw new TaskNotFoundException("Invalid date.Expected format: yyyy-MM-dd");
        }

        if ((data.getDue() != null) && (LocalDate.parse(data.getDue(), formatter1).isAfter(LocalDate.now()))) {
            data.setDue(data.getDue());
        } else {
            throw new TaskNotFoundException("Invalid Due date.");
        }
        if(data.getAssignedTo() == null  || data.getAssignedBy() == null || data.getTaskDescription()== null || data.getPriority()==null ||
                data.getAssignedTo().length()<=0  || data.getAssignedBy().length()<=0 || data.getTaskDescription().length()<=0
        ){
            throw new TaskNotFoundException("Invalid Data.");
        }
        if(!(data.getPriority()).equalsIgnoreCase("low") && !(data.getPriority()).equalsIgnoreCase("normal") && !(data.getPriority()).equalsIgnoreCase("high")){
            throw new TaskNotFoundException("Invalid priority type.");
        }
        System.out.println(data);
        Task task = taskRepository.save(data);
        task.setAssignedDate(String.valueOf(LocalDate.now()));
        System.out.println(taskRepository.save(task));
        return successHandler.successMessageHandler("Task added successfully", taskRepository.save(task));
    }

    @Override
    public ResponseEntity<Object> addTaskV2(Task data) {
        try {
            LocalDate.parse(data.getDue(), formatter);
        } catch (Exception e) {
            throw new TaskNotFoundException("Invalid date.Expected format: yyyy-MM-dd HH:mm:ss");
        }

        if ((data.getDue() != null) && (LocalDate.parse(data.getDue(), formatter).isAfter(LocalDate.now()))) {
            data.setDue(data.getDue());
        } else {
            throw new TaskNotFoundException("Invalid Due date.");
        }
        if(data.getAssignedTo() == null  || data.getAssignedBy() == null || data.getTaskDescription()== null || data.getPriority()==null ||
                data.getAssignedTo().length()<=0  || data.getAssignedBy().length()<=0 || data.getTaskDescription().length()<=0
        ){
            throw new TaskNotFoundException("Invalid Data.");
        }
        if(!(data.getPriority()).equalsIgnoreCase("low") && !(data.getPriority()).equalsIgnoreCase("normal") && !(data.getPriority()).equalsIgnoreCase("high")){
            throw new TaskNotFoundException("Invalid priority type.");
        }
        return successHandler.successMessageHandler("Task added successfully", taskRepository.save(data));
    }

    @Override
    public ResponseEntity<Object> getTask() {
        return successHandler.successMessageHandler("Task added successfully", taskRepository.findAll().size()>0?taskRepository.findAll():null );
    }

    @Override
    public ResponseEntity<Object> getTaskByAssignedDate(Set<String> assigned_date, String assigned_by, String assigned_to) {
        List<Task> tasks = new ArrayList<>();
        System.out.println("DAte =  > "+DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        for (String dateValue: assigned_date) {
            if(dateValue != null){
                try {
                    LocalDate.parse(dateValue, formatter1);
                } catch (Exception e) {
                    throw new TaskNotFoundException("Invalid date.Expected format: yyyy-MM-dd");
                }
            }
        }
        if((assigned_by != null && assigned_to != null) && !((Object) assigned_by instanceof String) && !((Object) assigned_to instanceof String)){
            throw new TaskNotFoundException("Invalid Name.");
        }
        if (!assigned_date.isEmpty() && Objects.requireNonNull(assigned_by).length() > 0) {
            tasks = taskRepository.findByAssignedDateInAndAssignedByAndAssignedToAllIgnoreCase(assigned_date, assigned_by, assigned_to);
        } else if (!(assigned_date.isEmpty())  && !(assigned_by.equals(""))) {
            tasks = taskRepository.findByAssignedDateInAndAssignedByAllIgnoreCase(assigned_date, assigned_by);
        } else if ((assigned_date.getClass().equals(LocalDate.class)) && !(assigned_to.equals(""))) {
            tasks = taskRepository.findByAssignedDateInAndAssignedToAllIgnoreCase(assigned_date, assigned_to);
        } else if (assigned_date.getClass().equals(LocalDate.class) && !(assigned_by.equals("")) ) {
            tasks = taskRepository.findByAssignedByAndAssignedToAllIgnoreCase(assigned_by, assigned_to);
        } else if (assigned_date.getClass().equals(LocalDate.class)) {
            tasks = taskRepository.findByAssignedDateIn(assigned_date);
        } else if (!(assigned_by.equals(""))) {
            tasks = taskRepository.findByAssignedByAllIgnoreCase(assigned_by);
        } else if (!(assigned_to.equals(""))) {
            tasks = taskRepository.findByAssignedToAllIgnoreCase(assigned_to);
        } else {
            tasks = taskRepository.findAll();
        }
        return successHandler.successMessageHandler("Tasks retrieved successfully.", tasks.size()>0?tasks:null);
    }


    @Override
    public ResponseEntity<Object> getTaskByDue(Set<String> deadline, String assigned_by, String assigned_to) {
        List<Task> tasks = null;
        for (String dateValue: deadline) {
            if(dateValue != null){
                try {
                    LocalDate.parse(dateValue, formatter1);
                } catch (Exception e) {
                    throw new TaskNotFoundException("Invalid date.Expected format: yyyy-MM-dd HH:mm:ss");
                }
            }
        }
        if((assigned_by != null && assigned_to != null) && !((Object) assigned_by instanceof String) && !((Object) assigned_to instanceof String)){
            throw new TaskNotFoundException("Invalid Name.");
        }
        if (!deadline.isEmpty() && assigned_by.length() > 0) {
            tasks = taskRepository.findByDueInAndAssignedByAndAssignedToAllIgnoreCase(deadline, assigned_by, assigned_to);
        } else if (!(deadline.isEmpty())  && !(assigned_by.equals(""))) {
            tasks = taskRepository.findByDueInAndAssignedByAllIgnoreCase(deadline, assigned_by);
        } else if ((deadline.getClass().equals(LocalDate.class)) && !(assigned_to.equals(""))) {
            tasks = taskRepository.findByDueInAndAssignedToAllIgnoreCase(deadline, assigned_to);
        } else if (deadline.getClass().equals(LocalDate.class) && !(assigned_by.equals("")) ) {
            tasks = taskRepository.findByDueAndAssignedToAllIgnoreCase(assigned_by, assigned_to);
        } else if (deadline.getClass().equals(LocalDate.class)) {
            tasks = taskRepository.findByDueIn(deadline);
        } else if (!(assigned_by.equals(""))) {
            tasks = taskRepository.findByAssignedByAllIgnoreCase(assigned_by);
        } else if (!(assigned_to.equals(""))) {
            tasks = taskRepository.findByAssignedToAllIgnoreCase(assigned_to);
        } else {
            tasks = taskRepository.findAll();
        }
        return successHandler.successMessageHandler("Tasks retrieved successfully.", tasks.size()>0?tasks:null);
    }

    @Override
    public ResponseEntity<Object> getTask(Long task_id) {
        Optional<Task> task = taskRepository.findById(task_id);
        if (task.isEmpty()) {
            throw new TaskNotFoundException("Task with id " + task_id + " not found.");
        }
        return successHandler.successMessageHandler("Task retrieved successfully", task.get());
    }

    @Override
    public ResponseEntity<Object> getTaskByAssignedDate(String assigned_date) {
        List<Task> tasks = taskRepository.findByAssignedDate(assigned_date);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Tasks with starting date " + assigned_date + " not found.");
        }
        return successHandler.successMessageHandler("Task retrieved successfully", tasks);
    }

    @Override
    public ResponseEntity<Object> getTaskByDue(String deadline) {
        List<Task> tasks = taskRepository.findByDue(deadline);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Tasks with Ending date " + deadline + " not found.");
        }
        return successHandler.successMessageHandler("Task retrieved successfully", tasks);
    }

    @Override
    public ResponseEntity<Object> getTaskByPriority(String priority) {
        List<Task> tasks = taskRepository.findByPriorityIgnoreCase(priority);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Tasks with priority level " + priority + " not found.");
        }
        return successHandler.successMessageHandler("Task retrieved successfully", tasks);
    }

    @Override
    public ResponseEntity<Object> getTaskByIsCompleted(boolean isCompleted) {
        List<Task> tasks = taskRepository.findByIsCompleted(isCompleted);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Tasks with status isCompleted : " + isCompleted + " not found.");
        }
        return successHandler.successMessageHandler("Task retrieved successfully", tasks);
    }

    @Override
    public ResponseEntity<Object> deleteTaskById(Long task_id) {
        Optional<Task> task = taskRepository.findById(task_id);
        if (task.isEmpty()) {
            throw new TaskNotFoundException("Task with id " + task_id + " not found.");
        }
        taskRepository.deleteById(task_id);
        return successHandler.successMessageHandler("Task with id " + task_id + " deleted successfully.", task.get());
    }


    @Override
    public ResponseEntity<Object> deleteTaskByAssignedDate(String assigned_date) {
        List<Task> tasks = taskRepository.findByAssignedDate(assigned_date);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Task with starting date " + assigned_date + " not found.");
        }
        taskRepository.deleteAllInBatch(tasks);
        return successHandler.successMessageHandler("Tasks with starting date " + assigned_date + " deleted successfully.", tasks);
    }

    @Override
    public ResponseEntity<Object> deleteTaskByDue(String deadline) {
        List<Task> tasks = taskRepository.findByDue(deadline);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Task with deadLine " + deadline + " not found.");
        }
        taskRepository.deleteAllInBatch(tasks);
        return successHandler.successMessageHandler("Tasks with deadLine " + deadline + " deleted successfully.", tasks);
    }

    @Override
    public ResponseEntity<Object> deleteTaskByPriority(String priority) {
        List<Task> tasks = taskRepository.findByPriorityIgnoreCase(priority);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Task with priority level " + priority + " not found.");
        }
        taskRepository.deleteAllInBatch(tasks);
        return successHandler.successMessageHandler("Tasks with priority level " + priority + " deleted successfully.", tasks);
    }

    @Override
    public ResponseEntity<Object> deleteTaskByIsCompleted(boolean isCompleted) {
        List<Task> tasks = taskRepository.findByIsCompleted(isCompleted);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Task with status isCompleted : " + isCompleted + " not found.");
        }
        taskRepository.deleteAllInBatch(tasks);
        return successHandler.successMessageHandler("Tasks with status isCompleted : " + isCompleted + " deleted successfully.", tasks);
    }

    @Override
    public ResponseEntity<Object> updateTask(Long task_id, Task data) {
        Optional<Task> task = taskRepository.findById(task_id);
        if (task.isEmpty()) {
            throw new TaskNotFoundException("Task with id " + task_id + " not found.");
        }
        if ((data.getTaskDescription() != null) && (data.getTaskDescription().length() > 0)) {
            task.get().setTaskDescription(data.getTaskDescription());
        }
        if((data.getAssignedBy()!=null) && (data.getAssignedBy().length()>0)){
            task.get().setAssignedBy(data.getAssignedBy());
        }
        if ((data.getAssignedTo() != null) && (data.getAssignedTo().length() > 0)) {
            task.get().setAssignedTo(data.getAssignedTo());
        }
        if ((data.getDue() != null) && (LocalDate.parse(data.getDue(), formatter).isAfter(LocalDate.now()))) {
            task.get().setDue(data.getDue());
        }
        if ((data.getPriority() != null) && (data.getPriority().length() > 0)) {
            task.get().setPriority(data.getPriority());
        }
        try {
        Boolean isCompleted = data.getIsCompleted();
        task.get().setIsCompleted(data.getIsCompleted());
        }
        catch (Exception e){
            throw new TaskNotFoundException("Invalid Status.");
        }

        taskRepository.save(task.get());
        return successHandler.successMessageHandler("Tasks with id " + task_id + " updated successfully.", taskRepository.save(task.get()));
    }

}


