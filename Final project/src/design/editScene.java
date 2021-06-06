/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design;
import activities.Employee;
import activities.Employee.Gender;
import activities.Employee.State;
import activities.Employee.Type;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

class editScene {

      private static Button save;
      private static Button cancel;

      private static TextField fNameField;
      private static TextField lNameField;
      private static TextField ageField;
      private static TextField typeField;
      private static RadioButton male;
      private static RadioButton female;
      private static RadioButton part;
      private static RadioButton full;

      private static ComboBox<State> stateField;

      private static Employee employee;

      public static Scene getEditScene(Employee _employee) {
            employee = _employee;

            VBox dataFields = initFields();
            HBox buttonsList = initButtons();
            activeActions();

            VBox root = new VBox(50, dataFields, buttonsList);
            root.setAlignment(Pos.CENTER);
            return new Scene(root, 400, 400);
      }

      private static void activeActions() {
            save.setOnAction((e) -> {
                  MainScene.employeesData.remove(employee);
                  Employee newEmployee = new Employee(
                          fNameField.getText(),
                          lNameField.getText(),
                          Integer.parseInt(ageField.getText()),
                          male.isSelected() ? Gender.MALE : Gender.FEMALE,
                          stateField.getSelectionModel().getSelectedItem(),
                          part.isSelected() ? Type.PART_TIME : Type.FULL_TIME);
                  MainScene.employeesData.add(newEmployee);
                  MainStage.needToSave = true;
                  back();
            });
            cancel.setOnAction((e) -> back());
      }

      private static void back() {
            MainStage.setScene("MainScene");
      }

      private static HBox initButtons() {
            save = new Button("حفظ");
            cancel = new Button("رجوع");
            HBox box = new HBox(50, save, cancel);
            box.setAlignment(Pos.CENTER);
            return box;
      }

      private static VBox initFields() {
            Label fName = new Label("الإسم الأول :  ");
            Label lName = new Label("الإسم الأخير :  ");
            Label age = new Label("العمر :  ");
            Label state = new Label("الحالة الإجتماعية :  ");

            fNameField = new TextField(employee.getFName());
            lNameField = new TextField(employee.getLName());
            ageField = new TextField(employee.getAge() + "");

            ToggleGroup gender = new ToggleGroup();
            male = new RadioButton("ذكر");
            female = new RadioButton("أنثى");
            male.setToggleGroup(gender);
            female.setToggleGroup(gender);
            if ( employee.getGender() == Gender.MALE ) {
                  male.setSelected(true);
            } else {
                  female.setSelected(true);
            }

            ToggleGroup type = new ToggleGroup();
            part = new RadioButton("دوام جزئي");
            full = new RadioButton("دوام كلي");
            part.setSelected(true);
            part.setToggleGroup(type);
            full.setToggleGroup(type);
            if ( employee.getType() == Type.FULL_TIME ) {
                  full.setSelected(true);
            } else {
                  part.setSelected(true);
            }

            ArrayList<State> states = new ArrayList<>();
            states.add(State.SINGLE);
            states.add(State.MARRIED);
            states.add(State.DEVORCED);
            states.add(State.WIDOWER);
            stateField = new ComboBox<>(FXCollections.observableList(states));
            stateField.setValue(employee.getState());

            HBox fNameBox = new HBox(fNameField, fName);
            HBox lNameBox = new HBox(lNameField, lName);
            HBox ageBox = new HBox(ageField, age);
            HBox genderBox = new HBox(10, male, female);
            HBox typeBox = new HBox(10, part, full);
            HBox stateBox = new HBox(stateField, state);

            fNameBox.setAlignment(Pos.CENTER_LEFT);
            lNameBox.setAlignment(Pos.CENTER_LEFT);
            ageBox.setAlignment(Pos.CENTER_LEFT);
            genderBox.setAlignment(Pos.CENTER_LEFT);
            typeBox.setAlignment(Pos.CENTER_LEFT);
            stateBox.setAlignment(Pos.CENTER_LEFT);

            VBox dataFields = new VBox(20, fNameBox, lNameBox, ageBox, genderBox, typeBox, stateBox);
            dataFields.setAlignment(Pos.CENTER);
            dataFields.setTranslateX(50);
            return dataFields;
      }

}
