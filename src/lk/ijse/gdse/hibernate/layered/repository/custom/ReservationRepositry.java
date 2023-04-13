package lk.ijse.gdse.hibernate.layered.repository.custom;

import lk.ijse.gdse.hibernate.layered.entity.Reservation;
import lk.ijse.gdse.hibernate.layered.repository.CrudRepositry;

public interface ReservationRepositry extends CrudRepositry<Reservation, Long> {
    boolean changePaidStatus(long reserveId, String paidStatus);
}
