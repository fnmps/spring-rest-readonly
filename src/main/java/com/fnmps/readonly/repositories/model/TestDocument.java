package com.fnmps.readonly.repositories.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TestDocument {

	private String id;
	private String someAttribute;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSomeAttribute() {
		return someAttribute;
	}

	public void setSomeAttribute(String someAttribute) {
		this.someAttribute = someAttribute;
	}

}
