package model;

import model.exceptions.EmployeeSalaryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    private String name;
    private String position;
    private int yearsWorked;
    private int salary;
    private int employeeID;
    private Employee employeeTestModel;
    private Location location1;
    private final int maxEmployeeSalary = 130000;

    @BeforeEach
    public void EmployeeSystemModelSetup() {
        name = "Bob";
        position = "Developer";
        yearsWorked = 0;
        salary = 0;
        employeeID = 1234;
        employeeTestModel = new Employee(name, position, yearsWorked, salary, employeeID);
        location1 = new Location("Downtown", 4500);
    }

    @Test
    public void getNameTest() {
        assertEquals(employeeTestModel.getName(), "Bob");
    }

    @Test
    public void getPositionTest() {
        assertEquals(employeeTestModel.getPosition(), "Developer");
    }

    @Test
    public void getEmployeeIDTest() {
        assertEquals(employeeTestModel.getEmployeeID(), 1234);
    }
    @Test
    public void getAssignedLocationTest() {
        assertEquals(employeeTestModel.getAssignedLocation(), null);
        employeeTestModel.assignToLocation(location1);
        assertEquals(employeeTestModel.getAssignedLocation(), location1);
    }

    @Test
    public void isAssignedToLocation() {
        employeeTestModel.assignToLocation(location1);
        assertTrue(employeeTestModel.isAssignedToLocation());
    }

    @Test
    void isAssignedToLocationFailTest() {
        assertFalse(employeeTestModel.isAssignedToLocation());
    }

    @Test
    void assignToLocationPassSingleTest() {
        employeeTestModel.assignToLocation(location1);
        assertEquals(employeeTestModel.getAssignedLocation(), location1);
    }

    @Test
    void assignToLocationPassMultipleTest() {
        Location location2 = new Location("Richmond", 2000);
        employeeTestModel.assignToLocation(location1);
        assertEquals(employeeTestModel.getAssignedLocation(), location1);
        employeeTestModel.assignToLocation(location2);
        assertEquals(location1.getEmployees().size(), 0);
        assertEquals(employeeTestModel.getAssignedLocation(), location2);
    }

    @Test
    void removeFromLocationTest() {
        employeeTestModel.removeFromLocation();
        assertNull(employeeTestModel.getAssignedLocation());
        employeeTestModel.assignToLocation(location1);
        assertNotNull(employeeTestModel.getAssignedLocation());
        employeeTestModel.removeFromLocation();
        assertNull(employeeTestModel.getAssignedLocation());
    }

    @Test
    public void giveRaiseTrueTest() {
        employeeTestModel.giveRaise( 1000);
        assertEquals(employeeTestModel.getSalary(), 1000);
    }

    @Test
    public void giveRaiseNegativeTest() {
        employeeTestModel.giveRaise( -1);
        assertEquals(employeeTestModel.getSalary(), 0);
    }

    @Test
    public void giveRaiseZeroAmountTest() {
        assertEquals(employeeTestModel.giveRaise( 0), employeeTestModel.getSalary());
    }

    @Test
    public void giveRaiseFailTest() {
        employeeTestModel.increaseYearsWorked();
        assertEquals(employeeTestModel.getyearsWorked(), 1);
        try {
            assertEquals(employeeTestModel.giveRaise(), 0);
        } catch (EmployeeSalaryException e) {
            e.printStackTrace();
        }
        assertEquals(employeeTestModel.getyearsWorked(), 2);
    }

    @Test
    public void giveRaiseSuccessTest() {
        try {
            for (int i = 0; i < 6; i++) {
                employeeTestModel.increaseYearsWorked();
            }
            assertEquals(employeeTestModel.giveRaise(), 3000);
            assertEquals(employeeTestModel.getyearsWorked(), 7);
            assertEquals(employeeTestModel.getSalary(), 3000);
        } catch (EmployeeSalaryException e) {
            fail("No exception supposed to be thrown...");
        }
    }

    @Test
    public void giveRaiseYearsTest() {
        try {
            for (int i = 0; i < 4; i++) {
                employeeTestModel.increaseYearsWorked();
            }
            assertEquals(employeeTestModel.giveRaise(), 0);
        } catch (EmployeeSalaryException e) {
            fail("No exception supposed to be thrown...");
        }
    }

    @Test
    public void giveRaiseMaxFailTest() {
        employeeTestModel.setSalary(maxEmployeeSalary);
        try {
            for (int i = 0; i < 6; i++) {
                employeeTestModel.increaseYearsWorked();
            }
            assertEquals(employeeTestModel.giveRaise(), 130000);
        } catch (EmployeeSalaryException e) {
            e.printStackTrace();
            System.out.println("Sorry maximum salary can only be: " + maxEmployeeSalary);
        }
    }

    @Test
    void EqualsNoTest() {
        Employee employee = new Employee("Bill", "Developer", 9, 75000, 456);
        Employee employee1 = new Employee("Steve", "Sales", 9, 75000, 455);
        Employee employee2 = new Employee("Satya", "Marketing", 9, 75000, 454);
        assertNotEquals(employee, employee1);
        assertNotEquals(employee1, employee2);
    }

    @Test
    void EqualsYesTest() {
        Employee employee = new Employee("Bill", "Developer", 9, 75000, 456);
        Employee employee1 = new Employee("Steve", "Sales", 9, 75000, 455);
        Employee employee2 = new Employee("Satya", "Marketing", 9, 75000, 456);
        assertEquals(employee, employee2);
        assertEquals(employee, employee);
    }

    @Test
    void HashCodeYesTest() {
        Employee employee = new Employee("Bill", "Developer", 9, 75000, 456);
        Employee employee1 = new Employee("Steve", "Sales", 9, 75000, 455);
        Employee employee2 = new Employee("Satya", "Marketing", 9, 75000, 456);
        assertEquals(employee.hashCode(), employee2.hashCode());
        assertEquals(employee.hashCode(), employee.hashCode());
    }

    @Test
    void HashCodeNoTest() {
        Employee employee = new Employee("Bill", "Developer", 9, 75000, 456);
        Employee employee1 = new Employee("Steve", "Sales", 9, 75000, 455);
        Employee employee2 = new Employee("Satya", "Marketing", 9, 75000, 456);
        assertNotEquals(employee.hashCode(), employee1.hashCode());
        assertNotEquals(employee1.hashCode(), employee2.hashCode());
    }
}