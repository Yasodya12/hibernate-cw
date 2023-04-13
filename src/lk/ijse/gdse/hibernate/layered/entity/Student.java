package lk.ijse.gdse.hibernate.layered.entity;


import lk.ijse.gdse.hibernate.layered.dto.StudentDTO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Student_table")
public class Student implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Address")
    private String address;
    @Column(name = "Contact")
    private String contact;
    @CreationTimestamp
    private Timestamp date;
    @Column(name = "Gender")
    private String gender;

    @OneToMany(mappedBy = "student", fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservationList=new ArrayList<>();

    public Student() {
    }

    public Student(long id, String name, String address, String contact, Timestamp date, String gender, List<Reservation> reservationList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.date = date;
        this.gender = gender;
        this.reservationList = reservationList;
    }

    public long getId() {
        return id;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public StudentDTO toDTO(){
        StudentDTO student=new StudentDTO();
        student.setId(this.id);
        student.setName(this.name);
        student.setAddress(this.address);
        student.setContact(this.contact);
        student.setGender(this.gender);
        student.setTime(this.date);
        return student;
    }
}


