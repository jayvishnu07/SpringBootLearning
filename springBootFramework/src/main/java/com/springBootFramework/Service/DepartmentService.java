package com.springBootFramework.Service;

import com.springBootFramework.Entity.Department;
import com.springBootFramework.Error.DepartmentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getAllDepartment();


    Optional<Department> getDepartmentById(Long departmentID) throws DepartmentNotFoundException;

    String deleteDepartment(Long departmentId) throws DepartmentNotFoundException;

    Department updateDepartment(Long departmentID, Department data) throws DepartmentNotFoundException;

    Optional<Department> getDepartmentByDepartmentName(String departmentName) throws DepartmentNotFoundException;

    Optional<Department> getDepartmentByDepartmentNameI(String departmentName) throws DepartmentNotFoundException;
}

