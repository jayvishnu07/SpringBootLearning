package com.crudApplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @SequenceGenerator(name = "task_sequence",sequenceName = "task_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    private Long taskId;
    private String assignedBy;
    private String assignedTo;
    private String taskDescription;
    private LocalDate assignedDate;
    private LocalDate deadLine;
    private String priority;
    private String status;

    @Transient
    public int getTotalNumberOfDays(){
        if((assignedDate != null) && (deadLine != null) ){
            return (int) ChronoUnit.DAYS.between(assignedDate, deadLine);
        }
        return 0;
    };
}

