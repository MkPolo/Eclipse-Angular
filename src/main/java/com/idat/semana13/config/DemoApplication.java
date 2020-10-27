package com.idat.semana13.config;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.catalina.filters.CorsFilter;

import com.idat.semana13.controller.UsuarioController;


@ApplicationPath("rest")
public class DemoApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		
		Set<Class<?>> classes = new HashSet<Class<?>>();
		
		classes.add(UsuarioController.class);
		
		return classes;
	}
	
	/*
	@Override
	public Set<Object> getSingletons(){
		
		Set<Object> providers = new HashSet<>();
	    CorsFilter corsFilter = new CorsFilter();
	    corsFilter.getAllowedOrigins().add("*");
	    corsFilter.getAllowedHttpMethods().add("OPTIONS");
	    corsFilter.getAllowedHttpMethods().add("GET");
	    //corsFilter.getAllowedHttpHeaders().add("Origin, X-Requested-With, Content-Type, Accept");
	    corsFilter.getAllowedHttpHeaders().add("Origin");
	    corsFilter.getAllowedHttpHeaders().add("X-Requested-With");
	    corsFilter.getAllowedHttpHeaders().add("Content-Type");
	    corsFilter.getAllowedHttpHeaders().add("Accept");
	    providers.add(corsFilter);
	    return providers;
	}
	*/
}