package de.techunity.itsc.restapi.v1.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.techunity.itsc.restapi.v1.api.StatusApiService;
import de.techunity.itsc.restapi.v1.model.Status;
import de.techunity.itsc.restapi.v1.model.Status.StatusEnum;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

public class StatusApiServiceImpl implements StatusApiService {

	@Override
	public Response getStatus(SecurityContext securityContext) {
		Status status = new Status();
		status.setStatus(StatusEnum.OK);
		try {
			String string = new ObjectMapper()
					.writeValueAsString(status);
			return Response.ok(string).build();
		}
		catch (JsonProcessingException e) {
			return Response.serverError().build();
		}
	}
}
