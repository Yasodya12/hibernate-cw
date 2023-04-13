package lk.ijse.gdse.hibernate.layered.dto;


import lk.ijse.gdse.hibernate.layered.entity.Student;

import java.sql.Timestamp;


public class StudentDTO {


    private long id;

    private String name;

    private String address;

    private String contact;

    private String gender;

    private Timestamp time;

    public StudentDTO() {
    }

    public StudentDTO(long id, String name, String address, String contact, String gender, Timestamp time) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.gender = gender;
        this.time = time;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public long getId() {
        return id;
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
        return "StudentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", gender='" + gender + '\'' +
                ", time=" + time +
                '}';
    }

    public Student toEntity(){
        Student student=new Student();
        student.setId(this.id);
        student.setName(this.name);
        student.setAddress(this.address);
        student.setContact(this.contact);
        student.setGender(this.gender);
        return student;
    }
}


