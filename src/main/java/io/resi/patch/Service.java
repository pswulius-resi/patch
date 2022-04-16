package io.resi.patch;

import io.resi.patch.beans.Bean;
import io.resi.patch.beans.Dto;
import io.resi.patch.beans.Entity;
import io.resi.patch.mapping.Mappers;

public class Service {

	public static final Mappers<Bean, Dto, Entity> MAPPERS = new Mappers<>(Bean.class, Dto.class, Entity.class);
	private final Repository repository;

	public Service() {
		repository = new Repository();
	}

	public Dto get() {
		return MAPPERS.convertEntityToDto(repository.get());
	}

	public void save(final Dto dto) {
		Entity entity = MAPPERS.convertDtoToEntity(dto);
		repository.save(entity);
	}
}
