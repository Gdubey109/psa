package com.crm.controller;

import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/add")
  public ResponseEntity<?> addEmployee(
            @Valid @RequestBody EmployeeDto dto,
            BindingResult  result // any error occurs in EmployeeDto dto that error
            // automatically comes to this class and springBoot takes care of thats error
  ){
        // if you check whether error are there  or not
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        EmployeeDto employeedto = employeeService.addEmployee(dto);
        return new ResponseEntity<>(employeedto, HttpStatus.CREATED);
  }

  // http://localhost:8080/api/v1/employee?id=1
  @DeleteMapping
  public ResponseEntity<String> deleteEmployee(
          @RequestParam Long id
  ){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee Deleted", HttpStatus.OK);
  }

    // http://localhost:8080/api/v1/employee?id=1
    @PutMapping
    public ResponseEntity<EmployeeDto> UpdateEmployee(
            @RequestParam Long id,
            @RequestBody EmployeeDto dto
    ){
        EmployeeDto employeeDto= employeeService.UpdateEmployee(id, dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee?pageSize=3&pageNo=1&sortBy=email&sortDir=desc
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(
    @RequestParam(name="pageSize",required = false, defaultValue = "5") int pageSize,
     @RequestParam(name="pageNo",required = false, defaultValue = "0") int pageNo,
    @RequestParam(name="sortBy",required = false, defaultValue = "name") String sortBy, //by this you have to sort the record based on email,mobile,name etc.
    @RequestParam(name="sortDir",required = false, defaultValue = "asc") String sortDir
    ){
       List<EmployeeDto> employeesDto = employeeService.getEmployee(pageNo,pageSize,sortBy,sortDir  );
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }
    // http://localhost:8080/api/v1/employee/employeeId/1
         @GetMapping("/employeeId/{empId}")
         public ResponseEntity<EmployeeDto> getEmployeeById(
                 @PathVariable long empId
         ){
        EmployeeDto dto=employeeService.getEmployeeId(empId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
         }
}
