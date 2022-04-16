package io.resi.patch.mapping;

import ma.glasnost.orika.impl.ConfigurableMapper;

public interface MapFactory<BEAN, DTO, ENTITY> {

	ConfigurableMapper createBeanDtoMapper(final Class<BEAN> beanType, final Class<DTO> dtoType);

	ConfigurableMapper createDtoEntityMapper(final Class<DTO> dtoType, final Class<ENTITY> entityType);
}
