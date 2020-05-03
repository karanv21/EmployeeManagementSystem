package ui;

import model.Employee;
import model.Location;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CompanyGuiFinal {

    private static Location location = new Location("Downtown", 21000);
    private static final JFrame frame = new JFrame();
    private static final JTable table = new JTable();
    private static JLabel label;
    private static final DefaultTableModel model = new DefaultTableModel();
    private static final Object[] columns = new Object[]{"Name", "Position",
            "Years Worked",
            "Salary",
            "Employee ID",
    };
    private static final Font font = new Font("", 1, 22);
    private static final JTextField textName = new JTextField();
    private static final JTextField textPosition = new JTextField();
    private static final JTextField textYearsWorked = new JTextField();
    private static final JTextField textSalary = new JTextField();
    private static final JTextField textEmployeeID = new JTextField();
    private static final JTextField textLocation = new JTextField();
    private static final JButton btnLocation = new JButton("Change");
    private static final JButton btnAdd = new JButton("Hire");
    private static final JButton btnDelete = new JButton("Fire");
    private static final JButton btnUpdate = new JButton("Update");
    private static final JTextField jtfFilter = new JTextField();

    private static final Object[] row = new Object[5];

    private static void setrowcol() {
        row[0] = textName.getText();
        row[1] = textPosition.getText();
        row[2] = textYearsWorked.getText();
        row[3] = textSalary.getText();
        row[4] = textEmployeeID.getText();
    }

    private static void createButtons() {
        textLocation.setText("Type location name here!");
        textName.setText("E.g. Bob");
        textPosition.setText("E.g. Sales");
        textYearsWorked.setText("E.g. 3");
        textSalary.setText("E.g. 80000");
        textEmployeeID.setText("E.g. 10001");
        label = new JLabel("Downtown");

        textLocation.setBounds(480, 5, 180, 25);
        textName.setBounds(65, 250, 120, 25);
        textPosition.setBounds(205, 250, 120, 25);
        textYearsWorked.setBounds(355, 250, 120, 25);
        textSalary.setBounds(520, 250, 120, 25);
        textEmployeeID.setBounds(685, 250, 120, 25);
        label.setBounds(200, 5, 100, 25);

        btnLocation.setBounds(320, 5, 150, 25);
        btnAdd.setBounds(205, 290, 100, 25);
        btnDelete.setBounds(345, 290, 100, 25);
        btnUpdate.setBounds(510, 290, 100, 25);
    }

    private static void setTable() {
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        table.setFont(font);
        table.setRowHeight(30);
    }

    private static void framestuff() {
        frame.setLayout(null);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 45, 860, 200);
        frame.add(pane);

        // add JTextFields to the jframe
        frame.add(textName);
        frame.add(textPosition);
        frame.add(textYearsWorked);
        frame.add(textSalary);
        frame.add(textEmployeeID);
        frame.add(textLocation);

        // add label to jframe
        frame.add(label);
        frame.pack();

        // add JButtons to the jframe
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);
        frame.add(btnLocation);

        frame.setSize(900, 470);
        frame.setLocationRelativeTo(null);
    }

    private static void rowSort() {
        TableRowSorter<TableModel> rowSorter
                = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        jtfFilter.setBounds(430, 400, 80, 25);
        JLabel labelFilter = new JLabel("Filter");
        labelFilter.setBounds(380, 400, 60, 25);
        table.setRowSorter(rowSorter);

        String text = jtfFilter.getText();
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }

        frame.add(jtfFilter);
        frame.add(labelFilter);
        getaUpdatebtn(rowSorter, jtfFilter);
    }

    //Modifies: all objects created in this class
    //Effects: Creates the gui and organizes employee and location information into a table. Records user input
    public static void main(String[] args) {
        // create JFrame and JTable
        // create a table model and set a Column Identifiers to this model
        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);

        // Change A JTable Background Color, Font Size, Font Color, Row Height
        setTable();

        //Make filter for table
        rowSort();

        // ------------------------------------------------------------------------------------------

        // create JButtons
        createButtons();

        // create JScrollPane
        framestuff();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // button add row
        getaAddbtn();

        // button remove row
        addaDeletebtn();

        //button change location
        getaLocationBtn();

        // get selected row data From table to textfields
        getaTable();

        // button update row
        getUpdatebtn();

    }

    private static void getUpdatebtn() {
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if (i >= 0) {
                    model.setValueAt(textName.getText(), i, 0);
                    model.setValueAt(textPosition.getText(), i, 1);
                    model.setValueAt(textYearsWorked.getText(), i, 2);
                    model.setValueAt(textSalary.getText(), i, 3);
                    model.setValueAt(textEmployeeID.getText(), i, 4);
                } else {
                    System.out.println("Update Error");
                }
            }
        });
    }

    private static void getaTable() {
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                textName.setText(model.getValueAt(i, 0).toString());
                textPosition.setText(model.getValueAt(i, 1).toString());
                textYearsWorked.setText(model.getValueAt(i, 2).toString());
                textSalary.setText(model.getValueAt(i, 3).toString());
                textEmployeeID.setText(model.getValueAt(i, 4).toString());
            }
        });
    }

    private static void getaLocationBtn() {
        btnLocation.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(textLocation.getText());
            }
        });
    }

    //Fires the employee and deletes the row
    private static void addaDeletebtn() {
        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();
                if (i >= 0) {
                    // remove a row from jtable
                    model.removeRow(i);
                } else {
                    System.out.println("Delete Error");
                }
                Employee employee = new Employee(textName.getText(),
                        textPosition.getText(),
                        Integer.parseInt(textYearsWorked.getText()),
                        Integer.parseInt(textSalary.getText()),
                        Integer.parseInt(textEmployeeID.getText()));
                location.fireEmployee(employee);
            }
        });
    }

    //Adds employee details to respective column in row
    private static void getaAddbtn() {
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setrowcol();
                try {
                    Employee employee = new Employee(textName.getText(),
                            textPosition.getText(),
                            Integer.parseInt(textYearsWorked.getText()),
                            Integer.parseInt(textSalary.getText()),
                            Integer.parseInt(textEmployeeID.getText()));
                    // add row to the model
                    model.addRow(row);
                    location.hireEmployee(employee);
                } catch (NumberFormatException nfe) {
                    System.out.println("Enter an integer!");
                }
            }
        });
    }

    //Helper method for the below method and it checks if text is present in the rows so it can update it
    private static void ifelsemethod(TableRowSorter<TableModel> rowSorter, JTextField jtfFiltered) {
        String text = jtfFiltered.getText();
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

    private static void getaUpdatebtn(TableRowSorter<TableModel> rowSorter, JTextField jtfFiltered) {
        jtfFiltered.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                ifelsemethod(rowSorter, jtfFiltered);
            }

            public void removeUpdate(DocumentEvent e) {
                ifelsemethod(rowSorter, jtfFiltered);
            }

            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
                //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}