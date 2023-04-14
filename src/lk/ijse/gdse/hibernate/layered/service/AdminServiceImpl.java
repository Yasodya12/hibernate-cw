package lk.ijse.gdse.hibernate.layered.service;

import lk.ijse.gdse.hibernate.layered.dto.AdminDTO;
import lk.ijse.gdse.hibernate.layered.entity.Admin;


import lk.ijse.gdse.hibernate.layered.repository.RepositryFactory;
import lk.ijse.gdse.hibernate.layered.repository.custom.AdminRepositryImpl;
import lk.ijse.gdse.hibernate.layered.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AdminServiceImpl implements AdminService{
    private static AdminServiceImpl adminService;
    private Session session;

    private final AdminRepositryImpl adminRepositry;



    public AdminServiceImpl() {
      adminRepositry  = (AdminRepositryImpl) RepositryFactory.getInstance().getRepositry(RepositryFactory.RepTypes.Admin);

    }

    public static AdminServiceImpl getInstance() {
        return null == adminService
                ? adminService = new AdminServiceImpl()
                : adminService;
    }
    @Override
    public long saveAdmin(AdminDTO adminDTO) { // We're getting a DTO type from the controller
        session = SessionFactoryConfig.getInstance()
                .getSession();
        Transaction transaction = session.beginTransaction();
        try {

            adminRepositry.setSession(session);
            long id = adminRepositry.save(adminDTO.toEntity()); // We pass it to the repository by converting it to an entity
            transaction.commit();
            session.close();
            return id;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return -1;
        }
    }
    @Override
    public boolean check(String usename, String password) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            adminRepositry.setSession(session);
            List<Admin> check = adminRepositry.check(usename, password);

            if(check.iterator().hasNext()){

                return true;
            }
            return false;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            return false;
        }
    }
    @Override
    public boolean reserPw(String userName, String pw){
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        adminRepositry.setSession(session);
      return  adminRepositry.reserPw(userName,pw);
    }

}
