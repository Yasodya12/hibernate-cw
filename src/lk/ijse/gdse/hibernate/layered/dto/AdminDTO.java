package lk.ijse.gdse.hibernate.layered.dto;

import lk.ijse.gdse.hibernate.layered.entity.Admin;

public class AdminDTO {
    long id;
    String userName;
    String password;

    public AdminDTO() {
    }


    public AdminDTO(long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin toEntity(){
        Admin admin=new Admin();
        admin.setUserName(this.userName);
        admin.setPassword(this.password);
        return admin;
    }
}
