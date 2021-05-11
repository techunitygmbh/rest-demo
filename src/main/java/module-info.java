module de.techunity.itsc {
	exports de.techunity.itsc;
	
	// Jakarta CDI 3.0 API - specification
	// Explicit dependencies are used. There are so called uber or shaded jars available from the library creators that
	// include all the dependencies. But using them creates hard to analyze dependency trees with ambiguous duplications of
	// dependencies.
	requires jakarta.cdi;
	// Weld 4.0.1 API - CDI implementation
	requires weld.se.core;
	// Weld 4.0.1 API - CDI support for servlets
	requires weld.servlet.core;
	// some modules of Weld we need to expose, so the class files are available at compile time
	requires weld.environment.common;
	requires weld.core.impl;
	requires weld.api;
	requires weld.spi;
	// Jakarta @inject annotation - needed to specify where CDI injections should occur
	requires jakarta.inject;

	// Jetty 11 - web server
	requires org.eclipse.jetty.server;
	// Jetty 11 - Servlet container
	requires org.eclipse.jetty.servlet;

	// Jakarta RESTful Web Services 3.0.0 - API specification
	requires transitive jakarta.ws.rs;
	// Jersey 3.0.1 - Jakarta RESTful Web Services implementation
	requires jersey.common;
	requires jersey.server;
	requires jersey.container.servlet.core;
	
	// Jackson 2.12.2 - JSON data binding library 
	// used by Jersey as a JSON provider (others can be used alternatively)
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.annotation;

	// SLF4J 1.7.30 - API for logging
	// uses Logback 1.2.3 as a logging library
	requires org.slf4j;

	// expose the packages containing our managed beans (all our classes that we want WELD to be able to handle) to the weld
	// module, so weld can access them via reflection
	opens de.techunity.itsc to weld.core.impl;
	
	// expose the packages containing all our Servlets to the weld module as well, and also to the Jersey REST framework,
	// for the same purpose.
	// A line like to following it not sufficient however, since also the accessing module Glassfish HK2 dependency
	// injection kernel is an unnamed module
	// opens de.techunity.itsc.restapi.v1.api to weld.core.impl, jersey.server;
	// so we just open those packages to all modules
	opens de.techunity.itsc.restapi.v1.api; 
	opens de.techunity.itsc.restapi.v1.model;
	opens de.techunity.itsc.restapi.v1.api.impl;

	
}
