package org.zanata.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoSuchEntityExceptionMapper extends
        RateLimitingAwareExceptionMapper implements
        ExceptionMapper<NoSuchEntityException> {

    @Override
    public Response toResponse(NoSuchEntityException exception) {
        releaseSemaphoreBeforeReturnResponse();
        return Response.status(Status.NOT_FOUND).entity(exception.getMessage())
                .build();
    }

}
