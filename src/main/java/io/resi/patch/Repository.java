package io.resi.patch;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.resi.patch.beans.Entity;

public class Repository {

	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public Repository() {

	}

	public Entity get() {
		return readFromDatabase();
	}

	public void save(final Entity entity) {
		LOG.info("Saving {}", entity);
	}

	private Entity readFromDatabase() {
		Entity entity = new Entity();
		entity.setFirstName("********");
		entity.setLastName("********");
		return entity;
	}
}
