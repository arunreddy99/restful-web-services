package com.build.rest.webservices.restfulwebservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.build.rest.webservices.restfulwebservices.bean.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
//	@GetMapping("/filtering")
//	public SomeBean filtering() {
//		SomeBean someBean= new SomeBean("value1", "value2", "value3");
//		
//		return someBean;
//	}
	
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		SomeBean someBean= new SomeBean("value1", "value2", "value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		String[] list = {"field1"};
		applyFilters(mappingJacksonValue, list);
		return mappingJacksonValue;
	}

//	@GetMapping("/filtering-list")
//	public List<SomeBean> filteringList() {
//		
//		return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("field4","fileds5", "fierld6"));
//	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList() {
		
		List<SomeBean> asList = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("field4","fileds5", "fierld6"));
		MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(asList);
		String[] list = {"field2","field3"};
		applyFilters(mappingJacksonValue, list);
		return mappingJacksonValue;
	}

	private void applyFilters(MappingJacksonValue mappingJacksonValue, String[] list) {
		SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept(list);
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		mappingJacksonValue.setFilters(filters);
	}

}
