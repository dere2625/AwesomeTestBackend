package com.dere.codesvalidate.repository;

import com.dere.codesvalidate.models.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigurationRepository extends MongoRepository<Configuration, String> {
}
