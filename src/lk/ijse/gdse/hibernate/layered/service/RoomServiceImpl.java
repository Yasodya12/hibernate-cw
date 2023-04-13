package lk.ijse.gdse.hibernate.layered.service;

import lk.ijse.gdse.hibernate.layered.dto.RoomDTO;
import lk.ijse.gdse.hibernate.layered.entity.Room;
import lk.ijse.gdse.hibernate.layered.repository.RepositryFactory;
import lk.ijse.gdse.hibernate.layered.repository.custom.RoomRepositryImpl;
import lk.ijse.gdse.hibernate.layered.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService{
    private static RoomServiceImpl roomService;
    private Session session;

    private final RoomRepositryImpl roomRepositry;

    public RoomServiceImpl() {
        roomRepositry = (RoomRepositryImpl) RepositryFactory.getInstance().getRepositry(RepositryFactory.RepTypes.Rooms);
    }

    public static RoomServiceImpl getInstance() {
        return null == roomService
                ? roomService = new RoomServiceImpl()
                : roomService;
    }
    @Override
    public long saveRoom(RoomDTO roomDTO) {
        session = SessionFactoryConfig.getInstance()
                .getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepositry.setSession(session);
            long id = roomRepositry.save(roomDTO.toEnity());
            transaction.commit();
            session.close();
            return id;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return -1l;
        }
    }
    @Override
    public List<RoomDTO> alloom(){
        session = SessionFactoryConfig.getInstance().getSession();
        roomRepositry.setSession(session);
        List<Room> students = roomRepositry.getAll();

        List<RoomDTO> roomDTOS=new ArrayList<>();
        for (Room room: students) {
            RoomDTO dto = room.toDTO();
            roomDTOS.add(dto);
        }
        session.close();
        return roomDTOS;
    }
    @Override
    public List<String> roomId(){
        session= SessionFactoryConfig.getInstance().getSession();
        roomRepositry.setSession(session);
        List<Room> rooms = roomRepositry.getAll();
        List<String> ids=new ArrayList<>();
        for (Room student:rooms) {
            ids.add(String.valueOf(student.getId()));
        }
        session.close();
        return ids;
    }
    @Override
    public boolean deleteRoom(RoomDTO roomDTO){

        session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        roomRepositry.setSession(session);
        boolean b = roomRepositry.delete(roomDTO.toEnity());
        if(b){
            transaction.commit();
        }else{
            transaction.rollback();
        }
        session.close();
        return b;
    }
    @Override
    public RoomDTO getRoomDTO(long name){
        session=SessionFactoryConfig.getInstance().getSession();
        roomRepositry.setSession(session);
        Room room = roomRepositry.get(name);
        session.close();
        return room.toDTO();
    }
    @Override
    public boolean updateRoom(RoomDTO roomDTO){

        session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        roomRepositry.setSession(session);
        boolean b = roomRepositry.update(roomDTO.toEnity());
        if(b){
            transaction.commit();
        }else{
            transaction.rollback();
        }
        session.close();
        return b;
    }
}
