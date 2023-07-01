package com.crudApplication.Entity;

import com.crudApplication.ExceptionHandler.TaskNotFoundException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "task_sequence")
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "assigned_by")
//    @Pattern(regexp = "[a-zA-Z]",message = "Invalid value for assignedBy")
    private String assignedBy;

    @Pattern(regexp = "[a-zA-Z]+", message = "Invalid value for assignedTo")
    private String assignedTo;

    @Column(name = "task_description")
    private String taskDescription;


    private String assignedDate;


    private String due;

    private String priority;

    @Column(name = "status")
    private boolean isCompleted;

    @Transient
    public String getAssignedDate() {

        try {
            assignedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignedDate;
    }

    ;

    @Transient
    public boolean getIsCompleted() {
        isCompleted = false;
        return false;
    }

    ;

    @Transient
    public String getDuration() {
        if (due != null) {
               String days;
               LocalDateTime dateTime2;
               Duration duration;
               Long result;
           try{
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
               DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

               LocalDateTime currentDateTime = LocalDateTime.now();

               if (due.length() <= 10) {
                   LocalDate dueDate = LocalDate.parse(due, formatter1);
                   long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
                   days = String.valueOf(daysBetween);
               } else {
                   dateTime2 = LocalDateTime.parse(due, formatter);
                   duration = Duration.between(currentDateTime, dateTime2);
                   long daysPart = duration.toDays();
                   long hoursPart = duration.toHoursPart();
                   long minutesPart = duration.toMinutesPart();
                   days = "Days: " + daysPart + ", Hours: " + hoursPart + ", Minutes: " + minutesPart;
               }

           }
           catch (Exception e){
               throw new TaskNotFoundException(e.getMessage());
           }
            return days;
        }
        return null;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}

