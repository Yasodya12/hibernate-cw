package lk.ijse.gdse.hibernate.layered.entity;

import lk.ijse.gdse.hibernate.layered.dto.AdminDTO;
import lk.ijse.gdse.hibernate.layered.dto.ReservationDTO;

import javax.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(length = 10,name = "userId")
    String userName;
    @Column(name = "u_password")
    String password;

    public Admin() {
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

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public AdminDTO toDTO(){
        AdminDTO adminDTO=new AdminDTO();
        adminDTO.setUserName(this.userName);
        adminDTO.setPassword(this.password);
        return adminDTO;
    }
}
