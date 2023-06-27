package com.crudApplication.Service;

import com.crudApplication.Entity.Task;
import com.crudApplication.ExceptionHandler.TaskNotFoundException;
import com.crudApplication.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImplementation implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTask() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isEmpty()){
            throw new TaskNotFoundException("Task with id "+taskId+" not found.");
        }
        return task;
    }

    @Override
    public List<Task> getTaskByAssignedDate(LocalDate assignedDate) {
        List<Task> tasks = taskRepository.findByAssignedDate(assignedDate);
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("Tasks with starting date "+assignedDate+" not found.");
        }
        return tasks;
    }

    @Override
    public List<Task> getTaskByDeadLine(LocalDate deadLine) {
        List<Task> tasks = taskRepository.findByDeadLine(deadLine);
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("Tasks with Ending date "+deadLine+" not found.");
        }
        return tasks;

    }

    @Override
    public List<Task> getTaskByPriority(String priority) {
        List<Task> tasks = taskRepository.findByPriorityIgnoreCase(priority);
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("Tasks with priority level "+priority+" not found.");
        }
        return tasks;
    }

    @Override
    public List<Task> getTaskByStatus(String status) {
        List<Task> tasks = taskRepository.findByStatusIgnoreCase(status);
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("Tasks with status "+status+" not found.");
        }
        return tasks;
    }

    @Override
    public String deleteTaskById(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isEmpty()){
            throw new TaskNotFoundException("Task with id "+taskId+" not found.");
        }
        taskRepository.deleteById(taskId);
        return "Task with id "+taskId+" deleted successfully 👍🏻.";
    }

    @Override
    public String deleteTaskByAssignedDate(LocalDate assignedDate) {
        List<Task> tasks = taskRepository.findByAssignedDate(assignedDate);
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("Task with starting date "+assignedDate+" not found.");
        }
        taskRepository.deleteAllInBatch(tasks);
        return "Tasks with starting date "+assignedDate+" deleted successfully 👍🏻.";
    }

    @Override
    public String deleteTaskByDeadLine(LocalDate deadLine) {
        List<Task> tasks = taskRepository.findByDeadLine(deadLine);
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("Task with deadLine "+deadLine+" not found.");
        }
        taskRepository.deleteAllInBatch(tasks);
        return "Tasks with deadLine "+deadLine+" deleted successfully 👍🏻.";
    }

    @Override
    public String deleteTaskByPriority(String priority) {
        List<Task> tasks = taskRepository.findByPriorityIgnoreCase(priority);
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("Task with priority level "+priority+" not found.");
        }
        taskRepository.deleteAllInBatch(tasks);
        return "Tasks with priority level "+priority+" deleted successfully 👍🏻.";
    }

    @Override
    public String deleteTaskByStatus(String status) {
        List<Task> tasks = taskRepository.findByStatusIgnoreCase(status);
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("Task with status "+status+" not found.");
        }
        taskRepository.deleteAllInBatch(tasks);
        return "Tasks with status "+status+" deleted successfully 👍🏻.";
    }

    @Override
    public Task updateTask(Long taskId,Task data){
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isEmpty()){
            throw new TaskNotFoundException("Task with id "+taskId+" not found.");
        }
        if((data.getTaskDescription()!=null) && (data.getTaskDescription().length()>0)){
            task.get().setTaskDescription(data.getTaskDescription());
        }
        if((data.getAssignedBy()!=null) && (data.getAssignedBy().length()>0)){
            task.get().setAssignedBy(data.getAssignedBy());
        }
        if((data.getAssignedTo()!=null) && (data.getAssignedTo().length()>0)){
            task.get().setAssignedTo(data.getAssignedTo());
        }
        if((data.getAssignedDate()!=null) && (data.getAssignedDate().isBefore(LocalDate.now()))){
            task.get().setTaskDescription(data.getTaskDescription());
        }
        if((data.getDeadLine()!=null) && (data.getDeadLine().isBefore(LocalDate.now()))){
            task.get().setDeadLine(data.getDeadLine());
        }
        if((data.getPriority()!=null) && (data.getPriority().length()>0)){
            task.get().setPriority(data.getPriority());
        }
        if((data.getStatus()!=null) && (data.getStatus().length()>0)){
            task.get().setStatus(data.getStatus());
        }
        taskRepository.save(task.get());
        return task.get();
    }

}
