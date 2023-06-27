package com.crudApplication.Repository;

import com.crudApplication.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByAssignedDate(LocalDate assignedDate);
    List<Task> findByDeadLine(LocalDate deadLine);
    List<Task> findByPriorityIgnoreCase(String priority);
    List<Task> findByStatusIgnoreCase(String priority);

}
