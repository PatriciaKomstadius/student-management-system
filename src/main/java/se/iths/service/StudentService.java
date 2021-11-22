package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    //CREATE
    public Student createStudent(Student student) {
        entityManager.persist(student);
        return student;
    }

    //READ
    public List<Student> getStudents() {
        return entityManager.createQuery("select i from Student i", Student.class).getResultList();
    }

    public Student findStudentById(Long id) {
        return entityManager.find((Student.class), id);
    }

    public List<Student> findStudentByLastName(String lastname) {
        return entityManager.createQuery("select i from Student i", Student.class).getResultList();
    }

    //UPDATE
    public Student updateStudent(Student student) {
        entityManager.merge(student);
        return student;
    }
    //DELETE
    public void deleteStudent(Long id) {
        Student studentToDelete = entityManager.find(Student.class, id);
        entityManager.remove(studentToDelete);
    }

    public List<Student> getNamedStudents() {
        return entityManager.createNamedQuery("studentEntity.findAll", Student.class).getResultList();
    }

    //Find students by subject
    public List<Subject> getSubjects(String subject) {
        String query = "SELECT i.students FROM Subject i JOIN FETCH i.teacher WHERE i.subject = :subject";
        return entityManager.createQuery(query, Subject.class).
                setParameter("subject", subject)
                .getResultList();
    }




}
