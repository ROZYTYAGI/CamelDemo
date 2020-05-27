package com.camel_demo_route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.camel_demo.db.MongoDBConfig;


@Component
public class ViewInBrowserCamelRoute extends RouteBuilder {

	/** The log. */
	final Logger log = LoggerFactory.getLogger(ViewInBrowserCamelRoute.class.getName());
	

	 @Override
	 public void configure() throws Exception {
	  from("direct:firstRoute")
	   .log("Camel body: ${body}");
	 }
	
}
