package com.example.service;

import com.example.dto.StudentDto;
import com.example.model.Student;
import com.example.repository.StudentRepositoryJdbc;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepositoryJdbc repo;

    public StudentService(StudentRepositoryJdbc repo) {
        this.repo = repo;
    }

    public List<StudentDto> getAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public StudentDto getById(Integer id) {
        return repo.findById(id).map(this::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id " + id));
    }

    @Transactional
    public StudentDto create(StudentDto dto) {
        Student s = fromDto(dto);
        Student created = repo.save(s);
        return toDto(created);
    }

    @Transactional
    public StudentDto update(Integer id, StudentDto dto) {
        Student existing = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id " + id));

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setDob(dto.getDob());

        Student updated = repo.save(existing);
        return toDto(updated);
    }

    @Transactional
    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Student not found with id " + id);
        }
        repo.deleteById(id);
    }

    // -------- Mapper methods --------
    private StudentDto toDto(Student s) {
        StudentDto d = new StudentDto();
        d.setStudentId(s.getStudentId());
        d.setFirstName(s.getFirstName());
        d.setLastName(s.getLastName());
        d.setEmail(s.getEmail());
        d.setPhone(s.getPhone());
        d.setDob(s.getDob());
        return d;
    }

    private Student fromDto(StudentDto d) {
        Student s = new Student();
        s.setStudentId(d.getStudentId()); // optional if generated
        s.setFirstName(d.getFirstName());
        s.setLastName(d.getLastName());
        s.setEmail(d.getEmail());
        s.setPhone(d.getPhone());
        s.setDob(d.getDob());
        return s;
    }
}
