package lk.ijse.gdse.hibernate.layered.projection;

import lk.ijse.gdse.hibernate.layered.entity.Room;


import java.util.Date;

public class StudentDetailsDTO {
    private long studentID;
    private String name;

    private String address;

    private String contact;

    private String gender;

    private long resID;


    private Room room;

    public StudentDetailsDTO() {
    }

    public StudentDetailsDTO(long studentID, String name, String address, String contact, String gender, long resID, Room room) {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.gender = gender;
        this.resID = resID;
        this.room = room;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getResID() {
        return resID;
    }

    public void setResID(long resID) {
        this.resID = resID;
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    @Override
    public String toString() {
        return "StudentDetailsDTO{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", gender='" + gender + '\'' +
                ", resID=" + resID +
                ", room=" + room +
                '}';
    }
}
