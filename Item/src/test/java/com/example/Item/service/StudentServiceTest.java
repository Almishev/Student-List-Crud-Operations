package com.example.Item.service;

import com.example.Item.model.Student;
import com.example.Item.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.BDDMockito.willDoNothing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class StudentServiceTest {

    @MockBean
    private StudentRepository repository;

    private StudentService fixture;

    @BeforeEach
    public void setUp() {
        fixture = new StudentService(repository);
    }

    @Test
    public void getStudents_ReturnsStudents_WhenStudentsExists() {

        int pageNumber = (int) (Math.random() * 100);
        int pageSize = (int) (Math.random() * 100);

        int totalRecords = (int) (Math.random() * 100);

        Student student1 = new Student(1, "One","Onev");
        Student student2 = new Student(2,  "Two","Twov");
        Student student3 = new Student(3, "Three","Threev");

        final List<Student> students = Arrays.asList(student1, student2, student3);

        final PageRequest page = PageRequest.of(pageNumber, pageSize);

        final Page<Student> expected = new PageImpl<>(students, page, totalRecords);

        given(repository.findAll(page)).willReturn(expected);

        Page<Student> actual = fixture.getStudents(pageNumber, pageSize);

        assertThat(actual).isEqualTo(expected);
        assertThat(actual.getContent()).hasSameElementsAs(students);
        assertThat(actual.getPageable()).isEqualTo(page);

        then(repository).should().findAll(page);
        then(repository).shouldHaveNoMoreInteractions();
    }


    @Test
    public void save_ReturnSaved_WhenStudentRecordIsCreated() {

        int id = 1;

        final Student expected = new Student();
        expected.setFirstName("Tanq");
        expected.setLastName("Almisheva");

        given(repository.save(expected)).willAnswer(invocation -> {

            Student toSave = invocation.getArgument(0);

            toSave.setId(id);

            return toSave;
        });

        Student actual = fixture.save(expected);

        assertThat(actual).isEqualTo(expected);

        then(repository).should().save(expected);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void getStudent_ReturnsStudent_WhenStudentExist() {

        int id = 1;

        Student student = new Student(id, "Vasko", "Popov");

        Optional<Student> expected = Optional.of(student);

        given(repository.findById(id)).willReturn(expected);

        Optional<Student> actual = fixture.getStudent(id);

        assertThat(actual).isEqualTo(expected);

        then(repository).should().findById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void getStudent_ReturnsStudent_WhenStudentDoesNotExist() {

        int id = 10;

        final Optional<Student> expected = Optional.empty();

        given(repository.findById(id)).willReturn(expected);

        final Optional<Student> actual = fixture.getStudent(id);

        assertThat(actual).isEqualTo(expected);

        then(repository).should().findById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void delete_DeletesStudent_WhenStudentExists() {

        int id = 1;

        willDoNothing().given(repository).deleteById(id);

        fixture.delete(id);

        then(repository).should().deleteById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }

}