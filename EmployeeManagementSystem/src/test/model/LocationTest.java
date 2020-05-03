package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    protected String name;
    protected int numberOfCustomersPerMonth;
    private Employee employee = new Employee("Bob", "Sales", 3, 80000, 123);
    private Employee employee1 = new Employee("Gwen", "Marketing", 5, 90000, 121);
    private Set<Employee> employees;
    private Location location;
    private Manager manager = new Manager("Manager Joe", "Downtown", 8, 100000, 120);

    @BeforeEach
    public void setup() {
        name = "Downtown";
        numberOfCustomersPerMonth = 2000;
        employees = new HashSet<>();
        location = new Location(name, numberOfCustomersPerMonth);
    }

    @Test
    void getName() {
        assertEquals(location.getName(), "Downtown");
        location = new Location("Richmond", 1500);
        assertEquals(location.getName(), "Richmond");
    }

    @Test
    void getNumberOfCustomersPerMonth() {
        assertEquals(location.getNumberOfCustomers(), 2000);
        location = new Location("Richmond", 1500);
        assertEquals(location.getNumberOfCustomers(), 1500);
    }

    @Test
    void getManager() {
        location.setManager(manager);
        assertEquals(location.getManager(), manager);
    }

    @Test
    void getManagerEmptyTest() {
        assertNull(location.getManager());
    }

    @Test
    void hasManagerPassTest() {
        location.setManager(manager);
        assertTrue(location.hasManager());
    }

    @Test
    void hasManagerFailTest() {
        assertFalse(location.hasManager());
    }

    @Test
    void isEmptyFalseTest() {
        location.hireEmployee(employee);
        assertFalse(location.isEmpty());
    }

    @Test
    void isEmptyTrueTest() {
        assertTrue(location.isEmpty());
    }

    @Test
    void hireEmployeeEmptyPassTest() {
        assertEquals(location.getEmployees().size(), 0);
    }

    @Test
    void hireEmployeePassSingleLocationTest() {
        location.hireEmployee(employee);
        assertTrue(location.getEmployees().contains(employee));
        location.hireEmployee(employee1);
        assertTrue(location.getEmployees().contains(employee1));
    }

    @Test
    void hireEmployeePassMultipleBusesTest() {
        location.hireEmployee(employee);

        Location location1 = new Location("Burnaby", 1100);

        location1.hireEmployee(employee);
        assertTrue(location1.getEmployees().contains(employee));
        assertEquals(location.getEmployees().size(), 0);
    }

    @Test
    void fireEmployeeTest() {
        location.hireEmployee(employee);
        location.hireEmployee(employee1);
        assertEquals(location.getEmployees().size(), 2);
        location.fireEmployee(employee);
        assertEquals(location.getEmployees().size(), 1);
        location.fireEmployee(employee1);
        assertEquals(location.getEmployees().size(), 0);
//        students = bus.getStudents();
//        assertEquals(bus.getStudents(), students);
//        students.remove(student1);
//        assertEquals(bus.getStudents(), students);
    }
}
