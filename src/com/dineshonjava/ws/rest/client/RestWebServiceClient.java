package com.dineshonjava.ws.rest.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;

public class RestWebServiceClient {

    public static final String ACCOUNT_SID = "ACca4f7d564ff760a6c0d6b6444214feb8";
    public static final String AUTH_TOKEN = "97fa04ae3a1c30ee36b25f8b1ec0990e";
    
	public static void main(String[] args) {
		RestWebServiceClient client = new RestWebServiceClient();
		client.schedulerRun();
	}
	
	private void schedulerRun(){
		try {
	        while (true) {
	        	sayHello();
	            Thread.sleep(60 * 1000);
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	private void sayHello() {
		try {
            Client client = Client.create();
            WebResource webResource = client.resource("http://www.vogella.com/tutorials/REST/article.html");
            ClientResponse response = webResource.accept("text/plain").get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            String output = response.getEntity(String.class);
            System.out.println(output.substring(21027, 21077));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}	
	
	private boolean sendSms(String text){
		try{
			TwilioRestClient tclient = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
			Account account = tclient.getAccount();
			MessageFactory messageFactory = account.getMessageFactory();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("To", ("+919003258318"))); // Replace with a valid phone number for your account. 
			params.add(new BasicNameValuePair("From", "+17148729100")); // Replace with a valid phone number for your account.
			params.add(new BasicNameValuePair("Body", text));
			Message sms = messageFactory.create(params);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
