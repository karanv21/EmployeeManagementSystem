package model;

import model.exceptions.EmployeeSalaryException;
import observer.LocationObserver;

import java.util.Objects;

public class Employee implements LocationObserver {

    private String name;
    private String position;
    private int yearsWorked;
    private int salary;
    private int employeeID;
    private Location location;
    private static final int maxEmployeeSalary = 130000;

    //Constructor
    public Employee(String name, String position, int yearsWorked, int salary, int employeeID) {
        this.name = name;
        this.position = position;
        this.yearsWorked = yearsWorked;
        this.salary = salary;
        this.employeeID = employeeID;
        location = null;
    }

    //Getter
    public String getName() {
        return name;
    }

    //Getter
    public String getPosition() {
        return position;
    }

    //Getter
    public int getyearsWorked() {
        return yearsWorked;
    }

    //Getter
    public int getSalary() {
        return salary;
    }

    //Setter
    public void setSalary(int salary) {
        this.salary = salary;
    }

    //Getter
    public int getEmployeeID() {
        return employeeID;
    }

    //Getter
    public Location getAssignedLocation() {
        return location;
    }

    //Effects: returns true if student is assigned to a bus, false otherwise
    public boolean isAssignedToLocation() {
        if (location == null) {
            return false;
        }
        return true;
    }

    //Modifies: o, this
    //Effects: checks if the object passed is equal to this by checking its employeeID
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        Employee employee = (Employee) o;
        return employeeID == employee.employeeID;
    }

    //Effects: returns objects hashcode for use of equals
    @Override
    public int hashCode() {
        return Objects.hash(employeeID);
    }

    //Modifies: this
    //Effects: hires this (employee) to location if not already hired to a location
    //         if already assigned to a location, fires employee from that location and hires employee into this one
    public void assignToLocation(Location location) {
        if (getAssignedLocation() != location) {
            if (this.location != null) {
                this.location.fireEmployee(this);
            }
            this.location = location;
            this.location.hireEmployee(this);
        }
    }

    //Modifies: this
    //Effects: fires employee from employee's location
    public void removeFromLocation() {
        if (getAssignedLocation() != location) {
            this.location.fireEmployee(this);
            this.location = null;
            //bidirectional relationship!
        }
        System.out.println("We are firing an employee! " + getName());
    }

    //Modifies: this
    //Effects: increases employee's years worked
    public void increaseYearsWorked() {
        ++yearsWorked;
    }

    //Modifies this
    //Effects: increases the salary by a bonus amount
    public int giveRaise(int bonus) {
        if (bonus >= 0) {
            this.salary += bonus;
        }
        return this.salary;
    }

    //Modifies: this
    //Effects: Increases the employee's by 3000 if it has been three years since hired
    //         else increases employee salary by 3000
    public int giveRaise() throws EmployeeSalaryException {
        try {
            if (this.yearsWorked % 3 == 0) {
                this.salary = (this.salary + 3000);
                this.yearsWorked++;
                if (this.salary > maxEmployeeSalary) {
                    this.salary = maxEmployeeSalary;
                    throw new EmployeeSalaryException();
                }
            } else {
                this.yearsWorked++;
            }
        } catch (EmployeeSalaryException ex) {
            ex.printStackTrace();
            System.out.println("Sorry maximum salary can only be: " + maxEmployeeSalary);
        }
        return this.salary;
    }

    //Effects: prints out a statement whenever a location hires a new employee
    @Override
    public void update(Location location) {
        System.out.println("We are hiring a new employee! " + getName());
    }
}
