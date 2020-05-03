package model;

import observer.Subject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class Location extends Subject implements Loadable, Saveable {
    private String name;
    private int numberOfCustomers;
    private Set<Employee> employees;
    private Manager manager;

    //Constructor
    public Location(String name, int numberOfCustomersPerMonth) {
        this.name = name;
        this.numberOfCustomers = numberOfCustomersPerMonth;
        employees = new HashSet<>();
        manager = null;
    }

    //Getter
    public String getName() {
        return name;
    }

    //Getter
    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    //Getter
    public Manager getManager() {
        return manager;
    }

    //Setter
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    //Effects: if this (location) has an assigned manager, return true
    //                                                     else return false
    public boolean hasManager() {
        if (getManager() != null) {
            return true;
        }
        return false;
    }

    //Getter
    public Set<Employee> getEmployees() {
        return employees;
    }

    //Effects: if location doesn't have employees, return true
    //                                             else return false
    public boolean isEmpty() {
        if (employees.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    //Modifies: employees, employee, (this)
    //Effects: if location doesn't have this employee in its employee list, add this employee
    //         assign the employee to this location and add an observer for this employee
    public void hireEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
            employee.assignToLocation(this);
            addObserver(employee);
        }
        notifyObserver(this);
    }

    //Modifies: employees, employee, (this)
    //Effects: if this employee is in the location's employee list, fire the employee
    //         remove the employee from this assigned location
    public void fireEmployee(Employee employee) {
        if (employees.contains(employee)) {
            employees.remove(employee);
            employee.removeFromLocation();
        }
    }

//    public void save() throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get("save.txt"));
//        //FileInputStream fileStream = new FileInputStream("save.txt");
//        PrintWriter writer = new PrintWriter("outputfile.txt", "UTF-8");
//        lines.add(this.getName());
//        for (String line : lines) {
//            writer.println(line);
//        }
//        writer.close();
//    }
//
//    public static ArrayList<String> splitOnSpace(String line) {
//        String[] splits = line.split(" ");
//        return new ArrayList<>(Arrays.asList(splits));
//    }
//
//    public void save() throws IOException {
//        PrintWriter writer = new PrintWriter("save.txt", "UTF-8");
//        for (Employee employee : employees) {
//            writer.println(employee.getName() + " " + employee.getPosition() + " " + employee.getyearsWorked() + " "
//                    + employee.getEmployeeID());
//        }
//        writer.close();
//    }

    //Modifies: the file that it writes on
    //Effects: loads the file that the system writes on
    @Override
    public void load() {

    }

//    public Set<Employee> load() throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
//        for (String line : lines) {
//            ArrayList<String> partsOfLine = splitOnSpace(line);
//            for (Employee re : employees) {
//                re.setName(partsOfLine.get(0));
//                re.setPosition(partsOfLine.get(1));
//                re.setYearsWorked(partsOfLine.get(2));
//            }
//        }
//        return employees;
//    }
}
