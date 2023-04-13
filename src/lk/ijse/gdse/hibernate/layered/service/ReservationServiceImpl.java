package lk.ijse.gdse.hibernate.layered.service;

import lk.ijse.gdse.hibernate.layered.dto.ReservationDTO;
import lk.ijse.gdse.hibernate.layered.dto.RoomDTO;
import lk.ijse.gdse.hibernate.layered.dto.StudentDTO;
import lk.ijse.gdse.hibernate.layered.entity.Reservation;
import lk.ijse.gdse.hibernate.layered.entity.Room;
import lk.ijse.gdse.hibernate.layered.entity.Student;
import lk.ijse.gdse.hibernate.layered.projection.StudentDetailsDTO;
import lk.ijse.gdse.hibernate.layered.repository.RepositryFactory;
import lk.ijse.gdse.hibernate.layered.repository.custom.QueryDAOImpl;
import lk.ijse.gdse.hibernate.layered.repository.custom.ReservationRepositryImpl;
import lk.ijse.gdse.hibernate.layered.repository.custom.RoomRepositryImpl;
import lk.ijse.gdse.hibernate.layered.repository.custom.StudentRepositryImpl;
import lk.ijse.gdse.hibernate.layered.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService{
    private static ReservationServiceImpl reservationService;
    private Session session;

    private final ReservationRepositryImpl reservationRepositry;

    private final RoomRepositryImpl roomRepositry;

    private final StudentRepositryImpl studentRepositry;

    private final QueryDAOImpl queryDAO;

    public ReservationServiceImpl() {
        reservationRepositry = (ReservationRepositryImpl) RepositryFactory.getInstance().getRepositry(RepositryFactory.RepTypes.Reservation);
        roomRepositry= (RoomRepositryImpl) RepositryFactory.getInstance().getRepositry(RepositryFactory.RepTypes.Rooms);
        studentRepositry= (StudentRepositryImpl) RepositryFactory.getInstance().getRepositry(RepositryFactory.RepTypes.Student);
        queryDAO= (QueryDAOImpl) RepositryFactory.getInstance().getRepositry(RepositryFactory.RepTypes.Query);
    }

    public static ReservationServiceImpl getInstance() {
        return null == reservationService
                ? reservationService = new ReservationServiceImpl()
                : reservationService;
    }
@Override
    public boolean saveReservation(ReservationDTO reservationDTO) {
        session = SessionFactoryConfig.getInstance()
                .getSession();
        reservationRepositry.setSession(session);
        roomRepositry.setSession(session);
        Transaction transaction = session.beginTransaction();
        try {


            long id = reservationRepositry.save(reservationDTO.toEntity());
            boolean b = roomRepositry.update(reservationDTO.getRoom());

            if(id>0&&b){
                transaction.commit();
                session.close();
                return true;
            }else{

                session.close();
                return false;
            }
        } catch (Exception ex) {

            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
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
    public List<ReservationDTO> allReservation(){
        session = SessionFactoryConfig.getInstance().getSession();
        reservationRepositry.setSession(session);
        List<Reservation> students = reservationRepositry.getAll();

        List<ReservationDTO> reservationDTOS=new ArrayList<>();
        for (Reservation room: students) {
            ReservationDTO dto = room.toDTO();
            reservationDTOS.add(dto);
        }
        session.close();
        return reservationDTOS;
    }
    @Override
    public List<String> reservationId(){
        session= SessionFactoryConfig.getInstance().getSession();
        reservationRepositry.setSession(session);
        List<Reservation> reservations = reservationRepositry.getAll();
        List<String> ids=new ArrayList<>();
        for (Reservation reservation:reservations) {
            ids.add(String.valueOf(reservation.getRes_id()));
        }
        session.close();
        return ids;
    }
    @Override
    public ReservationDTO getReservation(long name){
        session=SessionFactoryConfig.getInstance().getSession();
        reservationRepositry.setSession(session);
        Reservation reservation = reservationRepositry.get(name);
        session.close();
        return reservation.toDTO();
    }


//    public List<StudentDetailsDTO> getUnpaidStudents() {
//        return queryDAO.getUnpaidStudents();
//    }
    public List<String> getUnpaidIds(){
        session=SessionFactoryConfig.getInstance().getSession();
        queryDAO.setSession(session);
        ArrayList idList = new ArrayList();
        List<StudentDetailsDTO> unpaidStudents = queryDAO.getUnpaidStudents();
        for (StudentDetailsDTO studentDetailsDTO:unpaidStudents) {
            idList.add(studentDetailsDTO.getResID());
        }
        return idList;
    }
    @Override
    public boolean updateReservationPaid(long id, String status){

        session= SessionFactoryConfig.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
        reservationRepositry.setSession(session);
        boolean b = reservationRepositry.changePaidStatus(id,status);
        if(b){
            transaction.commit();
        }else{
            transaction.rollback();
        }
        session.close();
        return b;
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
    public RoomDTO getRoomDTO(long name){
        session=SessionFactoryConfig.getInstance().getSession();
        roomRepositry.setSession(session);
        Room room = roomRepositry.get(name);
        session.close();
        return room.toDTO();
    }

    @Override

    public List<StudentDTO> allStudent(){
        session = SessionFactoryConfig.getInstance().getSession();
        studentRepositry.setSession(session);
        List<Student> students = studentRepositry.getAll();

        List<StudentDTO> studentDTOS=new ArrayList<>();
        for (Student student: students) {
            StudentDTO dto = student.toDTO();
            studentDTOS.add(dto);
        }
        session.close();
        return studentDTOS;
    }

    @Override
    public List<String> stdId(){
        session= SessionFactoryConfig.getInstance().getSession();
        studentRepositry.setSession(session);
        List<Student> students = studentRepositry.getAll();
        List<String> ids=new ArrayList<>();
        for (Student student:students) {
            ids.add(String.valueOf(student.getId()));
        }
        session.close();
        return ids;
    }
    @Override
    public StudentDTO getStudentDTO(long name){
        session=SessionFactoryConfig.getInstance().getSession();
        studentRepositry.setSession(session);
        Student student = studentRepositry.get(name);
        session.close();
        return student.toDTO();
    }

}
