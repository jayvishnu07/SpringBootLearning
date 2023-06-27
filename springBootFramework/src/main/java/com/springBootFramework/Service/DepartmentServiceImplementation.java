package com.springBootFramework.Service;

import com.springBootFramework.Entity.Department;
import com.springBootFramework.Error.DepartmentNotFoundException;
import com.springBootFramework.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImplementation implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        departmentRepository.save(department);
        return department;
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(Long departmentID) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentID);
        if(department.isEmpty()){
            throw new DepartmentNotFoundException("Department with id "+departmentID+" not found");
        }
        return departmentRepository.findById(departmentID);
    }

    @Override
    public String deleteDepartment(Long departmentID) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentID);
        if(department.isEmpty()){
            throw new DepartmentNotFoundException("Department with id "+departmentID+" not found");
        }
        departmentRepository.deleteById(departmentID);
        return "Deleted Successfully";
    }

    @Override
    public Department updateDepartment(Long departmentID, Department data) throws DepartmentNotFoundException {
        Department department = departmentRepository.findById(departmentID).get();
        if(data == null){
            throw new DepartmentNotFoundException("Department with id "+departmentID+" not found");
        }
        if(Objects.nonNull(department.getDepartmentName()) && (department.getDepartmentName()).length()>0) {
            department.setDepartmentName(data.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentCode()) && (department.getDepartmentCode()).length()>0) {
            department.setDepartmentCode(data.getDepartmentCode());
        }
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> getDepartmentByDepartmentName(String departmentName) throws DepartmentNotFoundException {
        Department department = departmentRepository.findByDepartmentName(departmentName);
        if(department == null){
            throw new DepartmentNotFoundException("Department with name "+departmentName+" not found");
        }
        return Optional.of(department);
    }

    @Override
    public Optional<Department> getDepartmentByDepartmentNameI(String departmentName) throws DepartmentNotFoundException {
        Department department = departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
        if(department == null){
            throw new DepartmentNotFoundException("Department with name "+departmentName+" not found");
        }
        return Optional.of(department);
    }
}
