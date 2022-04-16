package io.resi.patch.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;

import io.resi.patch.beans.Bean;
import io.resi.patch.beans.Name;

interface Client {

	@PUT
	@Path("/optional")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Name patch(@RequestBody final Bean bean);
}
