package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // all crud database methods
}
