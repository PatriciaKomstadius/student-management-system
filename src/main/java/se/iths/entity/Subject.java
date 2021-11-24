package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    private Teacher teacher;


    public void addStudent(Student student) {
        students.add(student);
        student.setSubject(this);
    }

    public Subject() {
    }

    public Subject(String subject) {
        this.subject = subject;
    }

    @JsonbTransient
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
       this.teacher = teacher;
    }
}
