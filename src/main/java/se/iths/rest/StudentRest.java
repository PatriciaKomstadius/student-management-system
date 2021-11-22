package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exception.BadRequestException;
import se.iths.exception.NotFoundException;
import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    //POST
    @Path("")
    @POST
    public Response createStudent(Student student) {

        if (student.getFirstName().isEmpty() || student.getLastName().isEmpty() || student.getEmail().isEmpty()) {
            throw new BadRequestException("You have to fill in firstname, lastname and email");
        }

        studentService.createStudent(student);

        return Response.ok(student).status(Response.Status.CREATED).build();
    }

    //GET one
    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {

        Student foundStudent = studentService.findStudentById(id);

        if (foundStudent == null) {
            throw new NotFoundException("Student with id " + id + " not found.");
        }

        return Response.ok(foundStudent).status(Response.Status.FOUND).build();
    }

    //GET all
    @Path("")
    @GET
    public Response getAllStudents() {

        List<Student> allStudents = studentService.getStudents();

        if (allStudents.isEmpty()) {
            throw new NotFoundException("No students enrolled.");
        }

        return Response.ok(allStudents).status(Response.Status.FOUND).build();
    }

    //Get student by lastname
    @Path("lastname")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getStudentByLastName(@QueryParam("lastname") String lastname) {

        List<Student> students = studentService.findStudentByLastName(lastname);

        List<Student> foundStudents = new ArrayList<>();

        for (Student s : students)
            if (s.getLastName().equals(lastname)) {
                foundStudents.add(s);
            }

        if (foundStudents.isEmpty()) {
            throw new NotFoundException("No student with lastname " + lastname + " is found.");
        }
        return Response.ok(foundStudents).status(Response.Status.FOUND).build();
    }

    //PUT
    @Path("")
    @PUT
    public Response updateStudent(Student student) {

        if (student.getId() == null)
            throw new BadRequestException("You have to enter student ID number to update contact information.");
        if (student.getFirstName().isEmpty() || student.getLastName().isEmpty() || student.getEmail().isEmpty()) {
            throw new BadRequestException("You have to fill in firstname, lastname and email. ");
        }
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    //DELETE
    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {

        Student foundStudent = studentService.findStudentById(id);

        if (foundStudent == null) {
            throw new NotFoundException("Student with id " + id + " not found.");
        }

        studentService.deleteStudent(id);
        return Response.ok().build();
    }

    //Get all students
    @Path("getstudents")
    @GET
    public List<Student> getNamedStudents() {
        return studentService.getNamedStudents();
    }

    //Get students by subject
    @Path("getsubjects/{subject}")
    @GET
    public List<Subject> getStudentsBySubject (@PathParam("subject") String subject) {
        return studentService.getSubjects(subject);
    }


}
