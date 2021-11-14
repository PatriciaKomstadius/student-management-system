package se.iths.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<CustomException> {

    @Override
    public Response toResponse(CustomException exception) {
        ErrorMessage errorMessage = new ErrorMessage(404, exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }

//          return (Response.status(Response.Status.NOT_FOUND)
  //          .entity("Student with id not found.").type(MediaType.APPLICATION_JSON).build());

}
