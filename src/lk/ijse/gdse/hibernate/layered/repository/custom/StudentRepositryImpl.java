package lk.ijse.gdse.hibernate.layered.repository.custom;


import lk.ijse.gdse.hibernate.layered.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRepositryImpl implements StudentRepositry{
    private Session session;
    private static StudentRepositryImpl studentRepositry;
    public StudentRepositryImpl() {}
    public static StudentRepositryImpl getInstance() {
        return null == studentRepositry
                ? studentRepositry = new StudentRepositryImpl()
                : studentRepositry;
    }
    @Override
    public void setSession(Session session) {
        this.session = session;
    }
    @Override
    public Long save(Student student) {
        return (Long) session.save(student);
    }
    @Override
    public List<Student> getAll(){
        String sqlQuery = "FROM Student";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }
    @Override
    public Student get(Long id){
        return session.get(Student.class, id);

    }
    @Override
    public Student getStudentByName(String name){
        String sqlQuery = "FROM Student where name=?"+name;
        Student student = (Student) session.createQuery(sqlQuery);
        return student;


    }
    @Override
    public boolean delete(Student student){
        try {
            session.delete(student);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public boolean update(Student student){
        try {
            session.update(student);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
