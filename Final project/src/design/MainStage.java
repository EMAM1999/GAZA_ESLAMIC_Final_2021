/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design;
import activities.Data;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainStage extends Application {

      @Override
      public void stop() throws Exception {
            if ( MainStage.needToSave ) {
                  Alert a = new Alert(AlertType.CONFIRMATION, "هل تريد حفظ التغييرات ؟");
                  a.setHeaderText("");
                  a.setTitle("حفظ");
                  a.showAndWait();
                  switch ( a.getResult().getText() ) {
                        case "OK":
                              Data.writeData(Data.filePath, MainScene.employeesData);
                  }
            }
            Platform.exit();
      }

      public static Stage stage;

      @Override
      public void start(Stage s) throws Exception {
            stage = s;
            Scene scene = MainScene.getMainScene();
            s.setScene(scene);
            s.setResizable(false);
            s.setTitle("إدارة بيانات الموظفين");
            s.show();
      }
      public static boolean needToSave = false;

      public static void setScene(String sceneName) {
            switch ( sceneName.toLowerCase() ) {
                  case "mainscene":
                        stage.setTitle("إدارة بيانات الموظفين");
                        stage.setScene(MainScene.getMainScene());
                        break;
                  case "addscene":
                        stage.setTitle("إضافة موظف");
                        stage.setScene(addScene.getAddScene());
                        break;
                  case "editscene":
                        stage.setTitle("تحرير");
                        stage.setScene(editScene.getEditScene(MainScene.thisEmployee));
                        break;
                  default:
                        throw new AssertionError();
            }
      }

      public static void main(String[] args) {
            launch(args);
      }
}
