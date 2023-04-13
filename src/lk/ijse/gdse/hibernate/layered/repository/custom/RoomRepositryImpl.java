package lk.ijse.gdse.hibernate.layered.repository.custom;

import lk.ijse.gdse.hibernate.layered.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoomRepositryImpl implements RoomRepositry {
    private Session session;
    private static RoomRepositryImpl roomRepositry;

    public RoomRepositryImpl() {
    }

    public static RoomRepositryImpl getInstance() {
        return null == roomRepositry
                ? roomRepositry = new RoomRepositryImpl()
                : roomRepositry;
    }
    @Override
    public void setSession(Session session) {
        this.session = session;
    }
    @Override
    public Long save(Room room) {
        return (Long) session.save(room);
    }
    @Override
    public List<Room> getAll() {
        String sqlQuery = "FROM Room ";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }
    @Override
    public boolean delete(Room room) {
        try {
            session.delete(room);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public Room get(Long aLong) {
        return session.get(Room.class, aLong);

    }
    @Override
    public boolean update(Room room) {
        try {
            session.update(room);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }

    }
}

