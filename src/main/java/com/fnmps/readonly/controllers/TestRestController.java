package com.fnmps.readonly.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fnmps.readonly.api.TestApi;
import com.fnmps.readonly.model.Test;
import com.fnmps.readonly.repositories.TestRepository;
import com.fnmps.readonly.repositories.model.TestDocument;

@Controller
public class TestRestController implements TestApi {
	
	private TestRepository repository;
	
	public TestRestController(TestRepository repository) {
		this.repository = repository;
	}

	public ResponseEntity<Void> createTest(@Valid Test body) {
		TestDocument doc = new TestDocument();
		doc.setId(UUID.randomUUID().toString());
		doc.setSomeAttribute(body.getSomeAttribute());
		repository.save(doc);
		return ResponseEntity.ok(null);
	}

	public ResponseEntity<Test> getTest(String testId) {
		Test result = null;
		Optional<TestDocument> test = repository.findById(testId);
		if(test.isPresent()) {
			result = new Test();
			result.setId(test.get().getId());
			result.setSomeAttribute(test.get().getSomeAttribute());
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<Void> updateTest(String testId, @Valid Test body) {
		Optional<TestDocument> test = repository.findById(testId);
		if(test.isPresent()) {
			TestDocument doc = test.get();
			doc.setId(testId);
			doc.setSomeAttribute(body.getSomeAttribute());
			repository.save(doc);
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
