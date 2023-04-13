package lk.ijse.gdse.hibernate.layered.repository;


import lk.ijse.gdse.hibernate.layered.entity.Admin;
import lk.ijse.gdse.hibernate.layered.repository.custom.*;

public class RepositryFactory {

    private static RepositryFactory repositryFactory;
    private RepositryFactory() {
    }

    public static RepositryFactory getInstance(){
        if (repositryFactory ==null) {
            repositryFactory =new RepositryFactory();
        }
        return repositryFactory;
    }

    public enum RepTypes {
        Student,Rooms,Reservation,Admin,Query
    }

    public Object getRepositry(RepTypes types){
        switch (types) {
            case Student:
                return new StudentRepositryImpl();

            case Admin:
                return new AdminRepositryImpl();

            case Rooms:
                return new RoomRepositryImpl();

            case Reservation:
                return new ReservationRepositryImpl();
            case Query:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }

}
