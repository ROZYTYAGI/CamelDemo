package com.camel_demo.db;

import java.io.IOException;
import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;

public class MongoDBConfig implements Processor{

  //** The log. *//*
  private static final Logger log = LogManager.getLogger(MongoDBConfig.class.getName());


@Override
public void process(Exchange exchange) throws Exception {
	  
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
	
}

}
