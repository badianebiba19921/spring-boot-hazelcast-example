package com.techgeeknext.ebb.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employee {
    private static final long serialVersionUID = 1L;
    private String empId;
    private String empName;
}