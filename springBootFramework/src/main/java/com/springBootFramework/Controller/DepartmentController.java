package com.springBootFramework.Controller;

import com.springBootFramework.Entity.Department;
import com.springBootFramework.Error.DepartmentNotFoundException;
import com.springBootFramework.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("get-department")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }
    @GetMapping("get-department/{departmentID}")
    public Optional<Department> getDepartmentById(@PathVariable Long departmentID) throws DepartmentNotFoundException {
        return departmentService.getDepartmentById(departmentID);
    }
    @GetMapping("get-department/byName/{departmentName}")
    public Optional<Department> getDepartmentByDepartmentName(@PathVariable String departmentName) throws DepartmentNotFoundException {
        return departmentService.getDepartmentByDepartmentName(departmentName);
    }
    @GetMapping("get-department/byName-i/{departmentName}")
    public Optional<Department> getDepartmentByDepartmentNameI(@PathVariable String departmentName) throws DepartmentNotFoundException {
        return departmentService.getDepartmentByDepartmentNameI(departmentName);
    }

    @PostMapping("/save-department")
    public Department saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @DeleteMapping("/delete-department/{departmentId}")
    public String deleteDepartment(@PathVariable Long departmentId) throws DepartmentNotFoundException {
        return departmentService.deleteDepartment(departmentId);
    }

    @PutMapping("/update-department/{departmentID}")
    private Department updateDepartment(@PathVariable Long departmentID, @RequestBody Department data) throws DepartmentNotFoundException {
        return departmentService.updateDepartment(departmentID,data);
    }

}
