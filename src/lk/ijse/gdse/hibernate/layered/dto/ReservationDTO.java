package lk.ijse.gdse.hibernate.layered.dto;

import lk.ijse.gdse.hibernate.layered.entity.Reservation;
import lk.ijse.gdse.hibernate.layered.entity.Room;
import lk.ijse.gdse.hibernate.layered.entity.Student;

import javax.persistence.Id;
import java.sql.Timestamp;

public class ReservationDTO {

    private long res_id;



    private String status;


    private Student student;


    private Room room;

    public ReservationDTO() {
    }

    public ReservationDTO(long res_id, String status, Student student, Room room) {
        this.res_id = res_id;
        this.status = status;
        this.student = student;
        this.room = room;
    }

    public long getRes_id() {
        return res_id;
    }

    public void setRes_id(long res_id) {
        this.res_id = res_id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "res_id='" + res_id + '\'' +
                ", status='" + status + '\'' +
                ", student=" + student +
                ", room=" + room +
                '}';
    }

    public Reservation toEntity(){
        Reservation reservation = new Reservation();
        reservation.setRes_id(this.res_id);
        reservation.setStatus(this.status);
        reservation.setStudent(this.student);
        reservation.setRoom(this.room);
        return  reservation;
    }

}
