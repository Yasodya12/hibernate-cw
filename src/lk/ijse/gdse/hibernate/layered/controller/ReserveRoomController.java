package lk.ijse.gdse.hibernate.layered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hibernate.layered.dto.ReservationDTO;
import lk.ijse.gdse.hibernate.layered.dto.RoomDTO;
import lk.ijse.gdse.hibernate.layered.dto.StudentDTO;
import lk.ijse.gdse.hibernate.layered.service.*;
import lk.ijse.gdse.hibernate.layered.util.Navigation;
import lk.ijse.gdse.hibernate.layered.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReserveRoomController implements Initializable {
    public TableView tblRoom;
    public TableColumn clmRoomTypeId;
    public TableColumn clmRoomType;
    public TableColumn clmKeyMoney;
    public TableColumn clmQty;
    public TableView tblStudent;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn clmAddress;
    public TableColumn clmRejDate;
    public TableColumn clmGender;
    public TableColumn clmContact;
    public ComboBox cmdStudentId2;
    public ComboBox cmbRoomId;
    public ToggleGroup payment;
    public Label lblRoomType;
    public Label lblKeyMoney;
    public Label lblQty;
    public Label lblName;
    public Label lblAddress;
    public Label lblContact;
    public Label lblGender;
    public TableView tblReservation;
    public TableColumn clmResId;
    public TableColumn clmDate;
    public TableColumn clmStudentID;
    public TableColumn clmRoomTypeID;
    public TableColumn clmStatus;
    public RadioButton rbnFullPaid;
    public RadioButton rbnHaldPaid;
    public AnchorPane pane;
    public ComboBox cmdReserve;
    public Button btnUpdate;
    public Button btnreserve;
    public Label lblPaymentType;

    private ReservationServiceImpl reservationService= (ReservationServiceImpl) ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.Reservation);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnUpdate.setDisable(true);
        List<String> unpaidIds = reservationService.getUnpaidIds();
        ObservableList observableListReservation=  FXCollections.observableArrayList();
        observableListReservation.addAll(unpaidIds);
        cmdReserve.setItems(observableListReservation);


        clmRoomTypeId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("id"));
        clmRoomType.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("type"));
        clmKeyMoney.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("keyMoney"));
        clmQty.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("qty"));
        List<RoomDTO> roomDTOS = reservationService.alloom();


        ObservableList observableListRoom= FXCollections.observableArrayList();
        observableListRoom.addAll(roomDTOS);
        tblRoom.setItems(observableListRoom);


        clmId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("contact"));
        clmGender.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("gender"));
        clmRejDate.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("time"));



        List<StudentDTO> students = reservationService.allStudent();
        ObservableList observableListStudent= FXCollections.observableArrayList();
        observableListStudent.addAll(students);
        tblStudent.setItems(observableListStudent);


        clmResId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("res_id"));
        clmDate.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("name"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("status"));
        clmStudentID.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("student"));
        clmRoomTypeID.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("room"));

        List<ReservationDTO> reservations = reservationService.allReservation();
        ObservableList reservation=FXCollections.observableArrayList();
        reservation.addAll(reservations);
        tblReservation.setItems(reservation);

        List<String> names = reservationService.stdId();
        ObservableList observableListNames=FXCollections.observableArrayList();
        observableListNames.addAll(names);
        cmdStudentId2.setItems(observableListNames);

        List<String> roomId = reservationService.roomId();
        ObservableList observableListRooms=  FXCollections.observableArrayList();
        observableListRooms.addAll(roomId);
        cmbRoomId.setItems(observableListRooms);



    }

    public void btnReserveOnAction2(ActionEvent actionEvent) {

        if(rbnFullPaid.isSelected() || rbnHaldPaid.isSelected()){
            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setStudent(studentDTO.toEntity());
            roomDTO1.setQty(Integer.parseInt(lblQty.getText())-1);
            reservationDTO.setRoom(roomDTO1.toEnity());


            reservationDTO.setStatus("Half Paid");
            if(rbnFullPaid.isSelected()){
                reservationDTO.setStatus("Full Paid");

            }

            if(reservationService.saveReservation(reservationDTO)){

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Reservation Done");
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went Wrong");
                alert.show();
            }





            clmRoomTypeId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("id"));
            clmRoomType.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("type"));
            clmKeyMoney.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("keyMoney"));
            clmQty.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("qty"));
            List<RoomDTO> roomDTOS = reservationService.alloom();


            ObservableList observableListRoom= FXCollections.observableArrayList();
            observableListRoom.addAll(roomDTOS);
            tblRoom.setItems(observableListRoom);


            clmId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("id"));
            clmName.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("name"));
            clmAddress.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("address"));
            clmContact.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("contact"));
            clmGender.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("gender"));
            clmRejDate.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("time"));



            List<StudentDTO> students = reservationService.allStudent();
            ObservableList observableListStudent= FXCollections.observableArrayList();
            observableListStudent.addAll(students);
            tblStudent.setItems(observableListStudent);


            clmResId.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("res_id"));
            clmDate.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("name"));
            clmStatus.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("status"));
            clmStudentID.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("student"));
            clmRoomTypeID.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("room"));

            List<ReservationDTO> reservations = reservationService.allReservation();
            ObservableList reservation=FXCollections.observableArrayList();
            reservation.addAll(reservations);
            tblReservation.setItems(reservation);

            List<String> names = reservationService.stdId();
            ObservableList observableListNames=FXCollections.observableArrayList();
            observableListNames.addAll(names);
            cmdStudentId2.setItems(observableListNames);

            List<String> roomId = reservationService.roomId();
            ObservableList observableListRooms=  FXCollections.observableArrayList();
            observableListRooms.addAll(roomId);
            cmbRoomId.setItems(observableListRooms);

            List<ReservationDTO> reservationDTOS = reservationService.allReservation();
        }else{
            lblPaymentType.setText("Plese Select Payment Type");
        }
    }
    StudentDTO studentDTO;
    public void stdIdOnAction(ActionEvent actionEvent) {
        long l;

        try {
            l = Long.parseLong((String) cmdStudentId2.getValue());
            btnUpdate.setDisable(true);
            btnreserve.setDisable(false);
            studentDTO = reservationService.getStudentDTO(l);
            cmdReserve.setValue(null);
            cmbRoomId.setValue(null);
        } catch (NumberFormatException e) {

        }

        lblGender.setText(studentDTO.getGender());
        lblName.setText(studentDTO.getName());
        lblAddress.setText(studentDTO.getAddress());
        lblContact.setText(studentDTO.getContact());

    }
    RoomDTO roomDTO1;

    public void roomIdOnAction(ActionEvent actionEvent) {
        long l;
        try {
            l =Long.parseLong((String) cmbRoomId.getValue());
            roomDTO1= reservationService.getRoomDTO(l);
            btnUpdate.setDisable(true);
            btnreserve.setDisable(false);
            cmdReserve.setValue(null);
            cmdStudentId2.setValue(null);
        }catch (NumberFormatException e){

        }
        lblQty.setText(String.valueOf(roomDTO1.getQty()));
        lblRoomType.setText(roomDTO1.getType());
        lblKeyMoney.setText(roomDTO1.getKeyMoney());
    }


    public void roomFOrnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Rooms, pane);
    }

    public void studentFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Student, pane);
    }

    public void reservationOnAction(ActionEvent actionEvent) {
    }


    public void cmdReserveOnAction(ActionEvent actionEvent) {

        long l;
        try {
           l= (long) cmdReserve.getValue();
            ReservationDTO reservation= reservationService.getReservation(l);
            lblRoomType.setText(reservation.getRoom().getType());
            lblKeyMoney.setText(reservation.getRoom().getKeyMoney());
            lblQty.setText(String.valueOf(reservation.getRoom().getQty()));

            lblName.setText(reservation.getStudent().getName());
            lblAddress.setText(reservation.getStudent().getAddress());
            lblContact.setText(reservation.getStudent().getContact());
            lblGender.setText(reservation.getStudent().getGender());
            btnUpdate.setDisable(false);
            btnreserve.setDisable(true);
            cmdStudentId2.setValue(null);
            cmbRoomId.setValue(null);
        }catch (NumberFormatException e){

        }
    }

    public void updateAsFullyOnAction(ActionEvent actionEvent) {
        long l = (long) cmdReserve.getValue();
        if (reservationService.updateReservationPaid(l, "Full Paid")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Payment Updated");
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "Something went Wrong");
        alert.show();
    }
}
