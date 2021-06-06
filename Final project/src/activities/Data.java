package activities;
import activities.Employee.Gender;
import activities.Employee.State;
import activities.Employee.Type;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Data {

      public static final String filePath = "C:\\Users\\EMAM\\Desktop\\employees.txt";

      public static ArrayList<Employee> readData(String filePath) throws IOException {
            ArrayList<Employee> employees = new ArrayList<>();

            BufferedReader file = new BufferedReader(new FileReader(new File(filePath)));
            String s;
            while ( (s = file.readLine()) != null ) {
                  String[] data = s.split(",");
                  String fName = data[0];
                  String lName = data[1];
                  int age = Integer.parseInt(data[2]);
                  Gender gender
                          = data[3].equalsIgnoreCase("m") || data[3].equalsIgnoreCase("male")
                          ? Gender.MALE : Gender.FEMALE;
                  State state
                          = data[4].equalsIgnoreCase("single") ? State.SINGLE
                          : data[4].equalsIgnoreCase("married") ? State.MARRIED
                          : data[4].equalsIgnoreCase("devorced") ? State.DEVORCED
                          : State.WIDOWER;
                  Type type
                          = data[5].toUpperCase().contains("FULL")
                          ? Type.FULL_TIME : Type.PART_TIME;
                  Employee newEmployee = new Employee(fName, lName, age, gender, state, type);
                  employees.add(newEmployee);
            }
            return employees;
      }

      public static void writeData(String _filePath, ArrayList<Employee> _employees) throws IOException {
            for ( Employee employee: _employees ) {
                  System.out.println(employee);
            }
            BufferedWriter file = new BufferedWriter(new FileWriter(new File(filePath)));
            for ( Employee employee: _employees ) {
                  file.write(employee.toString());
                  file.newLine();
                  file.close();
            }
      }

}
