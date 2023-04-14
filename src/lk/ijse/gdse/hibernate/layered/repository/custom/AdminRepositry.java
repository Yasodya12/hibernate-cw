package lk.ijse.gdse.hibernate.layered.repository.custom;

import lk.ijse.gdse.hibernate.layered.entity.Admin;
import lk.ijse.gdse.hibernate.layered.repository.CrudRepositry;

import java.util.List;

public interface AdminRepositry extends CrudRepositry<Admin, Long> {
     List<Admin> check(String userName, String password);
     public boolean reserPw(String userName, String pw);
}
