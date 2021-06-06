/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design;
import activities.Data;
import activities.Employee;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScene {

      public static Employee thisEmployee;

      private static final TableView<Employee> namesList = new TableView<>();
      private static final TableColumn<Employee, String> fullName = new TableColumn<>();

      private static TextField nameField;
      private static TextField ageField;
      private static TextField genderField;
      private static TextField typeField;
      private static TextField stateField;

      private static Button add;
      private static Button delete;
      private static Button edit;
      private static Button save;
      private static Button calcSalary;
      private static Label salaryLabel;

      public static ArrayList<Employee> employeesData;

      public static Stage stage;

      public static Scene getMainScene() {
            ArrayList<Employee> employees = null;
            try {
                  employees = employeesData == null ? employeesData = Data.readData(Data.filePath) : employeesData;
            } catch ( IOException ex ) {
            }

            initTable(employees);
            VBox dataFields = initFields();
            activeActions();

            HBox root = new HBox(50, dataFields, namesList);
            root.setAlignment(Pos.CENTER);
            return new Scene(root, 700, 500);
      }

      private static void initTable(ArrayList<Employee> employees) {
            namesList.getColumns().clear();
            namesList.getColumns().add(fullName);

            fullName.setText("إسم الموظف");
            fullName.setPrefWidth(300);
            fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));

            namesList.getItems().clear();
            namesList.getItems().addAll(employees);
      }

      private static VBox initFields() {
            Label name = new Label("الإسم :  ");
            Label age = new Label("العمر :  ");
            Label gender = new Label("الجنس :  ");
            Label type = new Label("نوع الوظيفة :  ");
            Label state = new Label("الحالة الإجتماعية :  ");

            nameField = new TextField();
            ageField = new TextField();
            genderField = new TextField();
            typeField = new TextField();
            stateField = new TextField();

            HBox nameBox = new HBox(nameField, name);
            HBox ageBox = new HBox(ageField, age);
            HBox genderBox = new HBox(genderField, gender);
            HBox typeBox = new HBox(typeField, type);
            HBox stateBox = new HBox(stateField, state);
            VBox dataFields = new VBox(20, nameBox, ageBox, genderBox, typeBox, stateBox, initButtons());
            dataFields.setAlignment(Pos.CENTER);

            nameField.setEditable(false);
            ageField.setEditable(false);
            genderField.setEditable(false);
            typeField.setEditable(false);
            stateField.setEditable(false);
            return dataFields;
      }

      private static VBox initButtons() {
            add = new Button("إضافة موظف");
            delete = new Button("حذف موظف");
            edit = new Button("تحرير");
            save = new Button("تصدير");
            calcSalary = new Button("حساب الرواتب");
            salaryLabel = new Label();

            delete.setDisable(true);
            edit.setDisable(true);
            calcSalary.setDisable(true);
            save.setPrefSize(80, 40);

            HBox buttons = new HBox(20, add, delete, edit);
            HBox salaryBox = new HBox(20, calcSalary, salaryLabel);

            VBox box = new VBox(20, buttons, save, salaryBox);
            box.setAlignment(Pos.CENTER);
            return box;
      }

      private static void activeActions() {
            namesList.setOnMousePressed((e) -> {
                  thisEmployee = namesList.getSelectionModel().getSelectedItem();
                  Employee employee = namesList.getSelectionModel().getSelectedItem();
                  nameField.setText(employee.getFullName());
                  ageField.setText(employee.getAge() + "");
                  genderField.setText(employee.getGender().toString());
                  typeField.setText(employee.getType().toString());
                  stateField.setText(employee.getState().toString());
                  delete.setDisable(false);
                  edit.setDisable(false);
                  calcSalary.setDisable(false);
            });
            add.setOnAction(e -> MainStage.setScene("addScene"));
            delete.setOnAction(e -> {
                  employeesData.remove(thisEmployee);
                  namesList.getItems().clear();
                  namesList.getItems().addAll(employeesData);
                  MainStage.needToSave = true;
                  clearFields();
            });
            edit.setOnAction(e -> MainStage.setScene("editScene"));
            save.setOnAction(e -> {
                  try {
                        Data.writeData(Data.filePath, employeesData);
                        MainStage.needToSave = false;
                  } catch ( IOException ex ) {
                  }
            });
            calcSalary.setOnAction((e) -> {
                  salaryLabel.setText(thisEmployee.getSalary() + "");
            });
      }

      private static void clearFields() {
            nameField.clear();
            ageField.clear();
            genderField.clear();
            stateField.clear();
            typeField.clear();
            salaryLabel.setText("");

            delete.setDisable(true);
            edit.setDisable(true);
      }

}
