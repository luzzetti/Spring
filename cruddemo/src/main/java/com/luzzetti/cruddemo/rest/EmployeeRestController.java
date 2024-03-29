package com.luzzetti.cruddemo.rest;

import com.luzzetti.cruddemo.dao.EmployeeDAO;
import com.luzzetti.cruddemo.entity.Employee;
import com.luzzetti.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    //  quick and dirty:    inject employee dao
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    //  expose "/employees" endpoint and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    //  add mapping for GET /employees/{employeeId}

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    //  add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        //  Also just in case they pass an ID in JSON ... set id to 0
        //  this is to force a save of new item ... instead of update

        theEmployee.setId(0);

        employeeService.save(theEmployee);

        return theEmployee;

    }

    //  add mapping for PUT /employees - update an employee

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee)
    {
        employeeService.save(theEmployee);
        return theEmployee;
    }

    //  add mapping for DELETE /employees/{employeeId} - remove an employee

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee tempEmployee = employeeService.findById(employeeId);

        //  Throw exception if null

        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee with id: " + employeeId;

    }

}


