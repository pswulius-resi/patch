package io.resi.patch.config;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

	private static final String PACKAGE = "io.resi";
	private static final List<Class<? extends Annotation>> ANNOTATIONS = List.of(Path.class, Provider.class);

	public JerseyConfig() throws ClassNotFoundException {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);

		for (Class<? extends Annotation> annotation : ANNOTATIONS) {
			scanner.addIncludeFilter(new AnnotationTypeFilter(annotation));
		}

		for (BeanDefinition bd : scanner.findCandidateComponents(PACKAGE)) {
			register(Class.forName(bd.getBeanClassName()));
		}
	}
}
