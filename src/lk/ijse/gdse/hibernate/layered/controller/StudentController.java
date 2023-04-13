package lk.ijse.gdse.hibernate.layered.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hibernate.layered.dto.StudentDTO;
import lk.ijse.gdse.hibernate.layered.service.AdminServiceImpl;
import lk.ijse.gdse.hibernate.layered.service.ServiceFactory;
import lk.ijse.gdse.hibernate.layered.service.StudentServiceImpl;
import lk.ijse.gdse.hibernate.layered.util.Navigation;
import lk.ijse.gdse.hibernate.layered.util.Routes;
import lk.ijse.gdse.hibernate.layered.util.Validation;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController implements Initializable {


    public TextField txtName;
    public TextField txtContact;
    public TextField txtAddress;
    public RadioButton rbmEmail;
    public ToggleGroup gender;
    public RadioButton rbnMale;

    public TableView tblStudent;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn clmAddress;
    public TableColumn clmRejDate;
    public TableColumn clmGender;
    public TableColumn clmContact;
    public ComboBox cmbName;
    public ComboBox cmdStudentId2;
    public AnchorPane pane;
    public Label lblName;
    public Label lblAddress;
    public Label lblContact;
    public Label lblGender;

    String name;
    String contact;
    String address;
    String mail_femail="Femail";
    private StudentServiceImpl studentService= (StudentServiceImpl) ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.Student);
    Validation validation = Validation.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        clmId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("contact"));
        clmGender.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("gender"));
        clmRejDate.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("time"));



        List<StudentDTO> students = studentService.allStudent();

        ObservableList observableList= FXCollections.observableArrayList();
        observableList.addAll(students);
        tblStudent.setItems(observableList);



        List<String> strings = studentService.stdNames();

        ObservableList<String> nameList = FXCollections.observableArrayList();
        nameList.addAll(strings);
        cmbName.setItems(nameList);

        List<String> ids = studentService.stdId();
       ObservableList<String>  idList= FXCollections.observableArrayList();
       idList.addAll(ids);
       cmdStudentId2.setItems(idList);


    }

    public void saveOnAction(ActionEvent actionEvent) {
        lblName.setText(null);
        lblGender.setText(null);
        lblContact.setText(null);
        if(validation.namePattern(txtName.getText())){
            if(validation.contactPattern(txtContact.getText())){
                if(rbnMale.isSelected() || rbmEmail.isSelected()){
                    name=txtName.getText();
                    contact=txtContact.getText();
                    address=txtAddress.getText();
                    if(rbnMale.isSelected()){
                        mail_femail="Male";
                    }else {
                        mail_femail="Femail";
                    }
                    StudentDTO studentDTO=new StudentDTO();
                    studentDTO.setName(name);
                    studentDTO.setContact(contact);
                    studentDTO.setAddress(address);
                    studentDTO.setGender(mail_femail);
                    StudentServiceImpl studentService = StudentServiceImpl.getInstance();
                    long l = studentService.saveCustomer(studentDTO);
                    if(l>0){
                        cmdStudentId2.setValue(null);
                        txtName.setText(null);
                        txtContact.setText(null);
                        txtAddress.setText(null);
                        rbmEmail.setSelected(false);
                        rbnMale.setSelected(false);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Student Sucefully Added");
                        alert.show();

                        lblName.setText(null);
                        lblGender.setText(null);
                        lblContact.setText(null);
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Something went Wrong");
                        alert.show();

                        lblName.setText(null);
                        lblGender.setText(null);
                        lblContact.setText(null);
                    }


                    clmId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("id"));
                    clmName.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("name"));
                    clmAddress.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("address"));
                    clmContact.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("contact"));
                    clmGender.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("gender"));
                    clmRejDate.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("time"));



                    List<StudentDTO> students = studentService.allStudent();

                    ObservableList observableList= FXCollections.observableArrayList();
                    observableList.addAll(students);
                    tblStudent.setItems(observableList);



                    List<String> strings = studentService.stdNames();

                    ObservableList<String> nameList = FXCollections.observableArrayList();
                    nameList.addAll(strings);
                    cmbName.setItems(nameList);

                    List<String> ids = studentService.stdId();
                    ObservableList<String>  idList= FXCollections.observableArrayList();
                    idList.addAll(ids);
                    cmdStudentId2.setItems(idList);
                }else {
                    lblGender.setText("Plese Select Gender");

                }
            }else{
                lblContact.setText("Invail Contact Number");
            }


        }else{
            lblName.setText("Invaid Name");
        }




    }


    public void nameOnAction(ActionEvent actionEvent) {
//        StudentDTO studentDTO = instance.searchByName((String) cmbName.getValue());
//        System.out.println(studentDTO);

    }
    StudentDTO studentDTO;
    public void idOnAction(ActionEvent actionEvent) {
        long l;
        try {
           l =Long.parseLong((String) cmdStudentId2.getValue());
            studentDTO= studentService.getStudentDTO(l);
        }catch (NumberFormatException e){

        }

        rbmEmail.setSelected(true);
        txtName.setText(studentDTO.getName());
        txtAddress.setText(studentDTO.getAddress());
        txtContact.setText(studentDTO.getContact());

        if(studentDTO.getGender().equals("Male")){
            rbnMale.setSelected(true);
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {

            boolean b = studentService.deleteStudent(studentDTO);
        if(b){
            cmdStudentId2.setValue(null);
            txtName.setText(null);
            txtContact.setText(null);
            txtAddress.setText(null);
            rbmEmail.setSelected(false);
            rbnMale.setSelected(false);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Student Sucefully Deleted");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went Wrong");
            alert.show();
        }



        clmId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("contact"));
        clmGender.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("gender"));
        clmRejDate.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("time"));



        List<StudentDTO> students = studentService.allStudent();

        ObservableList observableList= FXCollections.observableArrayList();
        observableList.addAll(students);
        tblStudent.setItems(observableList);



        List<String> strings = studentService.stdNames();

        ObservableList<String> nameList = FXCollections.observableArrayList();
        nameList.addAll(strings);
        cmbName.setItems(nameList);

        List<String> ids = studentService.stdId();
        ObservableList<String>  idList= FXCollections.observableArrayList();
        idList.addAll(ids);
        cmdStudentId2.setItems(idList);

    }

    public void updateOnActiion(ActionEvent actionEvent) {
        lblName.setText(null);
        lblGender.setText(null);
        lblContact.setText(null);
        if(validation.namePattern(txtName.getText())){
            if(validation.contactPattern(txtContact.getText())){
                if(rbnMale.isSelected() || rbmEmail.isSelected()){
                    name=txtName.getText();
                    contact=txtContact.getText();
                    address=txtAddress.getText();
                    if(rbnMale.isSelected()){
                        mail_femail="Male";
                    }else {

                        mail_femail="Femail";
                    }
                    Timestamp time = studentDTO.getTime();
                    studentDTO.setName(txtName.getText());
                    studentDTO.setAddress(txtAddress.getText());
                    studentDTO.setContact(txtContact.getText());
                    studentDTO.setGender(mail_femail);
                    studentDTO.setTime(time);
                    System.out.println(time);


                    boolean b = studentService.updateStudent(studentDTO);
                    if(b){
                        cmdStudentId2.setValue(null);
                        txtName.setText(null);
                        txtContact.setText(null);
                        txtAddress.setText(null);
                        rbmEmail.setSelected(false);
                        rbnMale.setSelected(false);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Student Sucefully Updated");
                        alert.show();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Something went Wrong");
                        alert.show();
                    }


                    clmId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("id"));
                    clmName.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("name"));
                    clmAddress.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("address"));
                    clmContact.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("contact"));
                    clmGender.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("gender"));
                    clmRejDate.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("time"));



                    List<StudentDTO> students = studentService.allStudent();

                    ObservableList observableList= FXCollections.observableArrayList();
                    observableList.addAll(students);
                    tblStudent.setItems(observableList);



                    List<String> strings = studentService.stdNames();

                    ObservableList<String> nameList = FXCollections.observableArrayList();
                    nameList.addAll(strings);
                    cmbName.setItems(nameList);

                    List<String> ids = studentService.stdId();
                    ObservableList<String>  idList= FXCollections.observableArrayList();
                    idList.addAll(ids);
                    cmdStudentId2.setItems(idList);
                }else {
                    lblGender.setText("Plese Select Gender");

                }
            }else{
                lblContact.setText("Invail Contact Number");
            }


        }else{
            lblName.setText("Invaid Name");
        }
    }


    public void roomFOrnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Rooms, pane);
    }

    public void studentFormOnAction(ActionEvent actionEvent) {
    }

    public void reservationOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Reservation, pane);
    }
}
