package com.crudApplication.Repository;

import com.crudApplication.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByAssignedDateIn(Set<String> assigned_date);
    List<Task> findByAssignedDate(String assigned_date);
    List<Task> findByAssignedBy(String assigned_by);
    List<Task> findByAssignedTo(String assigned_to);
    List<Task> findByAssignedDateAndAssignedBy(String assigned_date, String assigned_by);
    List<Task> findByAssignedDateInAndAssignedBy(Set<String> assigned_date,String assigned_by);
    List<Task> findByDue(String deadline);
    List<Task> findByPriorityIgnoreCase(String priority);
    List<Task> findByIsCompleted(boolean isCompleted);

    List<Task> findByAssignedDateInAndAssignedByAndAssignedTo(Set<String> assigned_date, String assigned_by, String assigned_to);
    List<Task> findByAssignedByAndAssignedTo(String assignedBy, String assignedTo);
    List<Task> findByAssignedDateInAndAssignedTo(Set<String> assignedDate, String assignedTo);

    List<Task> findByDueInAndAssignedByAndAssignedTo(Set<String> deadline, String assignedBy, String assignedTo);

    List<Task> findByDueInAndAssignedBy(Set<String> deadline, String assignedBy);

    List<Task> findByDueInAndAssignedTo(Set<String> deadline, String assignedTo);

    List<Task> findByDueIn(Set<String> deadline);
    List<Task> findByTaskDescriptionIgnoreCase(String taskDescription);

    List<Task> findByDueAndAssignedTo(String assignedBy, String assignedTo);

    List<Task> findByAssignedByIgnoreCase(String assignedBy);

    List<Task> findByAssignedByAllIgnoreCase(String assignedBy);

    List<Task> findByDueInAndAssignedByAndAssignedToAllIgnoreCase(Set<String> deadline, String assignedBy, String assignedTo);

    List<Task> findByDueInAndAssignedByAllIgnoreCase(Set<String> deadline, String assignedBy);

    List<Task> findByDueInAndAssignedToAllIgnoreCase(Set<String> deadline, String assignedTo);

    List<Task> findByDueAndAssignedToAllIgnoreCase(String assignedBy, String assignedTo);

    List<Task> findByAssignedToAllIgnoreCase(String assignedTo);

    List<Task> findByAssignedDateInAndAssignedByAndAssignedToAllIgnoreCase(Set<String> assignedDate, String assignedBy, String assignedTo);

    List<Task> findByAssignedDateInAndAssignedByAllIgnoreCase(Set<String> assignedDate, String assignedBy);

    List<Task> findByAssignedDateInAndAssignedToAllIgnoreCase(Set<String> assignedDate, String assignedTo);

    List<Task> findByAssignedByAndAssignedToAllIgnoreCase(String assignedBy, String assignedTo);
}




