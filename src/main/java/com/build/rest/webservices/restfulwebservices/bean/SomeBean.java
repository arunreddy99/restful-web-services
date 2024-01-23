package com.build.rest.webservices.restfulwebservices.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// jsonignore and jsonignoreProperties can be used alternatively one is at variable level other is at class level
//@JsonIgnoreProperties({"field1","field2"})
//alternatively we can use dynamic filtering by defining filter at the controller level and using that filter here 
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	private String field1;
	
//	@JsonIgnore
	private String field2;
	private String field3;
	public SomeBean(String filed1, String filed2, String filed3) {
		super();
		this.field1 = filed1;
		this.field2 = filed2;
		this.field3 = filed3;
	}
	public String getField1() {
		return field1;
	}
	public String getField2() {
		return field2;
	}
	public String getField3() {
		return field3;
	}
	@Override
	public String toString() {
		return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}
	

}
