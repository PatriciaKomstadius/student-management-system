package se.iths.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<CustomException> {


    @Override
    public Response toResponse(CustomException exception) {
        ErrorMessage errorMessage = new ErrorMessage(400, exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorMessage)
                .build();
    }

}
