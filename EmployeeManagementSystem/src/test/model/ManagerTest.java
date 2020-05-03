package model;

import model.exceptions.ManagerSalaryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ManagerTest {
    private String name = "Manager Rio";
    private String branch = "Downtown";
    private int yearsWorked = 0;
    private int salary = 0;
    private int employeeID;
    private Manager manager;
    private int minManagerSalary = 130000;

    @BeforeEach
    public void ManagerSystemModelSetup() {
        manager = new Manager(name, branch, yearsWorked, salary, employeeID);
    }

    @Test
    public void getNameTest() {
        assertEquals(manager.getName(), "Manager Rio");
    }

    @Test
    void getEmployeeID() {
        assertEquals(manager.getEmployeeID(), employeeID);
    }

    @Test
    public void getPositionTest() {
        assertEquals(manager.getBranchLocation(), "Downtown");
    }

    @Test
    public void setSalaryExceptionFailTest() {
        try {
            manager.setSalary(12100);
            fail("Manager setSalary did not throw exception");
        } catch (ManagerSalaryException me) {
            me.printStackTrace();
            System.out.println("Sorry minimum salary has to be greater than: " + minManagerSalary);
        }
    }

    @Test
    public void setSalaryExceptionPassTest() {
        try {
            manager.setSalary(131000);
        } catch (ManagerSalaryException e) {
            fail("Manager setSalary did not throw exception");
            e.printStackTrace();
            System.out.println("Sorry minimum salary has to be greater than: " + minManagerSalary);
        }
        assertEquals(manager.getSalary(), 131000);
    }

    @Test
    public void giveRaiseTrueTest() {
        manager.giveRaise(1000);
        assertEquals(manager.getSalary(), 1000);
    }

    @Test
    public void giveRaiseNegativeTest() {
        manager.giveRaise(-1);
        assertEquals(manager.getSalary(), 0);
    }

    @Test
    public void giveRaiseZeroAmountTest() {
        assertEquals(manager.giveRaise( 0), manager.getSalary());
    }

    @Test
    public void triYearlyRaiseFailTest() {
        manager.increaseYearsWorked();
        assertEquals(manager.getYearsWorked(), 1);
        assertEquals(manager.giveRaise(), 0);
        assertEquals(manager.getYearsWorked(), 2);
    }

    @Test
    public void triYearlyRaiseSuccessTest() {
        for(int i=0; i<6; i++) {
            manager.increaseYearsWorked();
        }
        assertEquals(manager.giveRaise(), 3000);
    }
}
