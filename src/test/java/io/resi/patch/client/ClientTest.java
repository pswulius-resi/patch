package io.resi.patch.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import feign.slf4j.Slf4jLogger;
import io.resi.patch.beans.Bean;
import io.resi.patch.beans.Name;

public class ClientTest {

	public static final String LOCALHOST = "http://localhost:8080";
	public static final String NGROK = "https://resi-pete.ngrok.io";
	private static Client client;

	@BeforeAll
	public static void before() {
		ObjectMapper objectMapper = new ObjectMapper()
				.registerModule(new JavaTimeModule())
				.registerModule(new Jdk8Module())
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
				.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
				.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

		client = Feign.builder()
				.contract(new JAXRSContract())
				.encoder(new JacksonEncoder(objectMapper))
				.decoder(new JacksonDecoder(objectMapper))
				.logger(new Slf4jLogger())
				.target(Client.class, LOCALHOST);
	}

	@Test
	public void update() {
		Bean bean = new Bean();
		bean.setFirstName(Optional.of("first"));
		bean.setLastName(Optional.of("last"));
		Name result = client.patch(bean);
		assertEquals("first last", result.getName());
	}

	@Test
	public void ignore() {
		Bean bean = new Bean();
		bean.setFirstName("first");
		Name result = client.patch(bean);
		assertEquals("first ********", result.getName());
	}

	@Test
	public void unset() {
		Bean bean = new Bean();
		bean.setFirstName("first");
		bean.setLastName(Optional.empty());
		Name result = client.patch(bean);
		assertEquals("first null", result.getName());
	}
}
