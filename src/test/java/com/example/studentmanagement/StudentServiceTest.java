package com.example.studentmanagement;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testSave() {
        Student student = new Student("LACHGAR", "Mohamed", new Date());
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student saved = studentService.save(student);

        assertNotNull(saved);
        assertEquals("LACHGAR", saved.getNom());
        verify(studentRepository).save(student);
    }

    @Test
    void testDelete_WhenStudentExists() {
        Student student = new Student();
        student.setId(1);
        when(studentRepository.findById(1)).thenReturn(student);
        doNothing().when(studentRepository).delete(student);

        boolean result = studentService.delete(1);

        assertTrue(result);
        verify(studentRepository).delete(student);
    }

    @Test
    void testDelete_WhenStudentDoesNotExist() {
        when(studentRepository.findById(99)).thenReturn(null);

        boolean result = studentService.delete(99);

        assertFalse(result);
    }

    @Test
    void testFindAll() {
        List<Student> list = Arrays.asList(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(list);

        List<Student> result = studentService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        Student student = new Student();
        student.setId(1);
        when(studentRepository.findById(1)).thenReturn(student);

        Student result = studentService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testCountStudents() {
        when(studentRepository.count()).thenReturn(5L);

        long count = studentService.countStudents();

        assertEquals(5L, count);
    }

    @Test
    void testFindNbrStudentByYear() {
        List<Object[]> mockData = new java.util.ArrayList<>();
        mockData.add(new Object[]{1985, 2L});
        when(studentRepository.findNbrStudentByYear()).thenReturn(mockData);

        Collection<?> result = studentService.findNbrStudentByYear();

        assertEquals(1, result.size());
    }
}
