package com.slack.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@RestController
public class SlackSendMessage {
	
	/*
	 * 
	 * for sending msg to slack 
	 * first create the channel in slack 
	 * create web hook for channel --> the slack provide hook url -> the will integrate in code 
	 */
	
	/*  post man url 
	 
	curl --location 'http://localhost:8080/template/products' \
	--header 'Content-Type: application/json' \
	--data '{
	    "text":"HELLO 1"
	}'
	
	*/
	
	
	  @Autowired
	   RestTemplate restTemplate;

	   @PostMapping(value = "/template/products")
	   public void getProductList(@RequestBody String msg) {
		  String url="https://hooks.slack.com/services/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";
		  //String msg="{\"text\":\"hello maju\"}";
		  HashMap<String,String> hm= new HashMap<>();
		  hm.put("text",msg);
		  Gson gson = new Gson(); 
		  String json = gson.toJson(hm);
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<String> entity = new HttpEntity<String>(json,headers);
	      
	      restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	   }

}
