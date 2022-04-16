package io.resi.patch.mapping;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

public class SimpleMapFactory<BEAN, DTO, ENTITY> implements MapFactory<BEAN, DTO, ENTITY> {
	@Override
	public ConfigurableMapper createBeanDtoMapper(final Class<BEAN> beanType, final Class<DTO> dtoType) {
		return new ConfigurableMapper() {
			@Override
			public void configure(final MapperFactory mapperFactory) {
				mapperFactory.getConverterFactory().registerConverter(new OptionalStringConverter());
				mapperFactory.classMap(beanType, dtoType)
						.mapNulls(false)
						.mapNullsInReverse(false)
						.byDefault()
						.register();
			}
		};
	}

	@Override
	public ConfigurableMapper createDtoEntityMapper(final Class<DTO> dtoType, final Class<ENTITY> entityType) {
		return new ConfigurableMapper() {
			@Override
			public void configure(final MapperFactory mapperFactory) {
				mapperFactory.getConverterFactory().registerConverter(new OptionalStringConverter());
				mapperFactory.classMap(dtoType, entityType)
						.mapNulls(false)
						.mapNullsInReverse(false)
						.byDefault()
						.register();
			}
		};
	}
}
