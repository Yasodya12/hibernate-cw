package lk.ijse.gdse.hibernate.layered.service;

import lk.ijse.gdse.hibernate.layered.dto.AdminDTO;

public interface AdminService {
     long saveAdmin(AdminDTO adminDTO);
     boolean check(String usename, String password);

}
