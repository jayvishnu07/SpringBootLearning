package com.crudApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args)  {
		SpringApplication.run(CrudApplication.class, args);
	}

}


//{
//		"assignedBy":"Achyuth",
//		"assignedTo":"Jaivishnu",
//		"taskDescription":"CRUD Application",
//		"assignedDate":"2023-06-05",
//		"deadLine":"2023-06-10",
//		"priority":"High",
//		"status":"completed"
//		}



//	Get all Tasks : http://localhost:8080/api/v1/tasks
//
//		Get : http://localhost:8080/api/v1/tasks/id/1
//
//		Post : http://localhost:8080/api/v1/tasks
//
//		Put : http://localhost:8080/api/v1/tasks/id/1
//
//		Delete : http://localhost:8080/api/v1/tasks/id/1