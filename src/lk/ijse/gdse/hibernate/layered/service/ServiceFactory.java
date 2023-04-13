package lk.ijse.gdse.hibernate.layered.service;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    public ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        if (serviceFactory ==null) {
            serviceFactory =new ServiceFactory();
        }
        return serviceFactory;
    }

    public enum ServiceTypes {
        Student,Rooms,Reservation,Admin
    }


    public Object getService(ServiceTypes sTypes){
        switch (sTypes){
            case Student :
                return new StudentServiceImpl();
            case Rooms:
                return new RoomServiceImpl();
            case Reservation:
                return new ReservationServiceImpl();
            case Admin:
                return new AdminServiceImpl();
            default:
                return null;
        }
    }


}
