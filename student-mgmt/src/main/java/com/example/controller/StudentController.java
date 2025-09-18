package com.example.controller;

import com.example.dto.StudentDto;
import com.example.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Student", description = "Student management endpoints")
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://student-app:3000")
public class StudentController {

    private final StudentService srv;

    public StudentController(StudentService srv) {
        this.srv = srv;
    }

    @Operation(summary = "Get all students")
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.ok(srv.getAll());
    }

    @Operation(summary = "Get student by ID")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable Integer id) {
        StudentDto dto = srv.getById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @Operation(summary = "Create student")
    @PostMapping
    public ResponseEntity<StudentDto> create(@Valid @RequestBody StudentDto dto) {
        StudentDto created = srv.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Update student")
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable Integer id, @Valid @RequestBody StudentDto dto) {
        StudentDto updated = srv.update(id, dto);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete student")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        srv.delete(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}