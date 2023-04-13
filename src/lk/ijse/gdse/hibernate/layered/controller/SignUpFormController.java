package lk.ijse.gdse.hibernate.layered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hibernate.layered.dto.AdminDTO;
import lk.ijse.gdse.hibernate.layered.repository.RepositryFactory;
import lk.ijse.gdse.hibernate.layered.service.AdminServiceImpl;
import lk.ijse.gdse.hibernate.layered.service.ServiceFactory;
import lk.ijse.gdse.hibernate.layered.util.Navigation;
import lk.ijse.gdse.hibernate.layered.util.Routes;
import lk.ijse.gdse.hibernate.layered.util.Validation;

import java.io.IOException;

public class SignUpFormController {

    public TextField txtUserName;
    public AnchorPane
            pane;
    public Label lblUserName;
    public Label lblCode;
    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtPasswordTxt;



    private AdminServiceImpl adminService= (AdminServiceImpl) ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.Admin);
    Validation validation = Validation.getInstance();
    @FXML
    void btnLogIn(ActionEvent event) throws IOException {

       if(validation.namePattern(txtUserName.getText())){
           lblUserName.setText(null);
           lblCode.setText(null);
           if(txtCode.getText().equals("code")){
               AdminDTO adminDTO = new AdminDTO();
               adminDTO.setUserName(txtUserName.getText());
               adminDTO.setPassword(txtPasswordTxt.getText());
               long l = adminService.saveAdmin(adminDTO);
               if(l>0){
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

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Login, pane);
    }
}
