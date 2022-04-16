package io.resi.patch.mapping;

import java.util.Optional;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class OptionalStringConverter extends BidirectionalConverter<String, Optional<String>> {
	@Override
	public Optional<String> convertTo(final String source, final Type<Optional<String>> destinationType, final MappingContext mappingContext) {
		return Optional.of(source);
	}

	@Override
	public String convertFrom(final Optional<String> source, final Type<String> destinationType, final MappingContext mappingContext) {
		return source.orElse(null);
	}
}
