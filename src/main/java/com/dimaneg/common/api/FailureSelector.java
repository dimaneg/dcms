package com.dimaneg.common.api;

import javax.enterprise.util.AnnotationLiteral;

public class FailureSelector extends AnnotationLiteral<Failure> implements Failure {

	private static final long serialVersionUID = 5731200562827776682L;
	
	private String value;
	
	public FailureSelector() {
		this("");
	}
	
	public FailureSelector(String value) {
		this.value = value;
	}
	
	@Override
	public String value() {
		return value;
	}

}
