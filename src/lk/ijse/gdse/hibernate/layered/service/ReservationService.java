package lk.ijse.gdse.hibernate.layered.service;

import lk.ijse.gdse.hibernate.layered.dto.ReservationDTO;
import lk.ijse.gdse.hibernate.layered.dto.RoomDTO;
import lk.ijse.gdse.hibernate.layered.dto.StudentDTO;

import java.util.List;

public interface ReservationService {
     boolean saveReservation(ReservationDTO reservationDTO);
     List<RoomDTO> alloom();
    List<ReservationDTO> allReservation();
    List<String> reservationId();
    ReservationDTO getReservation(long name);
    List<String> getUnpaidIds();
    boolean updateReservationPaid(long id, String status);
    List<String> roomId();
    RoomDTO getRoomDTO(long name);
    List<StudentDTO> allStudent();
    List<String> stdId();
    StudentDTO getStudentDTO(long name);
}
