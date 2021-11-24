package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.exception.BadRequestException;
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
    public Response createSubject(Subject subject){

        Subject subjectResult = subjectService.createSubject(subject);
        return Response.ok(subjectResult).build();
    }

    //ADD STUDENTS FOR SUBJECTS
    @Path("addstudentstosubject/{id}")
    @POST
    public Response createSubjectForTeacher(@PathParam("id")Long id, Student student){

        Subject foundSubject = subjectService.findSubjectById(id);

        if (foundSubject == null) {
            throw new NotFoundException("Subject with ID " + id + " doesnt exist. Unable to enroll student to subject.");
        }

        subjectService.addStudentsForSubject(id, student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @GET
    public Response getSubject(@PathParam("id") Long id){
        Subject foundSubject = subjectService.findSubjectById(id);

        if (foundSubject == null) {
            throw new se.iths.exception.NotFoundException("Subject with ID " + id + " doesn't exist.");
        }
        return Response.ok(foundSubject).build();
    }

    //Get subject
    @Path("getteacherforsubject/{subject}")
    @GET
    public List<Subject> getByNameNP(@PathParam("subject") String subject) {

        if (subjectService.getSubjectBySubjectName(subject).isEmpty()) {
            throw new NotFoundException("No teacher for " + subject + " is found.");
        }
        return subjectService.getSubjectBySubjectName(subject);
    }
/*
    //OBS FUNKAR EJ Hitta alla studenter
    @Path("getallstudentsbyfirstname/{name}")
    @GET
    public List<Subject> getAllStudents(@PathParam("name") String name) {
        return subjectService.getAllStudentsBySubject(name);
    }
 */

    //Get teacher by lastname
    @Path("getteacherbyname/{lastname}")
    @GET
    public List<Teacher> getTeacherByNameNP(@PathParam("lastname") String lastName) {

        if (subjectService.getTeacherByLastname(lastName).isEmpty()) {
            throw new NotFoundException("No teacher with lastname " + lastName + " is found.");
        }
        return subjectService.getTeacherByLastname(lastName);
    }
/*
    //TA BORT??
    //Get subject's teacher by searching subject
    @Path("getteacherforsubject/{subject}")
    @GET
    public List<Teacher> getTeacherBySubject (@PathParam("subject") String subject) {

        return subjectService.getTeachers(subject);
    }

 */


}