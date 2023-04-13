package lk.ijse.gdse.hibernate.layered.service;

import lk.ijse.gdse.hibernate.layered.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    long saveCustomer(StudentDTO studentDTO);
    List<StudentDTO> allStudent();
    List<String> stdNames();
    List<String> stdId();
    boolean deleteStudent(StudentDTO studentDTO);
    boolean updateStudent(StudentDTO studentDTO);
    StudentDTO getStudentDTO(long name);
    StudentDTO searchByName(String name);
}
