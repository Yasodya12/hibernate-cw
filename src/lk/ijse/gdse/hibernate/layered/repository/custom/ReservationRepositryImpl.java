package lk.ijse.gdse.hibernate.layered.repository.custom;
import lk.ijse.gdse.hibernate.layered.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationRepositryImpl implements ReservationRepositry{
    private Session session;
    private static ReservationRepositryImpl reservationRepositry;

    public ReservationRepositryImpl() {
    }

    public static ReservationRepositryImpl getInstance() {
        return null == reservationRepositry
                ? reservationRepositry = new ReservationRepositryImpl()
                : reservationRepositry;
    }
    @Override
    public void setSession(Session session) {
        this.session = session;
    }
    @Override
    public Long save(Reservation reservation) {
        return (Long) session.save(reservation);
    }

    @Override
    public boolean update(Reservation object) {
        return false;
    }

    @Override
    public boolean delete(Reservation object) {
        return false;
    }
    @Override
    public List<Reservation> getAll() {
        String sqlQuery = "FROM Reservation";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }
    @Override
    public Reservation get(Long name) {
        return session.get(Reservation.class, name);
    }
    @Override
    public boolean changePaidStatus(long reserveId, String paidStatus) {
        String hql="update Reservation r set r.status=:sts where r.res_id=:rid";
        Query query = session.createQuery(hql);
        query.setParameter("sts",paidStatus);
        query.setParameter("rid",reserveId);
        int count = query.executeUpdate();
        return count >= 0;
    }
}
