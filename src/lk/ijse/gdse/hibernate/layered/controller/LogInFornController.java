package lk.ijse.gdse.hibernate.layered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hibernate.layered.repository.RepositryFactory;
import lk.ijse.gdse.hibernate.layered.service.AdminServiceImpl;
import lk.ijse.gdse.hibernate.layered.service.ServiceFactory;
import lk.ijse.gdse.hibernate.layered.util.Navigation;
import lk.ijse.gdse.hibernate.layered.util.Routes;
import lk.ijse.gdse.hibernate.layered.util.Validation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInFornController implements Initializable {

    public CheckBox checkBOX;
    public TextField txtUserName;

    public AnchorPane pane;
    public Label lblUserName;
    public Label lblPassword;


    private AdminServiceImpl adminService= (AdminServiceImpl) ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.Admin);
    @FXML
    private ToggleButton tgglShowPassword;

    @FXML
    private PasswordField txtPasswordPw;

    @FXML
    private TextField txtPasswordTxt;

    Validation validation = Validation.getInstance();
    @FXML
    void btnLogIn(ActionEvent event) throws IOException {
       if(validation.namePattern(txtUserName.getText())){
           boolean check;
           lblUserName.setText(null);
           lblPassword.setText(null);
           String username= txtUserName.getText();
           String password;
           if(checkBOX.isSelected()){

               password= txtPasswordTxt.getText();
           }else {
               password=txtPasswordPw.getText();
           }

           check = adminService.check(txtUserName.getText(), password);

           if(check){
               Navigation.navigate(Routes.Rooms, pane);
               return;
           }
           lblUserName.setText("Invail Username or Password");
           lblPassword.setText("Invail Username or Password");
       }else{
           lblUserName.setText("Invalid UserName Type");
       }

    }

    public void pwTypeOnAction(KeyEvent keyEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtPasswordTxt.setVisible(false);
    }

    public void checkBxShowPw(ActionEvent actionEvent) {
        if(checkBOX.isSelected()){
            System.out.println(txtPasswordTxt.getText()+"Select una");
            txtPasswordTxt.setText(txtPasswordPw.getText());
            txtPasswordPw.setVisible(false);
            txtPasswordTxt.setVisible(true);
            return;
        }
        txtPasswordPw.setText(txtPasswordTxt.getText());
        txtPasswordPw.setVisible(true);
        txtPasswordTxt.setVisible(false);
    }

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {

        Navigation.navigate(Routes.SignUp, pane);

    }

    public void reserPwOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.NewPassword,pane);
    }
}
