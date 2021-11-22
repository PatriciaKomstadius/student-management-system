package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {

        Subject subject1 = new Subject("Geography");
        Subject subject2 = new Subject("Math");
        Subject subject3 = new Subject("History");
        Subject subject4 = new Subject("English");

        Student student1 = new Student("Duke", "Larsson", "duke@student.se");
        Student student2 = new Student("Mona", "Nilsson", "mona@student.se");
        Student student3 = new Student("Luke", "Olsson", "luke@student.se");
        Student student4 = new Student("Mimi", "Larsson", "mimi@student.se");
        Student student5 = new Student("Molly", "Svensson", "molly@@student.se");


        Teacher teacher1 = new Teacher("Mona", "Månsson", "mona@skolan.se");
        Teacher teacher2 = new Teacher("Göran", "Göransson", "goran@skolan.se");
        Teacher teacher3 = new Teacher("Sven", "Svensson", "sven@skolan.se");
        Teacher teacher4 = new Teacher("Ulrika", "Ulriksson", "ukrika@skolan.se");
        Teacher teacher5 = new Teacher("Vikarie", "Vikariesson", "vikarie@skolan.se");

        teacher1.addSubject(subject1);
        teacher1.addSubject(subject2);
        teacher2.addSubject(subject3);
        teacher2.addSubject(subject4);

        subject1.addStudent(student5);
        subject1.addStudent(student1);
        subject2.addStudent(student2);
        subject3.addStudent(student3);
        subject4.addStudent(student2);
        subject4.addStudent(student5);
        subject4.addStudent(student1);
        subject4.addStudent(student4);

        entityManager.persist(subject1);
        entityManager.persist(subject2);
        entityManager.persist(subject3);
        entityManager.persist(subject4);

        entityManager.persist(teacher1);
        entityManager.persist(teacher2);
        entityManager.persist(teacher3);
        entityManager.persist(teacher4);
        entityManager.persist(teacher5);


    }

}
