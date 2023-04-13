package lk.ijse.gdse.hibernate.layered.entity;
import lk.ijse.gdse.hibernate.layered.dto.ReservationDTO;
import lk.ijse.gdse.hibernate.layered.dto.RoomDTO;
import lk.ijse.gdse.hibernate.layered.dto.StudentDTO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long res_id;
    @CreationTimestamp
    private Timestamp date;

    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="s_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="r_id", referencedColumnName = "id")
    private Room room;

    public Reservation() {
    }

    public Reservation(long res_id, Timestamp date, String status, Student student, Room room) {
        this.res_id = res_id;
        this.date = date;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
        return "Reservation{" +
                "res_id='" + res_id + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", student=" + student +
                ", room=" + room +
                '}';
    }
    public ReservationDTO toDTO(){
        ReservationDTO student=new ReservationDTO();
        student.setRes_id(this.res_id);
        student.setStatus(this.status);
        student.setStudent(this.student);
        student.setRoom(this.room);
        return student;
    }
}
