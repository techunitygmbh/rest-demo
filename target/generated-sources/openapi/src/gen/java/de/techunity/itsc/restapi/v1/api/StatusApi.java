package de.techunity.itsc.restapi.v1.api;

import de.techunity.itsc.restapi.v1.model.Error;
import de.techunity.itsc.restapi.v1.model.InlineResponse503;
import de.techunity.itsc.restapi.v1.model.Status;
import de.techunity.itsc.restapi.v1.api.StatusApiService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.swagger.annotations.*;
import java.io.InputStream;

import org.apache.cxf.jaxrs.ext.PATCH;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
@Path("/status")
@RequestScoped

@Api(description = "the status API")


@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSCXFCDIServerCodegen", date = "2021-05-11T18:53:30.399573600+02:00[Europe/Berlin]")

public class StatusApi  {

  @Context SecurityContext securityContext;

  @Inject StatusApiService delegate;


    @GET
    
    
    @Produces({ "application/json" })
    @ApiOperation(value = "get status of application", notes = "get status of application", response = Status.class, tags={ "Status" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK - application is operational", response = Status.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class),
        @ApiResponse(code = 503, message = "application is temporaily not operational - maybe shutting down or starting up - don't send requests", response = InlineResponse503.class) })
    public Response getStatus() {
        return delegate.getStatus(securityContext);
    }
}
