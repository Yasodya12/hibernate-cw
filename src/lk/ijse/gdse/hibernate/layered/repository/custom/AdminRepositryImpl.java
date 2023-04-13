package lk.ijse.gdse.hibernate.layered.repository.custom;


import lk.ijse.gdse.hibernate.layered.entity.Admin;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AdminRepositryImpl implements AdminRepositry{
    private Session session;

    public AdminRepositryImpl() {
    }
    public static AdminRepositryImpl adminRepositry;

    public static AdminRepositryImpl getInstance(){
        return null == adminRepositry
                ? adminRepositry = new AdminRepositryImpl()
                : adminRepositry;
    }
    @Override
    public void setSession(Session session) {
        this.session = session;

    }

//    public Admin getAdmin(String name){
//        return session.get(Admin.class, name);
//    }
    @Override
    public List<Admin> check(String userName, String password){
        String sqlQuery = "Select admin.id from Admin admin where admin.userName=:userName and admin.password=:password";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("userName", userName);
        query.setParameter("password", password);

        List<Admin> admin = (List) query.list();
        session.close();

        return admin;
    }


    @Override
    public Long save(Admin admin) {
        return (long) session.save(admin);
    }

    @Override
    public boolean update(Admin object) {
        return false;
    }

    @Override
    public Admin get(Long aLong) {
        return null;
    }

    @Override
    public boolean delete(Admin object) {
        return false;
    }

    @Override
    public List<Admin> getAll() {
        return null;
    }
}
