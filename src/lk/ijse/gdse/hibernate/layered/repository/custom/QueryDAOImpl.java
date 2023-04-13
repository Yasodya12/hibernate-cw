package lk.ijse.gdse.hibernate.layered.repository.custom;

import lk.ijse.gdse.hibernate.layered.projection.StudentDetailsDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl  implements QueryRepositry{
    private Session session;
    @Override
    public void setSession(Session session) {
        this.session = session;
    }
    @Override
    public List<StudentDetailsDTO> getUnpaidStudents() {

        String sql="SELECT new lk.ijse.gdse.hibernate.layered.projection.StudentDetailsDTO (s.id,s.name,s.address,s.contact,s.gender, r.res_id, r.room) FROM Student s INNER JOIN s.reservationList r WHERE r.status = 'Half Paid'";
        Query query = session.createQuery(sql);
        List<StudentDetailsDTO> list = query.list();
        session.close();
        return list;
    }

}
