package lk.ijse.gdse.hibernate.layered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hibernate.layered.dto.RoomDTO;
import lk.ijse.gdse.hibernate.layered.dto.StudentDTO;
import lk.ijse.gdse.hibernate.layered.service.ReservationServiceImpl;
import lk.ijse.gdse.hibernate.layered.service.RoomServiceImpl;
import lk.ijse.gdse.hibernate.layered.service.ServiceFactory;
import lk.ijse.gdse.hibernate.layered.util.Navigation;
import lk.ijse.gdse.hibernate.layered.util.Routes;
import lk.ijse.gdse.hibernate.layered.util.Validation;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomController implements Initializable {

    public TableView tblRoom;
    public TableColumn clmRoomTypeId;
    public TableColumn clmRoomType;
    public TableColumn clmKeyMoney;
    public TableColumn clmQty;
    public ComboBox cmbRoomId;
    public AnchorPane pane;
    public Label lblKeyMoney;
    public Label lblQty;
    private RoomServiceImpl roomService= (RoomServiceImpl) ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.Rooms);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clmRoomTypeId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("id"));
        clmRoomType.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("type"));
        clmKeyMoney.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("keyMoney"));
        clmQty.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("qty"));
        List<RoomDTO> rooms = roomService.alloom();


        ObservableList observableList= FXCollections.observableArrayList();
        observableList.addAll(rooms);
        tblRoom.setItems(observableList);


        List<String> ids = roomService.roomId();
        ObservableList<String>  idList= FXCollections.observableArrayList();
        idList.addAll(ids);
        cmbRoomId.setItems(idList);

    }

    public TextField txtType;
    public TextField txtKeyMoney;
    public TextField txtQty;

//    RoomServiceImpl roomService = RoomServiceImpl.getInstance();
    Validation validation = Validation.getInstance();
    public void saveOnAction(ActionEvent actionEvent) {

        lblKeyMoney.setText(null);
        lblQty.setText(null);

        RoomDTO roomDTO = new RoomDTO();
        if(validation.numberPattern(txtKeyMoney.getText()) && validation.numberPattern(txtQty.getText())){
            roomDTO.setType(txtType.getText());
            roomDTO.setKeyMoney(txtKeyMoney.getText());
            roomDTO.setQty(Integer.parseInt(txtQty.getText()));
            long l = roomService.saveRoom(roomDTO);
            if(l>0){
                cmbRoomId.setValue(null);
                txtType.setText(null);
                txtQty.setText(null);
                txtKeyMoney.setText(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Room Sucefully Added");
                alert.show();

            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went Wrong");
                alert.show();
            }

            clmRoomTypeId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("id"));
            clmRoomType.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("type"));
            clmKeyMoney.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("keyMoney"));
            clmQty.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("qty"));
            List<RoomDTO> students = roomService.alloom();


            ObservableList observableList= FXCollections.observableArrayList();
            observableList.addAll(students);
            tblRoom.setItems(observableList);


            List<String> ids = roomService.roomId();
            ObservableList<String>  idList= FXCollections.observableArrayList();
            idList.addAll(ids);
            cmbRoomId.setItems(idList);
            return;
        }else{
            lblQty.setText("Invalid Key Money or Qty");
            lblKeyMoney.setText("Invalid Key Money or Qty");
        }

    }
    RoomDTO roomDTO1;
    public void idOnAction(ActionEvent actionEvent) {

        long l;
        try {
            l =Long.parseLong((String) cmbRoomId.getValue());
            roomDTO1= roomService.getRoomDTO(l);
        }catch (NumberFormatException e){

        }
        txtQty.setText(String.valueOf(roomDTO1.getQty()));
        txtType.setText(roomDTO1.getType());
        txtKeyMoney.setText(roomDTO1.getKeyMoney());
    }
    public void deleteOnAction(ActionEvent actionEvent) {
        boolean b = roomService.deleteRoom(roomDTO1);
        if(b){
            cmbRoomId.setValue(null);
            txtType.setText(null);
            txtQty.setText(null);
            txtKeyMoney.setText(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Student Sucefully Deleted");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went Wrong");
            alert.show();
        }
        clmRoomTypeId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("id"));
        clmRoomType.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("type"));
        clmKeyMoney.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("keyMoney"));
        clmQty.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("qty"));
        List<RoomDTO> students = roomService.alloom();


        ObservableList observableList= FXCollections.observableArrayList();
        observableList.addAll(students);
        tblRoom.setItems(observableList);


        List<String> ids = roomService.roomId();
        ObservableList<String>  idList= FXCollections.observableArrayList();
        idList.addAll(ids);
        cmbRoomId.setItems(idList);
    }

    public void updateOnActiion(ActionEvent actionEvent) {
        if(validation.numberPattern(txtKeyMoney.getText()) && validation.numberPattern(txtQty.getText())){
            roomDTO1.setType(txtType.getText());
            roomDTO1.setKeyMoney(txtKeyMoney.getText());
            roomDTO1.setQty(Integer.parseInt(txtQty.getText()));
            boolean b = roomService.updateRoom(roomDTO1);
            if(b){
                cmbRoomId.setValue(null);
                txtType.setText(null);
                txtQty.setText(null);
                txtKeyMoney.setText(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Student Sucefully Updated");
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went Wrong");
                alert.show();
            }


            clmRoomTypeId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("id"));
            clmRoomType.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("type"));
            clmKeyMoney.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("keyMoney"));
            clmQty.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("qty"));
            List<RoomDTO> students = roomService.alloom();


            ObservableList observableList= FXCollections.observableArrayList();
            observableList.addAll(students);
            tblRoom.setItems(observableList);


            List<String> ids = roomService.roomId();
            ObservableList<String>  idList= FXCollections.observableArrayList();
            idList.addAll(ids);
            cmbRoomId.setItems(idList);
        }else {
            lblQty.setText("Invalid Key Money or Qty");
            lblKeyMoney.setText("Invalid Key Money or Qty");
        }


    }


    public void roomFOrnOnAction(ActionEvent actionEvent) {
    }

    public void studentFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Student, pane);
    }

    public void reservationOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Reservation, pane);
    }
}
