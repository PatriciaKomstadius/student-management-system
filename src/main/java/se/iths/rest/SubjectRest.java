package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.exception.NotFoundException;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;


    @Path("")
    @POST
    public Response createSubject(Subject subject) {

        Subject subjectResult = subjectService.createSubject(subject);
        return Response.ok(subjectResult).build();
    }

    //ADD STUDENT TO SUBJECT
    @Path("addstudenttosubject/{id}")
    @POST
    public Response createSubjectForStudent(@PathParam("id") Long id, Student student) {

        subjectService.addStudentToSubject(id, student);

        return Response.ok(student).build();
    }

    //GET ALL INFO ABOUT SUBJECT BY ID
    @Path("{id}")
    @GET
    public Response getListOfStudentsAndTeacherForSubject(@PathParam("id") Long id) {
        Subject foundSubject = subjectService.findSubjectById(id);

        if (foundSubject == null) {
            throw new se.iths.exception.NotFoundException("Subject with ID " + id + " doesn't exist.");
        }
        return Response.ok(foundSubject).build();
    }

    //Get ALL INFO ABOUT SUBJECT BY SUBJECTNAME
    @Path("all/{subject}")
    @GET
    public List<Subject> getTeacherBySubjectName(@PathParam("subject") String subject) {


        if (subjectService.getSubjectBySubjectName(subject).isEmpty()) {
            throw new NotFoundException("No teacher for " + subject + " is found.");
        }
        return subjectService.getSubjectBySubjectName(subject);
    }

    //Get teacher by lastname
    @Path("getteacherbyname/{lastname}")
    @GET
    public List<Teacher> getTeacherByLastname(@PathParam("lastname") String lastName) {

        if (subjectService.getTeacherByLastname(lastName).isEmpty()) {
            throw new NotFoundException("No teacher with lastname " + lastName + " is found.");
        }
        return subjectService.getTeacherByLastname(lastName);
    }

}
