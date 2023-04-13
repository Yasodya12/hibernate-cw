package lk.ijse.gdse.hibernate.layered.util;


import lk.ijse.gdse.hibernate.layered.entity.Admin;
import lk.ijse.gdse.hibernate.layered.entity.Reservation;
import lk.ijse.gdse.hibernate.layered.entity.Room;
import lk.ijse.gdse.hibernate.layered.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;





public class SessionFactoryConfig {

    private static SessionFactoryConfig factoryConfiguration;
    private final SessionFactory sessionFactory;


    private SessionFactoryConfig() {
        sessionFactory = new Configuration()
                .mergeProperties(Utility.getProperties())
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();
    }


    public static SessionFactoryConfig getInstance() {
        return (null == factoryConfiguration)
                ? factoryConfiguration = new SessionFactoryConfig()
                : factoryConfiguration;
    }


    public Session getSession() throws HibernateException {
        // Opens a new Session through the Session Factory & returns the Session created
        return sessionFactory.openSession();
    }
}
