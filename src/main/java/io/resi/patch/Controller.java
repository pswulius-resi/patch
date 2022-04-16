package io.resi.patch;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import io.resi.patch.beans.Bean;
import io.resi.patch.beans.Dto;
import io.resi.patch.beans.Name;

@Component
@Path("optional")
public class Controller {

	private final Service service;

	public Controller() {
		service = new Service();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Bean get() {
		Dto dto = service.get();
		return Service.MAPPERS.convertDtoToBean(dto);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Name patch(@RequestBody @Valid final Bean bean) {
		Dto dto = service.get();
		Service.MAPPERS.applyBeanToDto(bean, dto);
		service.save(dto);
		return new Name(dto.getFirstName() + " " + dto.getLastName());
	}
}
