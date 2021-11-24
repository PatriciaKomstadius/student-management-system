package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;


    public Subject createSubject(Subject subject) {
        entityManager.persist(subject);
        return subject;
    }

    //Get subject by ID
    public Subject findSubjectById(Long id) {
        return entityManager.find(Subject.class, id);
    }

    //Get subject with Named Parameters
    public List<Subject> getSubjectBySubjectName(String subject) {
        String query = "SELECT i FROM Subject i WHERE i.subject = :subject";
        return entityManager.createQuery(query, Subject.class).setParameter("subject", subject).getResultList();
    }

    //Get all students by subject
    public List<Subject> getAllStudentsBySubject(String subject) {
        String query = "SELECT i FROM Subject i WHERE i.subject = :subject";
        return entityManager.createQuery(query, Subject.class).
                setParameter("subject", subject)
                .getResultList();
    }

    //Get teacher by lastname with Named Parameters
    public List<Teacher> getTeacherByLastname(String lastName) {
        String query = "SELECT i FROM Teacher i WHERE i.lastName = :lastName";
        return entityManager.createQuery(query, Teacher.class).setParameter("lastName", lastName).getResultList();
    }

    //ADD STUDENTS TO SUBJECTS
    public Student addStudentsForSubject(Long id, Student student) {
        entityManager.find(Subject.class, id).addStudent(student);
        return student;
    }

}
