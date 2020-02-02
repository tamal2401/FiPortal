package com.demo.loginportal.enumss;

public enum ActiveFlagEnum {

	Y("yes"), N("no");
	
	private String value;
	
	private ActiveFlagEnum(String value) {
		this.value = value;
	};
	
	public String getFlag() {
        return value;
    }
}
