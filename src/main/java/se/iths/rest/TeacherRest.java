package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.exception.BadRequestException;
import se.iths.exception.NotFoundException;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {

        if (teacher.getFirstName().isEmpty() || teacher.getLastName().isEmpty() || teacher.getEmail().isEmpty()) {
            throw new BadRequestException("You have to fill in firstname, lastname and email");
        }
        Teacher teacherResult = teacherService.createTeacher(teacher);
        return Response.ok(teacherResult).build();
    }

    @Path("")
    @GET
    public List<Teacher> getTeachers() {

        if (teacherService.getAllTeachers().isEmpty()) {
            throw new NotFoundException("No teachers registered at this school.");
        }
        return teacherService.getAllTeachers();
    }

    //ADD SUBJECTS TO TEACHER BY ID
    @Path("addsubjectstoteacher/{id}")
    @POST
    public Response createSubjectForTeacher(@PathParam("id") Long id, Subject subject) {

        Teacher foundTeacher = teacherService.findTeacherById(id);

        if (foundTeacher == null) {
            throw new se.iths.exception.BadRequestException("Teacher with ID " + id + " doesn't exist");
        }
        teacherService.createSubjectForTeacher(id, subject);

        return Response.ok(subject).build();

    }

}
