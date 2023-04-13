package lk.ijse.gdse.hibernate.layered.service;

import lk.ijse.gdse.hibernate.layered.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    long saveRoom(RoomDTO roomDTO);
    List<RoomDTO> alloom();
    List<String> roomId();
    boolean deleteRoom(RoomDTO roomDTO);
    RoomDTO getRoomDTO(long name);
    boolean updateRoom(RoomDTO roomDTO);

}
