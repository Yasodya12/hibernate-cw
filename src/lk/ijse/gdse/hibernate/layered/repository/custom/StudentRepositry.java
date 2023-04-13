package lk.ijse.gdse.hibernate.layered.repository.custom;

import lk.ijse.gdse.hibernate.layered.entity.Student;
import lk.ijse.gdse.hibernate.layered.repository.CrudRepositry;

import javax.sql.rowset.CachedRowSet;

public interface StudentRepositry extends CrudRepositry<Student, Long> {
    Student getStudentByName(String name);
}
