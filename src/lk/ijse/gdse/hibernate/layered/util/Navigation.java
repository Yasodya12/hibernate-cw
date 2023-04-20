package lk.ijse.gdse.hibernate.layered.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
private static AnchorPane pane;
    public static Stage window;
    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {

            case  Reservation:
                window.setTitle("D-24 Hostal");
                initUI("ReserveRoom.fxml");
                window.setFullScreen(true);
                break;
            case Rooms:
                window.setTitle("D-24 Hostal");
                window.setFullScreen(true);
                initUI("Room.fxml");
                break;
            case SignUp:
                window.setTitle("D-24 Hostal");
                window.setFullScreen(false);
                initUI("SignUpForm.fxml");
                break;
            case Login:
                window.setTitle("D-24 Hostal");
                window.setFullScreen(false);
                initUI("LogInForn.fxml");
                break;
            case Student:
                window.setTitle("D-24 Hostal");
                window.setFullScreen(true);
                initUI("Student.fxml");
                break;
            case  NewPassword:
                window.setTitle("D-24 Hostal");
                window.setFullScreen(false);
                initUI("ResetPassword.fxml");
                break;

            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/gdse/hibernate/layered/view/" + location)));
    }
}
