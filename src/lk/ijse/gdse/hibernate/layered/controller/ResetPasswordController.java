package lk.ijse.gdse.hibernate.layered.controller;

import com.mysql.cj.xdevapi.Schema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hibernate.layered.dto.AdminDTO;
import lk.ijse.gdse.hibernate.layered.service.AdminServiceImpl;
import lk.ijse.gdse.hibernate.layered.service.ServiceFactory;
import lk.ijse.gdse.hibernate.layered.util.Navigation;
import lk.ijse.gdse.hibernate.layered.util.Routes;
import lk.ijse.gdse.hibernate.layered.util.Validation;

import java.io.IOException;

public class ResetPasswordController {
    @FXML
    private Label lblCode;

    @FXML
    private Label lblUserName;

    @FXML
    private AnchorPane pane;

    @FXML
    private PasswordField txtCode;

    @FXML
    private TextField txtPasswordTxt;

    @FXML
    private TextField txtUserName;

    private AdminServiceImpl adminService= (AdminServiceImpl) ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.Admin);
    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.Login, pane);
    }
    Validation validation = Validation.getInstance();
    @FXML
    void btnLogIn(ActionEvent event) throws IOException {

        if(validation.namePattern(txtUserName.getText())){
            lblUserName.setText(null);
            lblCode.setText(null);
            if(txtCode.getText().equals("code")){

                boolean b= adminService.reserPw(txtUserName.getText(), txtPasswordTxt.getText());
                if(b){
                    Navigation.navigate(Routes.Rooms,pane);
                    return;
                }
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong");
                alert.show();
            }else {
                lblCode.setText("Invalid Code");
            }
        }else{
            lblUserName.setText("Invalid Username Type");
        }
    }

}
