package org.servidor.firebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@SpringBootApplication
public class Campitosfirebase3Application{




@Autowired  RepositorioClave repoClave;



	@RequestMapping("/")
	String home() {
		//repoClave.save(new Clave("Yo","Juan Carlos"));
		//System.out.println("Registro gaurdado");
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Campitosfirebase3Application.class, args);

		// Create and populate a simple object to be used in the request
		Mensaje mensa = new Mensaje();
	mensa.setTo("dB_GbkOHUBQ:APA91bF289N_1e1NR2QBnyqGypg_XyKCwIDUbo09st9iVqwOjUZuEmFHVrXQ_4tQjD2KgsuZMq7fA3Z7Od8G0281JdPbD7KqlWaJ9ZoKolSLIbVqnAAd6ejMntnVHd4XgPcpZ7c_496l");
  Map<String,String> mapa=new HashMap<String, String>();
     Data data =new Data();
		data.setTitle("yo");
		data.setBody("Que poca madre");
		mensa.setData(data);
// Set the Content-Type header
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(new MediaType("application","json"));

		//headers.add("Authorization:AIzaSyDhBeG0yER-PYaTnATRWRgFTLVZQqn8Nso");
		requestHeaders.set("Authorization","key=AIzaSyDhBeG0yER-PYaTnATRWRgFTLVZQqn8Nso");

		HttpEntity<Mensaje> requestEntity = new HttpEntity<Mensaje>(mensa, requestHeaders);

// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();

// Make the HTTP POST request, marshaling the request to JSON, and the response to a String
		ResponseEntity<String> responseEntity = restTemplate.exchange("https://fcm.googleapis.com/fcm/send", HttpMethod.POST, requestEntity, String.class);
		String result = responseEntity.getBody();
   System.out.println(result);
	}
}
