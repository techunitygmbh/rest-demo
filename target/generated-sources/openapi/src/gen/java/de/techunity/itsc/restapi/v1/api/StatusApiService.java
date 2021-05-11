package de.techunity.itsc.restapi.v1.api;

import de.techunity.itsc.restapi.v1.api.*;
import de.techunity.itsc.restapi.v1.model.*;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import de.techunity.itsc.restapi.v1.model.Error;
import de.techunity.itsc.restapi.v1.model.InlineResponse503;
import de.techunity.itsc.restapi.v1.model.Status;

import java.util.List;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSCXFCDIServerCodegen", date = "2021-05-11T18:53:30.399573600+02:00[Europe/Berlin]")
public interface StatusApiService {
      public Response getStatus(SecurityContext securityContext);
}
