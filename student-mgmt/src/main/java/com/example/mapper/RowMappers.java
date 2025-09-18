package com.example.mapper;

import com.example.model.Student;
import org.springframework.jdbc.core.RowMapper;

public class RowMappers {
    public static final RowMapper<Student> STUDENT_MAPPER = (rs, rowNum) -> {
        Student s = new Student();
        s.setStudentId(rs.getInt("student_id"));
        s.setFirstName(rs.getString("first_name"));
        s.setLastName(rs.getString("last_name"));
        s.setEmail(rs.getString("email"));
        s.setPhone(rs.getString("phone"));
        java.sql.Date dob = rs.getDate("dob");
        if (dob != null) s.setDob(dob.toLocalDate());
        java.sql.Timestamp admTs = rs.getTimestamp("admission_date");
        if (admTs != null) s.setAdmissionDate(admTs.toInstant().atOffset(java.time.ZoneOffset.UTC));
        return s;
    };
}
