package lk.ijse.gdse.hibernate.layered.service;


import lk.ijse.gdse.hibernate.layered.dto.StudentDTO;

import lk.ijse.gdse.hibernate.layered.entity.Student;
import lk.ijse.gdse.hibernate.layered.repository.RepositryFactory;
import lk.ijse.gdse.hibernate.layered.repository.custom.StudentRepositryImpl;
import lk.ijse.gdse.hibernate.layered.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService{
    private static StudentServiceImpl studentService;
    private Session session;

    private final StudentRepositryImpl studentRepositry;

    public StudentServiceImpl() {
        studentRepositry = (StudentRepositryImpl) RepositryFactory.getInstance().getRepositry(RepositryFactory.RepTypes.Student);
    }

    public static StudentServiceImpl getInstance() {
        return null == studentService
                ? studentService = new StudentServiceImpl()
                : studentService;
    }
    @Override
    public long saveCustomer(StudentDTO studentDTO) { // We're getting a DTO type from the controller
        session = SessionFactoryConfig.getInstance()
                .getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRepositry.setSession(session);
            long id = studentRepositry.save(studentDTO.toEntity()); // We pass it to the repository by converting it to an entity
            transaction.commit();
            session.close();
            return id;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return -1l;
        }
    }
    @Override
    public List<StudentDTO> allStudent(){
        session = SessionFactoryConfig.getInstance().getSession();
        studentRepositry.setSession(session);
        List<Student> students = studentRepositry.getAll();

        List<StudentDTO> studentDTOS=new ArrayList<>();
        for (Student student: students) {
            StudentDTO dto = student.toDTO();
            studentDTOS.add(dto);
        }
        session.close();
        return studentDTOS;
    }
    @Override
    public List<String> stdNames(){
        session = SessionFactoryConfig.getInstance().getSession();
        studentRepositry.setSession(session);
        List<Student> students = studentRepositry.getAll();
        List<String> names=new ArrayList<>();
        for (Student student:students) {
            names.add(student.getName());
        }
        session.close();
        return names;
    }
    @Override
    public List<String> stdId(){
       session= SessionFactoryConfig.getInstance().getSession();
       studentRepositry.setSession(session);
        List<Student> students = studentRepositry.getAll();
        List<String> ids=new ArrayList<>();
        for (Student student:students) {
            ids.add(String.valueOf(student.getId()));
        }
        session.close();
        return ids;
    }
    @Override
    public boolean deleteStudent(StudentDTO studentDTO){

       session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        studentRepositry.setSession(session);
        boolean b = studentRepositry.delete(studentDTO.toEntity());
        if(b){
            transaction.commit();
        }else{
            transaction.rollback();
        }
        session.close();
        return b;
    }
    @Override
    public boolean updateStudent(StudentDTO studentDTO){

        session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        studentRepositry.setSession(session);
        boolean b = studentRepositry.update(studentDTO.toEntity());
        if(b){
            transaction.commit();
        }else{
            transaction.rollback();
        }
        session.close();
        return b;
    }
    @Override
    public StudentDTO getStudentDTO(long name){
        session=SessionFactoryConfig.getInstance().getSession();
        studentRepositry.setSession(session);
        Student student = studentRepositry.get(name);
        session.close(); // We've closed the unclosed sessions in previous week's code
        return student.toDTO();
    }
    @Override
    public StudentDTO searchByName(String name){
        session=SessionFactoryConfig.getInstance().getSession();
        studentRepositry.setSession(session);
        Student studentByName = studentRepositry.getStudentByName(name);
        session.close();
        return studentByName.toDTO();

    }

}
