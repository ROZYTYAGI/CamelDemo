package com.camel_demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ViewInBrowser {

	/** The log. */
	final Logger log = LoggerFactory.getLogger(ViewInBrowser.class.getName());

	/**
	 * ProducerTemplate Class
	 */
	@Autowired
	private ProducerTemplate producerTemplate;

	/**
	 * ConsumerTemplate Class
	 */
	@Autowired
	private ConsumerTemplate consumerTemplate;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public @ResponseBody String startCamel(HttpServletResponse response) {
		log.info("in controller ::::::: ");
/*
		producerTemplate.sendBody("direct:viewInBrouser","helolo");
		String template = consumerTemplate.receiveBody("seda:endRoute", String.class);

		log.info("++++++++++ " + template);*/
		 MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
		    
		    
		    //Accessing the database 
		    MongoDatabase database = mongo.getDatabase("myDb");  
		    
		    //Creating a collection 
		    database.createCollection("sampleCollection");
		    MongoCollection<Document> collection = database.getCollection("sampleCollection");
			System.out.println("Collection sampleCollection selected successfully");
			Document document = new Document("title", "MongoDB")
			.append("description", "database")
			.append("likes", 100)
			.append("url", "http://www.tutorialspoint.com/mongodb/")
			.append("by", "tutorials point");
			
			//Inserting document into the collection
			collection.insertOne(document);
			System.out.println("Document inserted successfully");
			
	

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		return "hello world";
	}

	
}