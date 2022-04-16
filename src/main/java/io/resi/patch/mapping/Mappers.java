package io.resi.patch.mapping;

import ma.glasnost.orika.BoundMapperFacade;

public class Mappers<BEAN, DTO, ENTITY> {

	protected final BoundMapperFacade<BEAN, DTO> bean_x_dto;
	protected final BoundMapperFacade<DTO, ENTITY> dto_x_entity;

	public Mappers(final Class<BEAN> beanClass, Class<DTO> dtoClass, Class<ENTITY> entityClass) {
		this(beanClass, dtoClass, entityClass, new SimpleMapFactory<>());
	}

	public Mappers(final Class<BEAN> beanClass, Class<DTO> dtoClass, Class<ENTITY> entityClass, MapFactory<BEAN, DTO, ENTITY> factory) {
		if (factory == null) {
			throw new IllegalArgumentException("a map factory is required");
		}
		this.bean_x_dto = factory.createBeanDtoMapper(beanClass, dtoClass).dedicatedMapperFor(beanClass, dtoClass);
		this.dto_x_entity = factory.createDtoEntityMapper(dtoClass, entityClass).dedicatedMapperFor(dtoClass, entityClass);
	}

	public void applyBeanToDto(BEAN bean, DTO dto) {
		bean_x_dto.map(bean, dto);
	}

	/**
	 *  Converting between objects creates a new instance of the return type, and applies fields
	 *  according to the supplied mapper.
	 *  @see SimpleMapFactory default mapping behavior defined here.
	 */
	public DTO convertBeanToDto(BEAN bean) {
		return bean_x_dto.map(bean);
	}

	public BEAN convertDtoToBean(DTO dto) {
		return bean_x_dto.mapReverse(dto);
	}

	public ENTITY convertDtoToEntity(DTO dto) {
		return dto_x_entity.map(dto);
	}

	public DTO convertEntityToDto(ENTITY entity) {
		return dto_x_entity.mapReverse(entity);
	}
}
