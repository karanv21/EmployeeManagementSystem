package model;

import model.exceptions.ManagerSalaryException;

public class Manager {

    private String name;
    private int yearsWorked;
    private int salary;
    private int employeeID;
    private String branchLocation;
    private int minManagerSalary = 130000;

    //Constructor
    public Manager(String name, String branchLocation, int yearsWorked, int salary, int employeeID) {
        this.name = name;
        this.yearsWorked = yearsWorked;
        this.salary = salary;
        this.employeeID = employeeID;
        this.branchLocation = branchLocation;
    }

    //Getter
    public String getName() {
        return name;
    }

    //Getter
    public int getYearsWorked() {
        return yearsWorked;
    }

    //Getter
    public int getSalary() {
        return salary;
    }

    //Getter
    public int getEmployeeID() {
        return employeeID;
    }

    //Modifies: this
    //Effects: sets the salary of the manager
    public void setSalary(int salary) throws ManagerSalaryException {
        try {
            this.salary = salary;
            if (this.salary < minManagerSalary) {
                this.salary = minManagerSalary;
                throw new ManagerSalaryException();
            }
        } catch (ManagerSalaryException me) {
            me.printStackTrace();
            System.out.println("Sorry minimum salary can only be: " + minManagerSalary);
        }
    }

    //Getter
    public String getBranchLocation() {
        return branchLocation;
    }

    //Modifies: this
    //Effects: increases yearsworked
    public void increaseYearsWorked() {
        ++this.yearsWorked;
    }

    //Modifies: this
    //Effects: gives a raise of amount to the manager (this)
    public int giveRaise(int amount) {
        if (amount >= 0) {
            this.salary += amount;
            return this.salary;
        }
        return this.salary;
    }

    //Modifies: this
    //Effects: Gives a raise to the manager (this) every 3 years
    public int giveRaise() {
        if (this.yearsWorked % 3 == 0) {
            this.salary = (this.salary + 3000);
            this.yearsWorked++;
            return this.salary;
        } else {
            this.yearsWorked++;
            return this.salary;
        }
    }
}
