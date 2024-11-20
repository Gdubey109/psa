package com.crm.service;

import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }
    //create add employee method for employee entity
    public EmployeeDto addEmployee(EmployeeDto dto) {
        Employee employee= mapToEntity(dto);// this will take a dto object converted to entity and it will return back to the Employee object
        Employee emp = employeeRepository.save(employee); // save content is entity
        EmployeeDto employeeDto = mapToDto(emp);// this will get converted to EmployeeDto
       // employeeDto.setDate(new Date());
        return employeeDto ; // this method is returning back is a DTO
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto UpdateEmployee(Long id, EmployeeDto dto) {
        Employee employee= mapToEntity(dto);
        employee.setId(id);
        Employee updateEmployee= employeeRepository.save(employee);
        EmployeeDto employeeDto= mapToDto(updateEmployee);
        return employeeDto;

    }

    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {
   Sort sort =   sortDir.equalsIgnoreCase("asc") ?Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        Pageable page =PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees= all.getContent();
        List<EmployeeDto> dto= employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

   EmployeeDto mapToDto(Employee employee){ // job of this method is to convert your entity to dto
        //now i use that the employee object content and put that to dto

       EmployeeDto dto= modelMapper.map(employee, EmployeeDto.class);

       //   EmployeeDto dto = new EmployeeDto();
      //   dto.setId(employee.getId());
      //   dto.setName(employee.getName());
    //         dto.setEmailId(employee.getEmailId());
//         dto.setMobile(employee.getMobile());
         return dto;
         // whenever i call this method and supply the entity object to it that entity
       // object will be converted to dto amd that dto is what it is going to return
    }
    Employee mapToEntity(EmployeeDto dto){ // job of this method is to convert your dto to entity
        //now my job here will be whatever dto content is there it has to copy that to entity

        Employee emp= modelMapper.map(dto, Employee.class);

//       Employee emp = new Employee();
//       emp.setId(dto.getId());
//       emp.setName(dto.getName());
//       emp.setEmailId(dto.getEmailId());
//       emp.setMobile(dto.getMobile());
       return emp;
        // whenever i call this method and supply the entity object to it that entity
        // object will be converted to dto amd that dto is what it is going to return
    }

    public EmployeeDto getEmployeeId(long empId) {
        Employee employee=employeeRepository.findById(empId).orElseThrow(
                () -> new ResourceNotFound("Employee not found with id: " + empId)
        );
           EmployeeDto dto = mapToDto(employee);
           return dto;
    }
}
