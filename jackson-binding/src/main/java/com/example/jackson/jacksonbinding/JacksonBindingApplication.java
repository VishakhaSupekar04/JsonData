package com.example.jackson.jacksonbinding;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.Double.valueOf;

@SpringBootApplication
public class JacksonBindingApplication {


	public static void main(String[] args){

		try {
			Gson gson = new Gson();

			// 1. JSON file to Java object
			MyJsonObj myObject = gson.fromJson(new FileReader("/Users/gkalyankar/Downloads/jackson-binding/src/main/resources/myjson.json"), MyJsonObj.class);
			System.out.println("JSON serialized");

			// This is just to print serialized Java object into JSON format as a string 
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(myObject);
			System.out.println(json);

			//This is how you can access the serialized object data

			// To access single json record
			double coordinate=(double) myObject.features.get(0).geometry.coordinates.get(1);
			System.out.println(coordinate);

			// To iterate over all the json data
			Iterator<Feature> it = myObject.features.iterator();
			int i=1;
			while(it.hasNext())
			{
				Geometry geo = it.next().geometry;
				Iterator<Object> it2 = geo.coordinates.iterator();

				while(it2.hasNext())
				{
					Object obj = it2.next();
					if(obj instanceof ArrayList)
					{
						Iterator<Double> it3 =  ((ArrayList) obj).iterator();
						while(it3.hasNext()) {
							System.out.println("These are co-ordinates of coordinates list " + i + " =>" + it3.next());
						}

					}
					else {
						double value = (double) obj;
						System.out.println(
								"These are co-ordinates of coordinates list " + i + " =>" + value);
					}
				}
				i++;

			}

		}catch (Exception e){
			System.out.println(e.toString());
		}


	}

}
