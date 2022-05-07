package com.fnmps.readonly.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fnmps.readonly.repositories.model.TestDocument;

public interface TestRepository extends MongoRepository<TestDocument, String> {
}
