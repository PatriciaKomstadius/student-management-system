package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public Teacher createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
        return teacher;
    }

    public List<Teacher> getAllTeachers() {
        return entityManager.createNamedQuery("teacherEntity.findAll", Teacher.class).getResultList();
    }

    //ADD SUBJECTS TO TEACHER BY ID
    public Subject createSubjectForTeacher(Long id, Subject subject) {

        entityManager.find(Teacher.class, id).addSubject(subject);
        return subject;
    }

    //FIND TEACHER BY ID
    public Teacher findTeacherById(Long id) {
        return entityManager.find((Teacher.class), id);
    }


}

