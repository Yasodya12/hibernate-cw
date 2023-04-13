package lk.ijse.gdse.hibernate.layered.repository.custom;

import lk.ijse.gdse.hibernate.layered.projection.StudentDetailsDTO;
import lk.ijse.gdse.hibernate.layered.repository.SuperRepositry;

import java.util.List;

public interface QueryRepositry extends SuperRepositry {
    List<StudentDetailsDTO> getUnpaidStudents();
}
