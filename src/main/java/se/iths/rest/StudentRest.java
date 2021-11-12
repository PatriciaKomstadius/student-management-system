package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        studentService.createStudent(student);
        return Response.ok(student).build();
    }


    //GET one

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Student foundStudent = studentService.findStudentById(id);

        return Response.ok(foundStudent).build();

    }

    //GET all
    @Path("")
    @GET
    public Response getAllStudents(){
        List<Student> allStudents = studentService.getStudents();
        return Response.ok(allStudents).build();
    }

    //Get by lastname
    @Path("lastname")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getStudentByLastName(@QueryParam("lastname") String lastname) {

        List<Student> students = studentService.findStudentByLastMame(lastname);


        List<Student> foundStudents = new ArrayList<>();
        for (Student s : students)
            if (s.getLastName().equals(lastname)) {
                foundStudents.add(s);
            }
        return Response.ok(foundStudents).build();



    }




    //PUT
    @Path("")
    @PUT
    public Response updateStudent(Student student){
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }


    //DELETE
    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.ok().build();
    }


}
